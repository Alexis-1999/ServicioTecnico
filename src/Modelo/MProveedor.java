package Modelo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

public class MProveedor extends ConexionBD{
     public ResultSet rs;
     public String idProveedores = "";
     
    /** Constructor de clase */
    public MProveedor (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaConsulta()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","RazonSocial","Direccion","Ruc","Telefono","Email","Barrios"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM proveedores p,"+" barrios B where p.idBarrios=B.idBarrios order by p.idProveedores");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][7];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM proveedores p,"+" barrios B where p.idBarrios=B.idBarrios order by p.idProveedores");
         ResultSet res = pstm.executeQuery();                         
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "p.idProveedores" );
                data[i][1] = res.getString( "p.RazonSocial" );
                data[i][2] = res.getString( "p.Direccion" );
                data[i][3] = res.getString( "p.Ruc" );
                data[i][4] = res.getString( "p.Telefono" );
                data[i][5] = res.getString( "p.Email" );
                data[i][6] = res.getString( "p.idBarrios" )+" - "+res.getString(  "B.descripcion" );
               // data[i][8] = res.getString(  "Ci.ciudades" )+" - "+res.getString(  "Ci.descripcion" );
               
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }

    /** Registra un nuevo proveedores */
    public boolean NuevoProveedor(String idProveedoreses, String RazonSocial , String Direccion, String Ruc, String Telefono, String Email, String idBarrios )
    {
         //SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        if( valida_datos(idProveedoreses, RazonSocial, Direccion, Ruc,Telefono, Email) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO proveedores ( idProveedores,RazonSocial,Direccion,Ruc,telefono,Email,idBarrios ) "
                    + "VALUES ( '" +  idProveedores+ "','" + RazonSocial + "', '" + Direccion + "','" + Telefono +"','" + Ruc +"','"+ Email + "','" + idBarrios + "') ";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConnection().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            return false;
        }
        else
         return false;
    }
    public boolean ActualizarProveedor(String  idProveedores, String RazonSocial , String Direccion, String Ruc, String Telefono, String Email, String idBarrios ){
        String q = "UPDATE proveedores SET RazonSocial ='" + RazonSocial + "',Direccion ='" + Direccion + "',Ruc ='" + Ruc + "',Telefono ='" + Telefono + "',Email ='" + Email + "',idBarrios ='" +idBarrios + "'   WHERE idProveedores ='" + idProveedores+ "' ";
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return false;
    }


    /** Elimina un registro dado su ID -> Llave primaria */
    public boolean EliminarProveedor( String idProveedores )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM proveedores WHERE idProveedores='" + idProveedores + "' " ;
        //se ejecuta la consulta
         try {
            PreparedStatement pstm = this.getConnection().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;
    }
    public void GeneraridProveedores() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idProveedores) + 1 as id from proveedores");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("id") == null) {
               idProveedores = "1";
            } else {
              idProveedores = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos(String idProveedores, String RazonSocial , String Direccion, String Ruc, String Telefono, String Email )
    {                              
        if( idProveedores.equals("  ") )
            return false;
        else if( RazonSocial.length() > 0 && Direccion.length()>0 && Ruc.length()>0 && Telefono.length()>0 && Email.length()> 0)
        {
            return true;
        }
        else return false;
    }
     public DefaultComboBoxModel getListaBarrio()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM barrios order by idBarrios");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idBarrios" ) + " - " + res.getString( "descripcion" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
   
     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM proveedores");
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
            Logger.getLogger(MProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
 
}


