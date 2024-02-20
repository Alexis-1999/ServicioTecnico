package Modelo;



import Vista.VFacturaVenta;
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


public class MFacturaVenta extends ConexionBD {

    public ResultSet rs;
    VFacturaVenta vista;
    public int totales3 = 0;
   
    public String Nro_Factura = "";
 
    public boolean RegistrarVenta(String nro,String idTimbrado, String fecha, String cliente, String usuario) {
         String q = " insert into factservicios (NumFactura,idTimbrados, Fecha, idClientes, usuarios)"
                    + "values( '" + nro+ "','" + idTimbrado + "', '" + fecha + "', '" + cliente + "', '" + usuario + "')";
         
         //String q3 =" UPDATE detalle_timbrado SET situacion='Inactivo' WHERE secuencia='"+idTimbrado+"' ";
        try {
           
            PreparedStatement pstm = this.getConnection().prepareStatement(q);
            pstm.execute();
            pstm.close();
            
           // PreparedStatement pstm3 = this.getConnection().prepareStatement(q3);
           // pstm3.execute();
           // pstm3.close();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean RegistrarDetalleVenta(String nro, String producto, String cantidad, String precio) {
        String q2 = " insert into detalle_ventas (NumFactura, IdtiposServicios, cantidad, precio_venta)"
                    + "values('" + nro + "','" + producto + "','" + cantidad + "','" + precio + "')";
        
        String q3 =" UPDATE insumos SET cantidad=cantidad-'" + cantidad + "' WHERE idInsumos='"+producto+"' ";
        try {
            
            PreparedStatement pstm2 = this.getConnection().prepareStatement(q2);
            pstm2.execute();
            pstm2.close();
            
            //actualiza stock           
            PreparedStatement pstm3 = this.getConnection().prepareStatement(q3);  
            pstm3.execute();
            pstm3.close();

            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void generar_codigo() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(NumFactura) + 1 as nro from factservicios");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("nro") == null) {
                Nro_Factura = "1";
            } else {
                Nro_Factura = rs.getString("nro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MFacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
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

   public DefaultComboBoxModel getListaTimbrado() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM vtimbrados");
             ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idTimbrados" )
                         + " - " + res.getString( "Fecha_Validez" )
                        );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }

    /**
 * Obtiene las categorias del sistema en un DefaultComboBoxModel
 */
    public DefaultComboBoxModel getListaProducto1()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM vinsumos");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idInsumos" )
                         + " - " + res.getString( "Descripcion" )
                         + " - " + res.getString( "PrecioVenta" )
                         + " - " + res.getString( "Cantidad" )
                        );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }


    public boolean cargarDatos(String codigo, String descripcion, String precio, String cantidad, JTable tabla, String totales) {
        //Para darle tamaño a las columnas de la tabla..
        String c = tabla.getColumnName(0);
        TableColumn tc = tabla.getColumn(c);
        tc.setPreferredWidth(50);
        String d = tabla.getColumnName(1);
        TableColumn td = tabla.getColumn(d);
        td.setPreferredWidth(50);
        //captura el formato de la tabla seteada
        javax.swing.table.DefaultTableModel tableModel = (javax.swing.table.DefaultTableModel) tabla.getModel();

        String total = "";
        int total2 = 0;

        int precio2 = Integer.parseInt(precio);
        int cantidad2 = Integer.parseInt(cantidad);
        total2 = precio2 * cantidad2;
        total = String.valueOf(total2);

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
        tableModel.addRow(new Object[]{codigo, descripcion, precio, cantidad, total});
        int totales2 = Integer.parseInt(totales);
        totales3 = total2 + totales2;
        return true;
    }
    
    
    public void Reportes(String archivo, JTable tabla, String codigo, String fecha,String total) {
        try {
            javax.swing.table.DefaultTableModel dtml =
                ( javax.swing.table.DefaultTableModel) tabla.getModel();
            JRTableModelDataSource jrtmd = new JRTableModelDataSource(dtml);
            //Pasamos parametros para el Jasper
            Map parametros = new HashMap();
            parametros.put("rs", rs);
            parametros.put("Nro", codigo);
            parametros.put("Fecha", fecha);
            parametros.put("Total", total);
             
            //Preparaciòn del reporte
            String dir = System.getProperty("user.dir") + "\\Reportes\\";
            JasperFillManager.fillReportToFile(dir + archivo+".jasper", parametros, jrtmd);
           //se muestra por pantalla el reporte generado
            JasperViewer ventana = new JasperViewer(dir + archivo + ".jrprint", false, false);
            ventana.setTitle("Vista previa");
            ventana.setVisible(true); 
         
        } catch (JRException ex) {
            Logger.getLogger(MFacturaVenta.class.getName()).log(Level.SEVERE, null, ex);       
        }

    }

}
