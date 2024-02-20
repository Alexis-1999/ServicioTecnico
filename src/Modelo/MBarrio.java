package Modelo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

public class MBarrio extends ConexionBD{
     public ResultSet rs;
     public String idBarrios = "";
      public String filtro = "";
    /** Constructor de clase */
    public MBarrio (){}

    // PARA LA BUSQUEDA EL FILTRO
 public DefaultTableModel gettablaConsulta()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Descripcion","Ciudad"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM barrios B, ciudades Ci WHERE  B.idCiudades=Ci.idCiudades and B.Descripcion LIKE '%"+ filtro +"%' ");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][3];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM barrios B, ciudades Ci WHERE  B.idCiudades=Ci.idCiudades and B.Descripcion LIKE '%"+ filtro +"%' ORDER BY idBarrios");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "B.idBarrios" );
                data[i][1] = res.getString( "B.Descripcion" );
                data[i][2] = res.getString(  "B.idCiudades" )+" - "+res.getString(  "Ci.nombre" );
               
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

    /** Registra un nuevo barrios */
    public boolean NuevoBarrio(String idBarrios, String Descripcion , String idCiudades)
    {
         //SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        if( valida_datos(idBarrios, Descripcion) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO barrios (idBarrios,Descripcion,idCiudades ) "
                    + "VALUES ( '" + idBarrios + "','" + Descripcion +"','" + idCiudades +"'  ) ";
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
    public boolean ActualizarBarrio(String idBarrios, String Descripcion ,String idCiudades ){
        String q = "UPDATE barrios SET Descripcion ='" + Descripcion +  "',idCiudades ='" + idCiudades +  "'   WHERE idBarrios ='" + idBarrios + "' ";
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
    public boolean EliminarBarrio( String idBarrios )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM barrios WHERE idBarrios='" + idBarrios + "' " ;
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
    public void generaridBarrios() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idBarrios) + 1 as id from barrios");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("id") == null) {
               idBarrios = "1";
            } else {
              idBarrios = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MBarrio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos(String idBarrios, String Descripcion )
    {
        if( idBarrios.equals("  ") )
            return false;
        else if( Descripcion.length() > 0)
        {
            return true;
        }
        else return false;
    }

    public DefaultComboBoxModel getListaciudades()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM ciudades ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idCiudades" ) + " - " + res.getString( "Nombre" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM barrios");
            ResultSet rss = psta.executeQuery();
            //Pasamos parametros para el Jasper
            JRResultSetDataSource jRs = new JRResultSetDataSource(rss);
            Map parametros = new HashMap();
           
            parametros.put("rs", rss);
             
            //Preparaciòn del trabajo
            String dir = System.getProperty("user.dir") + "\\Reportes\\";
            JasperFillManager.fillReportToFile(dir + archivo+".jasper", parametros, jRs);

            JasperViewer ventana = new JasperViewer(dir + archivo + ".jrprint", false, false);
            ventana.setTitle("Vista previa");
            ventana.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(MBarrio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MBarrio.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }

    private boolean valida_datos(String idBarrios, String Descripcion, String ciudades) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
 
}


