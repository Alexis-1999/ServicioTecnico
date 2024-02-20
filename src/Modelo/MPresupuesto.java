package Modelo;


import Vista.VPresupuesto;
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

public class MPresupuesto extends ConexionBD {

    public ResultSet rs;
    VPresupuesto vista;
    public int totales3 = 0;
    public String presupuesto = "";
 
    public boolean Registrarpresupuesto(String idPresupuestos, String Fecha,String Total,String idUsuarios, String idFuncionarios, String idRecepciones) {
         String q = " insert into presupuestos( idPresupuestos, Fecha,Total,idUsuarios, idFuncionarios,idRecepciones )"
                    + "values( '" + idPresupuestos + "', '" + Fecha + "', '" + Total + "', '" + idUsuarios + "', '" + idFuncionarios + "','" +idRecepciones + "')";
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

    public boolean RegistrarDetallepresupuesto(String Presupuestos, String IdtiposServicios, String cantidad ) {
        String q2 = " insert into detallepresupuesto (Presupuestos, IdtiposServicios, cantidad )"
                    + "values('" + Presupuestos + "','" + IdtiposServicios + "','" + cantidad + "' )";
        try {
            
            PreparedStatement pstm2 = this.getConnection().prepareStatement(q2);
            pstm2.execute();
            pstm2.close();
            return true;
        } catch (SQLException e) {
//            System.err.println(e.getMessage());
        }
        return false;
    }

    public void generar_codigo() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idPresupuestos) + 1 as nro from presupuestos");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("nro") == null) {
                presupuesto = "1";
            } else {
                presupuesto = rs.getString("nro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultComboBoxModel getListaFuncionario() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM Funcionarios");
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

    public DefaultComboBoxModel getListaRecepcion() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM funcionarios");
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString(1) + " - " + rs.getString(3) + " - " + rs.getString(9)
                        );
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return model;
    }
    public DefaultComboBoxModel getListaTipoServicios() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM tiposservicios");
            rs = pstm.executeQuery();
            while (rs.next()) {
                model.addElement(rs.getString(1) + " - " + rs.getString(2)+ " - " + rs.getString(3));
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
/*     public void Reportes(String archivo) {
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
