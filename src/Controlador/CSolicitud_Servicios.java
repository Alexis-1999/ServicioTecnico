
package Controlador;


import Modelo.MSolicitud_Servicios;
import Vista.VSolicitud_Servicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class CSolicitud_Servicios implements ActionListener {

    VSolicitud_Servicios vista;
    MSolicitud_Servicios modelo = new MSolicitud_Servicios();

    public enum AccionMVC {

        AGREGAR,
        GUARDAR,
        CANCELAR,
        SALIR,
        AGREGAR_DETALLE,
        ELIMINAR_DETALLE
    }

    public CSolicitud_Servicios(VSolicitud_Servicios vista) {
        this.vista = vista;
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);
//        vista.CampoTotal.setText(String.valueOf(0));
        
//        this.vista.CampoNro.setEnabled(false);
        this.vista.CampoCodigoUsuario.setEnabled(false);
        this.vista.CampoNombreUsuario.setEnabled(false);
//        this.vista.CampoStock.setVisible(false);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date currentDate = new Date();
        String lastdate = formatter.format(currentDate);
        this.vista.CampoFecha.setText(lastdate);

        this.vista.BotonAgregar.setActionCommand("AGREGAR");
        this.vista.BotonAgregar.addActionListener(this);

        this.vista.BotonGuardar.setActionCommand("GUARDAR");
        this.vista.BotonGuardar.addActionListener(this);

        this.vista.BotonCancelar.setActionCommand("CANCELAR");
        this.vista.BotonCancelar.addActionListener(this);

        this.vista.BotonSalir.setActionCommand("SALIR");
        this.vista.BotonSalir.addActionListener(this);

        this.vista.BotonAgregar_Detalle.setActionCommand("AGREGAR_DETALLE");
        this.vista.BotonAgregar_Detalle.addActionListener(this);

        this.vista.BotonEliminar_Detalle.setActionCommand("ELIMINAR_DETALLE");
        this.vista.BotonEliminar_Detalle.addActionListener(this);

        this.vista.ComboCliente.setModel(this.modelo.getListaClientes());
        this.vista.ComboProducto.setModel(this.modelo.getListaProducto1());
//        this.vista.ComboTimbrado.setModel(this.modelo.getListaTimbrado());
        this.vista.txtestado.setText(modelo.getDescripcionEstado(modelo.getEstadoActivo()));
        deshabilitaBotones();
    }

    // Método para preparar los datos necesarios para la vista
//    private void prepararDatosParaVista() {
//        // Obtener el estado activo
//        int idEstadoActivo = modelo.getEstadoActivo();
//
//        // Obtener la descripción del estado activo
//        String descripcionEstadoActivo = modelo.getDescripcionEstado(modelo.getEstadoActivo());
//
//        // Usar la descripciónEstadoActivo en tu vista
//        // Por ejemplo, aquí podrías establecer la descripción del estado en un campo de texto de tu vista
//        this.vista.txtestado.setText(descripcionEstadoActivo);
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (CSolicitud_Servicios.AccionMVC.valueOf(e.getActionCommand())) {
            case AGREGAR:
                this.vista.ComboProducto.setEnabled(true);
                this.vista.ComboCliente.setEnabled(true);
                this.vista.CampoCantidad.setEnabled(true);
                this.vista.txtDescripcion.setEnabled(true);
                this.vista.BotonAgregar_Detalle.setEnabled(true);
                this.vista.BotonCancelar.setEnabled(true);
                this.vista.BotonAgregar.setEnabled(false);
                this.vista.BotonSalir.setEnabled(false);
                this.modelo.generar_codigo();
                this.vista.CampoNro.setText(this.modelo.solicitud_servicios);
                break;
            case GUARDAR:
                String cliente[] = this.vista.ComboCliente.getSelectedItem().toString().split("-");
//                String timbrado[] = this.vista.ComboTimbrado.getSelectedItem().toString().split("-");
//                String estado[] = this.vista.ComboEstado.getSelectedItem().toString().split("-");
                int cantidadMercaderias = this.vista.tablageneral.getRowCount();
                JTable tabla3 = this.vista.tablageneral;
                if (this.modelo.RegistrarSolicitudServicios(
                        this.vista.CampoNro.getText(),
//                        this.vista.CampoFecha.getText(),
//                        timbrado[3].trim(),
                        this.vista.CampoFecha.getText(),
                        this.vista.txtDescripcion.getText(),
                        cliente[0].trim(),
                        this.vista.CampoCodigoUsuario.getText(),
                        this.modelo.getEstadoActivo()
                )) {
                    
                    for (int i = 0; i < cantidadMercaderias; i++) {
                        String codigoMercadria = tabla3.getValueAt(i, 0).toString();
//                        String descripcion = tabla3.getValueAt(i, 1).toString();
                        String cantidadMercaderia = tabla3.getValueAt(i, 2).toString();
//                        String precioMercaderia = tabla3.getValueAt(i, 3).toString();
                        this.modelo.RegistrarDetalleSolicitudServicios(this.vista.CampoNro.getText(), codigoMercadria, cantidadMercaderia);
                    }
                    JOptionPane.showMessageDialog(vista, "Exito: Nuevo registro agregado.");
//                    this.modelo.Reportes("FacturaVentas", this.vista.tablageneral, this.vista.CampoNro.getText(),
//                           
//                           this.vista.CampoFecha.getText()
//                           );
                  
                } else {
                    JOptionPane.showMessageDialog(vista, "Error: Los datos son incorrectos.");
                }
                deshabilitaBotones();
                limpiar();
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
         
                JTable tabla = this.vista.tablageneral;
//                         String totalgeneral = this.vista.CampoTotal.getText(); txtestado
                 String codigo[] = this.vista.ComboProducto.getSelectedItem().toString().split("-");
                 String descripcion[] = this.vista.ComboProducto.getSelectedItem().toString().split("-");
//                         String precio[] = this.vista.ComboProducto.getSelectedItem().toString().split("-");

                  if (this.modelo.cargarDatos(codigo[0].trim(), this.vista.CampoCantidad.getText().trim(), descripcion[1].trim(),tabla))
                 {
//                             this.vista.CampoTotal.setText(String.valueOf(this.modelo.totales3));
                 }

                  else {
                     JOptionPane.showMessageDialog(vista, "La mercaderia ya existe en el detalle..", "Atención",
                             JOptionPane.WARNING_MESSAGE);
                 }
                 this.vista.BotonEliminar_Detalle.setEnabled(true);
                 this.vista.BotonGuardar.setEnabled(true);

                 break;
            case ELIMINAR_DETALLE:
                JTable tabla2 = this.vista.tablageneral;
                javax.swing.table.DefaultTableModel dtm = (javax.swing.table.DefaultTableModel) tabla2.getModel();
                int indice = tabla2.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado el Detalle a borrar..",
                            "Atención", JOptionPane.WARNING_MESSAGE);
                } else {
                    dtm.removeRow(indice);
                }
                int nro_fila = tabla2.getRowCount();
                if (nro_fila == 0) {
                    this.vista.BotonEliminar_Detalle.setEnabled(false);
                    this.vista.BotonGuardar.setEnabled(false);
                }
                break;
        }
    }

    public void limpiar() {
//        int totales = 0;
     //   this.vista.txtnrofact.setText("");
        this.vista.CampoCantidad.setText("");
        this.vista.txtDescripcion.setText("");
//        this.vista.CampoStock.setText("");
        
//        this.vista.CampoTotal.setText(String.valueOf(totales));
        //limpiar tabla
        this.vista.tablageneral.selectAll();
        int[] L = this.vista.tablageneral.getSelectedRows();
        javax.swing.table.DefaultTableModel dtm =
                (javax.swing.table.DefaultTableModel) this.vista.tablageneral.getModel();
        for (int i = L.length - 1; i >= 0; --i) {
            dtm.removeRow(L[i]);
        }
    }

    private void deshabilitaBotones() {
        this.vista.CampoFecha.setEnabled(false);
        this.vista.CampoNro.setEnabled(false);
        this.vista.txtDescripcion.setEnabled(false);
        this.vista.txtestado.setEnabled(false);
//        this.vista.CampoStock.setEnabled(false);
        this.vista.ComboProducto.setEnabled(false);
        this.vista.ComboCliente.setEnabled(false);
        this.vista.CampoCantidad.setEnabled(false);
        this.vista.BotonAgregar_Detalle.setEnabled(false);
        this.vista.BotonEliminar_Detalle.setEnabled(false);
        this.vista.BotonGuardar.setEnabled(false);
        this.vista.BotonCancelar.setEnabled(false);
        this.vista.BotonAgregar.setEnabled(true);
        this.vista.BotonSalir.setEnabled(true);
//        this.vista.CampoTotal.setEnabled(true);
        limpiar();
    }
}
