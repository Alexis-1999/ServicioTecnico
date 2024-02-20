package Modelo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

public class MEntidadesEmisoras extends ConexionBD{
     public ResultSet rs;
     public String idEntidadesEmisoras = "";
     
    /** Constructor de clase */
    public MEntidadesEmisoras (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel tablaConsulta()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","RazonSocial","Ruc","Direccion","Telefono","Email"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM entidadesEmisoras  order by idEntidadesEmisoras");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();                                                                      
        }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][6];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM entidadesEmisoras   order by idEntidadesEmisoras");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "idEntidadesEmisoras" );
                data[i][1] = res.getString( "RazonSocial" );
                data[i][2] = res.getString( "Ruc" );
                data[i][3] = res.getString( "Direccion" );
                data[i][4] = res.getString( "Telefono" );
                data[i][5] = res.getString( "Email" );
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

    /** Registra un nuevo entidadesEmisoras */
    public boolean Guardar(String idEntidadesEmisoras, String RazonSocial, String Ruc, String Direccion,String Telefono,String Email )
    {
        if( valida_datos(idEntidadesEmisoras, RazonSocial,Ruc,Direccion,Telefono,Email ) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO entidadesEmisoras ( idEntidadesEmisoras,RazonSocial,Ruc,Direccion,Telefono,Email ) "
                    + "VALUES ( '" + idEntidadesEmisoras + "','" + RazonSocial + "','" + Ruc + "','" +  Direccion + "','" +  Telefono + "','" +  Email + "') ";
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
    public boolean Modificar(String idEntidadesEmisoras,String RazonSocial,String Ruc,String Direccion,String Telefono,String Email ){
  
                   String q = "UPDATE entidadesEmisoras SET RazonSocial ='" + RazonSocial + "',Ruc='" + Ruc +"',Direccion='" + Direccion + "',Telefono='" + Telefono + "',Email='" + Email +"' WHERE idEntidadesEmisoras ='" + idEntidadesEmisoras + "' ";
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
    public boolean Eliminar( String idEntidadesEmisoras )
            
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM entidadesEmisoras WHERE idEntidadesEmisoras='" + idEntidadesEmisoras + "' " ;
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
    public void generar_idEntidadesEmisoras() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idEntidadesEmisoras) + 1 as id from entidadesEmisoras");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("id") == null) {
               idEntidadesEmisoras = "1";
            } else {
                idEntidadesEmisoras = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MEntidadesEmisoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos(String idEntidadesEmisoras, String RazonSocial,String Ruc,String Direccion,String Telefono,String Email) 
    {
        if( idEntidadesEmisoras.equals("  ") )
            return false;
        else if( RazonSocial.length() > 0 &&  Ruc.length() > 0 &&  Direccion.length() > 0 &&  Telefono.length() > 0 &&  Email.length() > 0)
        {
            return true;
        }
        else return false;
    }
    

     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM entidadesEmisoras order by idEntidadesEmisoras");
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
            Logger.getLogger(MEntidadesEmisoras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MEntidadesEmisoras.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

    private boolean valida_datos(String idEntidadesEmisoras, String RazonSocial, String Ruc, String entidadesEmisoras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


 
}


