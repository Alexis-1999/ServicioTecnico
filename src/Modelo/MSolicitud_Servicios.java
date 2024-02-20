package Modelo;



import Vista.VSolicitud_Servicios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;


public class MSolicitud_Servicios extends ConexionBD {

    public ResultSet rs;
    VSolicitud_Servicios vista;
    public int totales3 = 0;
   
    public String solicitud_servicios = "";
 
    public boolean RegistrarSolicitudServicios(String nro, String fecha, String descripcion,String cliente, String usuario, int estado) {
    String q = " insert into solicitud_servicios (idSolicitud_Servicios,Fecha, Descripcion_Servicios, idClientes, usuarios, idEstado)"
                + "values( '" + nro+ "','" + fecha + "', '" + descripcion + "', '" + cliente + "', '" + usuario + "', '" + estado + "')";

    try {
        PreparedStatement pstm = this.getConnection().prepareStatement(q);
        pstm.execute();
        pstm.close();
        return true;
    } catch (SQLException e) {
        System.err.println("Error al registrar la solicitud de servicios: " + e.getMessage());
    }
    return false;
    }

    public boolean RegistrarDetalleSolicitudServicios(String nro, String cantidad, String equipos) {
        String q2 = " insert into detalle_solicitud_servicios (idSolicitud_Servicios, Cantidad, idEquipos)"
                    + "values('" + nro + "','" + cantidad + "','" + equipos + "')";
        
//        String q3 =" UPDATE insumos SET cantidad=cantidad-'" + cantidad + "' WHERE idInsumos='"+producto+"' "; ------------------------------------------------------
        try {
            
            PreparedStatement pstm2 = this.getConnection().prepareStatement(q2);
            pstm2.execute();
            pstm2.close();
            
            //actualiza stock           -------------------------------------------------------------------------------------------------------
//            PreparedStatement pstm3 = this.getConnection().prepareStatement(q3);  
//            pstm3.execute();
//            pstm3.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void generar_codigo() {
     try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idSolicitud_Servicios) + 1 as nro from solicitud_servicios");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("nro") == null) {
                solicitud_servicios = "1";
            } else {
                solicitud_servicios = rs.getString("nro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MSolicitud_Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public DefaultComboBoxModel getListaClientes() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM clientes");
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString(1) + " - " + rs.getString(2) );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }
    
    public int getEstadoActivo() {
        int estadoActivo = 0;
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT idEstado FROM Estado WHERE Descripcion_Estado = 'Activo'");
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                estadoActivo = rs.getInt("idEstado");
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return estadoActivo;
    }
    
    public String getDescripcionEstado(int idEstado) {
    String descripcionEstado = null;
    try {
        PreparedStatement pstm = this.getConnection().prepareStatement("SELECT Descripcion_Estado FROM Estado WHERE idEstado = ?");
        pstm.setInt(1, idEstado);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            descripcionEstado = rs.getString(1);
        }
        rs.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    }
    return descripcionEstado;
}

//   public DefaultComboBoxModel getListaTimbrado() {
//        DefaultComboBoxModel model = new DefaultComboBoxModel();
//        try {
//            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM vtimbrados");
//             ResultSet res = pstm.executeQuery();
//         while(res.next()){
//                model.addElement( res.getString( "idTimbrados" )
//                         + " - " + res.getString( "Fecha_Validez" )
//                        );
//         }
//         res.close();
//         }catch(SQLException e){
//            System.err.println( e.getMessage() );
//        }
//        return model;
//    }

    /**
 * Obtiene las categorias del sistema en un DefaultComboBoxModel
 */
    public DefaultComboBoxModel getListaProducto1()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM vequipos");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idEquipos" )
                         + " - " + res.getString( "TipoEquipo" )
                         + " - " + res.getString( "Marca" )
//                         + " - " + res.getString( "Cantidad" )
                        );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }


    public boolean cargarDatos(String codigo, String cantidad, String descripcion, JTable tabla) {
        //Para darle tamaño a las columnas de la tabla..
        String c = tabla.getColumnName(0);
        TableColumn tc = tabla.getColumn(c);
        tc.setPreferredWidth(50);
        String d = tabla.getColumnName(1);
        TableColumn td = tabla.getColumn(d);
        td.setPreferredWidth(50);
        //captura el formato de la tabla seteada
        javax.swing.table.DefaultTableModel tableModel = (javax.swing.table.DefaultTableModel) tabla.getModel();

//        String total = "";
//        int total2 = 0;

//        int precio2 = Integer.parseInt(precio);
//        int cantidad2 = Integer.parseInt(cantidad);
//        total2 = precio2 * cantidad2;
//        total = String.valueOf(total2);

        int nro_fila = tableModel.getRowCount();
        if (nro_fila > 0) {
            for (int i = 0; i < nro_fila; i++) {
                String cod = (tableModel.getValueAt(i, 0).toString());
                if (cod.equals(codigo)) {
                    return false;
                }
            }
        }

        //agrega a las filas los valores de las variables al JTable
        tableModel.addRow(new Object[]{codigo, descripcion, cantidad});
//        int totales2 = Integer.parseInt(totales);
//        totales3 = total2 + totales2;
        return true;
    }
    
    
//    public void Reportes(String archivo, JTable tabla, String codigo, String fecha) { 
//        try {
//            javax.swing.table.DefaultTableModel dtml =
//                ( javax.swing.table.DefaultTableModel) tabla.getModel();
//            JRTableModelDataSource jrtmd = new JRTableModelDataSource(dtml);
//            //Pasamos parametros para el Jasper
//            Map parametros = new HashMap();
//            parametros.put("rs", rs);
//            parametros.put("Nro", codigo);
//            parametros.put("Fecha", fecha);
////            parametros.put("Total", total);
//             
//            //Preparaciòn del reporte
//            String dir = System.getProperty("user.dir") + "\\Reportes\\";
//            JasperFillManager.fillReportToFile(dir + archivo+".jasper", parametros, jrtmd);
//           //se muestra por pantalla el reporte generado
//            JasperViewer ventana = new JasperViewer(dir + archivo + ".jrprint", false, false);
//            ventana.setTitle("Vista previa");
//            ventana.setVisible(true); 
//         
//        } catch (JRException ex) {
//            Logger.getLogger(MFacturaVenta.class.getName()).log(Level.SEVERE, null, ex);       
//        }
//
//    }


}

