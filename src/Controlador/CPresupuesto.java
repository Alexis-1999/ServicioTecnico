package Controlador;



import Modelo.MPresupuesto;
import Vista.VPresupuesto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CPresupuesto implements ActionListener {

    VPresupuesto vista;
    MPresupuesto modelo = new MPresupuesto();

    public enum AccionMVC {

        AGREGAR,
        GUARDAR,
        CANCELAR,
        SALIR,
        AGREGAR_DETALLE,
        ELIMINAR_DETALLE
    }

    public CPresupuesto(VPresupuesto vista) {
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

        this.vista.Agregar.setActionCommand("AGREGAR");
        this.vista.Agregar.addActionListener(this);

        this.vista.Guardar.setActionCommand("GUARDAR");
        this.vista.Guardar.addActionListener(this);

        this.vista.Cancelar.setActionCommand("CANCELAR");
        this.vista.Cancelar.addActionListener(this);

        this.vista.Salir.setActionCommand("SALIR");
        this.vista.Salir.addActionListener(this);

        this.vista.agregarDetalle.setActionCommand("AGREGAR_DETALLE");
        this.vista.agregarDetalle.addActionListener(this);

        this.vista.eliminarDetalle.setActionCommand("ELIMINAR_DETALLE");
        this.vista.eliminarDetalle.addActionListener(this);

        this.vista.comboFuncionario.setModel(this.modelo.getListaFuncionario());
        this.vista.ComboRecepcion.setModel(this.modelo.getListaRecepcion());
        this.vista.comboServicios.setModel(this.modelo.getListaTipoServicios());
        deshabilitaBotones();
    }

    public void actionPerformed(ActionEvent e) {
        switch (CPresupuesto.AccionMVC.valueOf(e.getActionCommand())) {
            case AGREGAR:
                this.vista.comboFuncionario.setEnabled(true);
                this.vista.ComboRecepcion.setEnabled(true);
                this.vista.comboServicios.setEnabled(true);
                this.vista.txtcantidad.setEnabled(true);
                this.vista.agregarDetalle.setEnabled(true);
                this.vista.Cancelar.setEnabled(true);
                this.vista.Agregar.setEnabled(false);
                this.vista.Salir.setEnabled(false);
                this.modelo.generar_codigo();
                this.vista.txtPresupuesto.setText(this.modelo.presupuesto);
                break;
            case GUARDAR:
                String Funcionario[] = this.vista.comboFuncionario.getSelectedItem().toString().split("-");
                String Recepcion[] = this.vista.ComboRecepcion.getSelectedItem().toString().split("-");
                int cantidadMercaderias = this.vista.tablaventas.getRowCount();
                JTable tabla3 = this.vista.tablaventas;
                if (this.modelo.Registrarpresupuesto(
                        this.vista.txtPresupuesto.getText(),                        
                        this.vista.txtfecha.getText(),
                         this.vista.txtTotal.getText(),
                         this.vista.CampoCodUsuario1.getText(),
                        Funcionario[0].trim(),
                        Recepcion[0].trim()
                        )) {
                    for (int i = 0; i < cantidadMercaderias; i++) {
                        String codigoMercadria = tabla3.getValueAt(i, 0).toString();
                        String cantidadMercaderia = tabla3.getValueAt(i, 2).toString();
//                       String precioMercaderia = tabla3.getValueAt(i, 3).toString();
//                         String subtotal = tabla3.getValueAt(i, 4).toString();
                        this.modelo.RegistrarDetallepresupuesto(this.vista.txtPresupuesto.getText(), codigoMercadria,
                                cantidadMercaderia);
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
                String codigo[] = this.vista.comboServicios.getSelectedItem().toString().split("-");
                String descripcion[] = this.vista.comboServicios.getSelectedItem().toString().split("-");
                String precio[] = this.vista.comboServicios.getSelectedItem().toString().split("-");
             
                 if (this.modelo.cargarDatos(codigo[0].trim(), descripcion[1].trim()
                        , this.vista.txtcantidad.getText().trim(),precio[2].trim(),tabla, totalgeneral))
                {
                    this.vista.txtTotal.setText(String.valueOf(this.modelo.totales3));
                }

                 else {
                  //  JOptionPane.showMessageDialog(vista, "La mercaderia ya existe en el detalle..", "Atención",
                       //     JOptionPane.WARNING_MESSAGE);
                }
                this.vista.eliminarDetalle.setEnabled(true);
                this.vista.Guardar.setEnabled(true);
                break;
            case ELIMINAR_DETALLE:
                JTable tabla2 = this.vista.tablaventas;
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) tabla2.getModel();
                int indice = tabla2.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado el Detalle a borrar..",
                            "Atención", JOptionPane.WARNING_MESSAGE);
                } else {
                    //JOptionPane.showMessageDialog(null, "");
                    String totales = (tabla2.getValueAt(indice, 3).toString());
                    String cant = (tabla2.getValueAt(indice, 2).toString());
                     //JOptionPane.showMessageDialog(null, "");
   //                 String.valueOf(this.vista.TablaCate.getValueAt(fila, 1))
                    int totales2 = Integer.parseInt(totales)*Integer.parseInt(cant);
                    int totales3 = Integer.parseInt(this.vista.txtTotal.getText());
                    int totales4 = totales3 - totales2;
                    this.vista.txtTotal.setText(String.valueOf(totales4));
                    dtm.removeRow(indice);
                }
                int nro_fila = tabla2.getRowCount();
                if (nro_fila == 0) {
                    this.vista.eliminarDetalle.setEnabled(false);
                    this.vista.Guardar.setEnabled(false);
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
        this.vista.txtPresupuesto.setEnabled(false);
        this.vista.comboServicios.setEnabled(false);
        this.vista.comboFuncionario.setEnabled(false);
         this.vista.ComboRecepcion.setEnabled(false);
        this.vista.txtcantidad.setEnabled(false);
        this.vista.agregarDetalle.setEnabled(false);
        this.vista.eliminarDetalle.setEnabled(false);
        this.vista.Guardar.setEnabled(false);
        this.vista.Cancelar.setEnabled(false);
        this.vista.Agregar.setEnabled(true);
        this.vista.Salir.setEnabled(true);
        this.vista.txtTotal.setEnabled(true);
        limpiar();
    }
}