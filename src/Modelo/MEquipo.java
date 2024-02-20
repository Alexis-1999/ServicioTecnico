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

public class MEquipo extends ConexionBD{
     public ResultSet rs;
     public String idEquipos = "";
     
    /** Constructor de clase */
    public MEquipo (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaEquipo()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Marcas","TiposEquipos","Medidas","Colores","Modelos","Descripcion"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM equipos E, marcas M, tiposequipos TE, medidas ME, colores CO, modelos MO where E.idMarcas=M.idMarcas and E.idTiposEquipos=TE.idTiposEquipos and E.idMedidas=ME.idMedidas and E.idColores=CO.idColores and E.idModelos=MO.idModelos order by E.idEquipos");
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
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM equipos E, marcas M, tiposequipos TE, medidas ME, colores CO, modelos MO where E.idMarcas=M.idMarcas and E.idTiposEquipos=TE.idTiposEquipos and E.idMedidas=ME.idMedidas and E.idColores=CO.idColores and E.idModelos=MO.idModelos order by E.idEquipos");
         ResultSet res = pstm.executeQuery();                         
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "E.idEquipos" );
                data[i][1] = res.getString(  "E.idMarcas" )+" - "+res.getString(  "M.Marca" );
                data[i][2] = res.getString(  "E.idTiposEquipos" )+" - "+res.getString(  "TE.Descripcion" );
                data[i][3] = res.getString(  "E.idMedidas" )+" - "+res.getString(  "ME.Medida" );
                data[i][4] = res.getString(  "E.idColores" )+" - "+res.getString(  "CO.Color" );
                data[i][5] = res.getString(  "E.idModelos" )+" - "+res.getString(  "MO.Modelo" );
                data[i][6] = res.getString(  "E.Descripcion" );
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

    /** Registra un nuevo equipos */
    public boolean NuevoEquipo(String idEquipos, String Descripcion, String idMarcas, String idTiposEquipos, String idMedidas, String idColores, String idModelos )
    {
         //SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        if( valida_datos( Descripcion) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO equipos ( idEquipos,Descripcion, idMarcas, idTiposEquipos, idMedidas, idColores, idModelos  ) "
                    + "VALUES ( '" + idEquipos + "','"+ Descripcion + "','" + idMarcas + "', '" + idTiposEquipos + "','" + idMedidas +"','"+idColores+"','"+ idModelos +  "' ) ";
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
    public boolean ActualizarEquipo(String idEquipos, String Descripcion, String idMarcas, String idTiposEquipos, String idMedidas, String idColores, String idModelos){
        String q = "UPDATE equipos SET idEquipos ='" + idEquipos +"',Descripcion ='" + Descripcion + "',idMarcas ='" + idMarcas + "',idTiposEquipos ='" + idTiposEquipos + "',idMedidas ='" +idMedidas+ "',idColores ='" + idColores +  "',idModelos ='" + idModelos + "'   WHERE idEquipos ='" + idEquipos + "' ";
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
    public boolean EliminarCliente( String idEquipos )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM equipos WHERE idEquipos='" + idEquipos+ "' " ;
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
    public void generaridEquipos() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idEquipos) + 1 as id from equipos");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("id") == null) {
               idEquipos = "1";
            } else {
               idEquipos = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MEquipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos( String Descripcion)
    {
        if( idEquipos.equals("  ") )
            return false;
        else if( Descripcion.length() > 0)
        {
            return true;
        }
        else return false;
    }
     public DefaultComboBoxModel getListaMarca()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM marcas order by idMarcas");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idMarcas" ) + " - " + res.getString( "Marca" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    public DefaultComboBoxModel getListaTiposEquipos()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM tiposequipos ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idTiposEquipos" ) + " - " + res.getString( "Descripcion" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
     public DefaultComboBoxModel getListaMedidas()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM medidas ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idMedidas" ) + " - " + res.getString( "Medida" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
         public DefaultComboBoxModel getListaColores()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM colores ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idColores" ) + " - " + res.getString( "Color" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
             public DefaultComboBoxModel getListaModelos()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM modelos ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idModelos" ) + " - " + res.getString( "Modelo" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
     
     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM equipos");
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
            Logger.getLogger(MEquipo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MEquipo.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
 
}


