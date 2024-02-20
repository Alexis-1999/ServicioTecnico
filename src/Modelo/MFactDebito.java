package Modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import net.sf.jasperreports.view.JasperViewer;

public class MFactDebito extends ConexionBD {

    public ResultSet rs;
   MFactDebito vista;
    public int totales3 = 0;
    public String NotaDebitoCompra = "";
 
    public boolean RegistrarNotaCompra(String idNotaDebitoCompra, String Fecha,String idFacturaCompras,String idUsuarios, String idProveedores) {
         String q = " insert into notadebitocompra ( idNotaDebitoCompra, Fecha, idFacturaCompras, idUsuarios, idProveedores )"
                    + "values( '" + idNotaDebitoCompra + "','" + Fecha + "','" + idFacturaCompras + "','" + idUsuarios + "','" + idProveedores +"')";
        try {
           
            PreparedStatement pstm = this.getConnection().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean RegistrarDetalleNotaCompra(String idNotaDebitoCompra, String cantidad, String idInsumos, String subtotal ) {
        String q2 = " insert into detallefacturascompra (idNotaDebitoCompra, cantidad, idInsumos,subtotal  )"
                    + "values('" + idNotaDebitoCompra + "','" + cantidad + "','" + idInsumos +"','" + subtotal + "' )";
        try {
            
            PreparedStatement pstm2 = this.getConnection().prepareStatement(q2);
            pstm2.execute();
            pstm2.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void generar_codigo() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idNotaDebitoCompra) + 1 as nro from notadebitocompra");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("nro") == null) {
                NotaDebitoCompra = "1";
            } else {
                NotaDebitoCompra = rs.getString("nro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MFactDebito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultComboBoxModel getListaProveedor() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM proveedores");
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString(1) + " - " + rs.getString(2) + " " + rs.getString(3));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }

    public DefaultComboBoxModel getListaInsumos() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM insumos");
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString(1) + " - " + rs.getString(2) + " - " + rs.getString(4)
                        );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }
     public DefaultComboBoxModel getListaFacturaCompra() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM facturacompras");
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString(1));
                        
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
        td.setPreferredWidth(300);
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
/*    public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement(this.mod;
            ResultSet rss = psta.executeQuery();
            //Pasamos parametros para el Jasper
            JRResultSetDataSource jRs = new JRResultSetDataSource(rss);
            Map parametros = new HashMap();

            parametros.put("rs", rss);

            //Preparaciòn del trabajo
            String dir = System.getProperty("user.dir") + "\\Reporte\\";
            JasperFillManager.fillReportToFile(dir + archivo+".jasper", parametros, jRs);

            JasperViewer ventana = new JasperViewer(dir + archivo + ".jrprint", false, false);
            ventana.setTitle("Vista previa");
            ventana.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Mmarcas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mmarcas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
*/
}
