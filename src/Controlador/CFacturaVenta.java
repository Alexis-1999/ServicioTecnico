
package Controlador;


import Modelo.MFacturaVenta;
import Vista.VFacturaVenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class CFacturaVenta implements ActionListener {

    VFacturaVenta vista;
    MFacturaVenta modelo = new MFacturaVenta();

    public enum AccionMVC {

        AGREGAR,
        GUARDAR,
        CANCELAR,
        SALIR,
        AGREGAR_DETALLE,
        ELIMINAR_DETALLE
    }

    public CFacturaVenta(VFacturaVenta vista) {
        this.vista = vista;
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.CampoTotal.setText(String.valueOf(0));
        
        this.vista.CampoCodigoUsuario.setEnabled(false);
        this.vista.CampoNombreUsuario.setEnabled(false);
        this.vista.CampoStock.setVisible(false);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();
        String lastdate = formatter.format(currentDate);
        this.vista.CampoFecha.setText(lastdate);

        this.vista.BotonAgregarVenta.setActionCommand("AGREGAR");
        this.vista.BotonAgregarVenta.addActionListener(this);

        this.vista.BotonGuardarVenta.setActionCommand("GUARDAR");
        this.vista.BotonGuardarVenta.addActionListener(this);

        this.vista.BotonCancelarVenta.setActionCommand("CANCELAR");
        this.vista.BotonCancelarVenta.addActionListener(this);

        this.vista.BotonSalirVenta.setActionCommand("SALIR");
        this.vista.BotonSalirVenta.addActionListener(this);

        this.vista.BotonAgregar_Detalle.setActionCommand("AGREGAR_DETALLE");
        this.vista.BotonAgregar_Detalle.addActionListener(this);

        this.vista.BotonEliminar_Detalle.setActionCommand("ELIMINAR_DETALLE");
        this.vista.BotonEliminar_Detalle.addActionListener(this);

        this.vista.ComboCliente.setModel(this.modelo.getListaClientes());
        this.vista.ComboProducto.setModel(this.modelo.getListaProducto1());
        this.vista.ComboTimbrado.setModel(this.modelo.getListaTimbrado());
        deshabilitaBotones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (CFacturaVenta.AccionMVC.valueOf(e.getActionCommand())) {
            case AGREGAR:
                this.vista.ComboProducto.setEnabled(true);
                this.vista.ComboCliente.setEnabled(true);
                this.vista.CampoCantidad.setEnabled(true);
                this.vista.BotonAgregar_Detalle.setEnabled(true);
                this.vista.BotonCancelarVenta.setEnabled(true);
                this.vista.BotonAgregarVenta.setEnabled(false);
                this.vista.BotonSalirVenta.setEnabled(false);
                this.modelo.generar_codigo();
                this.vista.CampoNro.setText(this.modelo.Nro_Factura);
                break;
            case GUARDAR:
                String cliente[] = this.vista.ComboCliente.getSelectedItem().toString().split("-");
                String timbrado[] = this.vista.ComboTimbrado.getSelectedItem().toString().split("-");
                int cantidadMercaderias = this.vista.tablaventas.getRowCount();
                JTable tabla3 = this.vista.tablaventas;
                if (this.modelo.RegistrarVenta(
                        this.vista.CampoNro.getText(),
                        timbrado[3].trim(),
                        this.vista.CampoFecha.getText(),
                        cliente[0].trim(),
                        this.vista.CampoCodigoUsuario.getText()
                )) {
                    
                    for (int i = 0; i < cantidadMercaderias; i++) {
                        String codigoMercadria = tabla3.getValueAt(i, 0).toString();
                        String cantidadMercaderia = tabla3.getValueAt(i,2).toString();
                        String precioMercaderia = tabla3.getValueAt(i, 3).toString();
                        this.modelo.RegistrarDetalleVenta(this.vista.CampoNro.getText(), codigoMercadria,
                                cantidadMercaderia, precioMercaderia);
                    }
                    JOptionPane.showMessageDialog(vista, "Exito: Nuevo registro agregado.");
                    this.modelo.Reportes("FacturaVentas", this.vista.tablaventas, this.vista.CampoNro.getText(),
                           
                           this.vista.CampoFecha.getText(),
                           this.vista.CampoTotal.getText() );
                  
                } else {
                  //  JOptionPane.showMessageDialog(vista, "Error: Los datos son incorrectos.");
                }
                deshabilitaBotones();
                break;
            case CANCELAR:
                deshabilitaBotones();
                limpiar();
                break;
            case SALIR:
                this.vista.dispose();
                break;
            case AGREGAR_DETALLE:
            //controla stock de producto
         
                        JTable tabla = this.vista.tablaventas;
                         String totalgeneral = this.vista.CampoTotal.getText();
                         String codigo[] = this.vista.ComboProducto.getSelectedItem().toString().split("-");
                         String descripcion[] = this.vista.ComboProducto.getSelectedItem().toString().split("-");
                         String precio[] = this.vista.ComboProducto.getSelectedItem().toString().split("-");

                          if (this.modelo.cargarDatos(codigo[0].trim(), descripcion[1].trim()
                                 + " - " + descripcion[3].trim(), this.vista.CampoCantidad.getText().trim(),precio[2].trim(),tabla, totalgeneral))
                         {
                             this.vista.CampoTotal.setText(String.valueOf(this.modelo.totales3));
                         }

                          else {
                           //  JOptionPane.showMessageDialog(vista, "La mercaderia ya existe en el detalle..", "Atención",
                                //     JOptionPane.WARNING_MESSAGE);
                         }
                         this.vista.BotonEliminar_Detalle.setEnabled(true);
                         this.vista.BotonGuardarVenta.setEnabled(true);
             
                         break;
            case ELIMINAR_DETALLE:
                JTable tabla2 = this.vista.tablaventas;
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) tabla2.getModel();
                int indice = tabla2.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado el Detalle a borrar..",
                            "Atención", JOptionPane.WARNING_MESSAGE);
                } else {
                    
                    String totales = (tabla2.getValueAt(indice, 3).toString());
                    String cant = (tabla2.getValueAt(indice, 2).toString());
                   
   //                 String.valueOf(this.vista.TablaCate.getValueAt(fila, 1))
                    int totales2 = Integer.parseInt(totales)*Integer.parseInt(cant);
                    int totales3 = Integer.parseInt(this.vista.CampoTotal.getText());
                    int totales4 = totales3 - totales2;
                    this.vista.CampoTotal.setText(String.valueOf(totales4));
                    dtm.removeRow(indice);
                }
                int nro_fila = tabla2.getRowCount();
                if (nro_fila == 0) {
                    this.vista.BotonEliminar_Detalle.setEnabled(false);
                    this.vista.BotonGuardarVenta.setEnabled(false);
                }
                break;
        }
    }

    public void limpiar() {
        int totales = 0;
     //   this.vista.txtnrofact.setText("");
        this.vista.CampoCantidad.setText("");
        this.vista.CampoStock.setText("");
        
        this.vista.CampoTotal.setText(String.valueOf(totales));
        //limpiar tabla
        this.vista.tablaventas.selectAll();
        int[] L = this.vista.tablaventas.getSelectedRows();
        javax.swing.table.DefaultTableModel dtm =
                (javax.swing.table.DefaultTableModel) this.vista.tablaventas.getModel();
        for (int i = L.length - 1; i >= 0; --i) {
            dtm.removeRow(L[i]);
        }
    }

    private void deshabilitaBotones() {
        this.vista.CampoFecha.setEnabled(false);
        this.vista.CampoNro.setEnabled(false);
        this.vista.CampoStock.setEnabled(false);
        this.vista.ComboProducto.setEnabled(false);
        this.vista.ComboCliente.setEnabled(false);
        this.vista.CampoCantidad.setEnabled(false);
        this.vista.BotonAgregar_Detalle.setEnabled(false);
        this.vista.BotonEliminar_Detalle.setEnabled(false);
        this.vista.BotonGuardarVenta.setEnabled(false);
        this.vista.BotonCancelarVenta.setEnabled(false);
        this.vista.BotonAgregarVenta.setEnabled(true);
        this.vista.BotonSalirVenta.setEnabled(true);
        this.vista.CampoTotal.setEnabled(true);
        limpiar();
    }
}