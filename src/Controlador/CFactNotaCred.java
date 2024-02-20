package Controlador;



import Modelo.MFactNotaCred;
import Vista.VFactNotaCred;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CFactNotaCred implements ActionListener {

     VFactNotaCred vista;
 MFactNotaCred modelo = new  MFactNotaCred();

    public enum AccionMVC {

        AGREGAR,
        GUARDAR,
        CANCELAR,
        SALIR,
        AGREGAR_DETALLE,
        ELIMINAR_DETALLE
    }

    public CFactNotaCred(VFactNotaCred vista) {
        this.vista = vista;
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        vista.txtTotal.setText(String.valueOf(0));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();
        String lastdate = formatter.format(currentDate);
        this.vista.txtfecha.setText(lastdate);

        this.vista.AgregarVenta.setActionCommand("AGREGAR");
        this.vista.AgregarVenta.addActionListener(this);

        this.vista.GuardarVenta.setActionCommand("GUARDAR");
        this.vista.GuardarVenta.addActionListener(this);

        this.vista.CancelarVenta.setActionCommand("CANCELAR");
        this.vista.CancelarVenta.addActionListener(this);

        this.vista.SalirVenta.setActionCommand("SALIR");
        this.vista.SalirVenta.addActionListener(this);

        this.vista.btnagregar_detalle.setActionCommand("AGREGAR_DETALLE");
        this.vista.btnagregar_detalle.addActionListener(this);

        this.vista.btneliminar_detalle.setActionCommand("ELIMINAR_DETALLE");
        this.vista.btneliminar_detalle.addActionListener(this);

        this.vista.comboProveedor.setModel(this.modelo.getListaProveedor());
        this.vista.cboInsumos.setModel(this.modelo.getListaInsumos());
        this.vista.ComboaFacturaCompra.setModel(this.modelo.getListaFacturaCompra());
        deshabilitaBotones();
    }

    public void actionPerformed(ActionEvent e) {
        switch (CFactNotaCred.AccionMVC.valueOf(e.getActionCommand())) {
            case AGREGAR:
                this.vista.cboInsumos.setEnabled(true);
                this.vista.comboProveedor.setEnabled(true);
               
                this.vista.txtcantidad.setEnabled(true);
                this.vista.btnagregar_detalle.setEnabled(true);
                this.vista.CancelarVenta.setEnabled(true);
                this.vista.AgregarVenta.setEnabled(false);
                this.vista.SalirVenta.setEnabled(false);
                this.modelo.generar_codigo();
                this.vista.txtcodfact.setText(this.modelo.idNotacreditoCompras);
                break;
            case GUARDAR:
                String cliente[] = this.vista.comboProveedor.getSelectedItem().toString().split("-");
                String FacturaCompra[] = this.vista.ComboaFacturaCompra.getSelectedItem().toString().split("-");
                int cantidadMercaderias = this.vista.tablaventas.getRowCount();
                JTable tabla3 = this.vista.tablaventas;
                if (this.modelo.RegistrarDetalleNotaCompra(
                        this.vista.txtcodfact.getText(),                        
                        this.vista.txtfecha.getText(),
                       cliente[0].trim(),
                       FacturaCompra[0].trim()
                        )) {
                    for (int i = 0; i < cantidadMercaderias; i++) {
                        String codigoMercadria = tabla3.getValueAt(i, 0).toString();
                        String cantidadMercaderia = tabla3.getValueAt(i, 2).toString();
                        String precioMercaderia = tabla3.getValueAt(i, 3).toString();
                         String subtotal = tabla3.getValueAt(i, 4).toString();
                        this.modelo.RegistrarDetalleNotaCompra(this.vista.txtcodfact.getText(),
                                cantidadMercaderia, precioMercaderia,subtotal);
                    }
             //       this.modelo.Reportes("Facturacion",this.vista.tablaventas,this.vista.txtnrofact.setText(""));
                    JOptionPane.showMessageDialog(vista, "Exito: Nuevo registro agregado.");
                } else {
                    JOptionPane.showMessageDialog(vista, "Error: Los datos son incorrectos.");
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
               JTable tabla = this.vista.tablaventas;
                String totalgeneral = this.vista.txtTotal.getText();
                String codigo[] = this.vista.cboInsumos.getSelectedItem().toString().split("-");
                String descripcion[] = this.vista.cboInsumos.getSelectedItem().toString().split("-");
                String precio[] = this.vista.cboInsumos.getSelectedItem().toString().split("-");
             
                 if (this.modelo.cargarDatos(codigo[0].trim(), descripcion[1].trim()
                        , this.vista.txtcantidad.getText().trim(),precio[2].trim(),tabla, totalgeneral))
                {
                    this.vista.txtTotal.setText(String.valueOf(this.modelo.totales3));
                }

                 else {
                  //  JOptionPane.showMessageDialog(vista, "La mercaderia ya existe en el detalle..", "Atención",
                       //     JOptionPane.WARNING_MESSAGE);
                }
                this.vista.btneliminar_detalle.setEnabled(true);
                this.vista.GuardarVenta.setEnabled(true);
                break;
            case ELIMINAR_DETALLE:
                JTable tabla2 = this.vista.tablaventas;
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) tabla2.getModel();
                int indice = tabla2.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado el Detalle a borrar..",
                            "Atención", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Holla");
                    String totales = (tabla2.getValueAt(indice, 3).toString());
                    String cant = (tabla2.getValueAt(indice, 2).toString());
                     JOptionPane.showMessageDialog(null, "Holla1");
   //                 String.valueOf(this.vista.TablaCate.getValueAt(fila, 1))
                    int totales2 = Integer.parseInt(totales)*Integer.parseInt(cant);
                    int totales3 = Integer.parseInt(this.vista.txtTotal.getText());
                    int totales4 = totales3 - totales2;
                    this.vista.txtTotal.setText(String.valueOf(totales4));
                    dtm.removeRow(indice);
                }
                int nro_fila = tabla2.getRowCount();
                if (nro_fila == 0) {
                    this.vista.btneliminar_detalle.setEnabled(false);
                    this.vista.GuardarVenta.setEnabled(false);
                }
                break;
        }
    }

    public void limpiar() {
        int totales = 0;
     //   this.vista.txtnrofact.setText("");
        this.vista.txtcantidad.setText("");
        this.vista.txtTotal.setText(String.valueOf(totales));
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
        this.vista.txtfecha.setEnabled(false);
        this.vista.txtcodfact.setEnabled(false);
        this.vista.cboInsumos.setEnabled(false);
        this.vista.comboProveedor.setEnabled(false);
        this.vista.txtcantidad.setEnabled(false);
        this.vista.btnagregar_detalle.setEnabled(false);
        this.vista.btneliminar_detalle.setEnabled(false);
        this.vista.GuardarVenta.setEnabled(false);
        this.vista.CancelarVenta.setEnabled(false);
        this.vista.AgregarVenta.setEnabled(true);
        this.vista.SalirVenta.setEnabled(true);
        this.vista.txtTotal.setEnabled(true);
        limpiar();
    }
}