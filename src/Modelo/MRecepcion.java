package Modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

public class MRecepcion extends ConexionBD{
     public ResultSet rs;
     public String idRecepciones = "";
     
    /** Constructor de clase */
    public MRecepcion (){}  

                            
    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaRecepcion()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Descripcon","Fecha","Usuario","Equipo"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM recepciones R, usuarios U,tiposequipos T where  R.idUsuarios=U.idUsuarios and R.idTiposEquipos=T.idTiposEquipos  order by R.idRecepciones");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM recepciones R, usuarios U, tiposequipos T where  R.idUsuarios=U.idUsuarios and R.idTiposEquipos=T.idTiposEquipos  order by R.idRecepciones");
         ResultSet res = pstm.executeQuery();
         int i=0;                            
         while(res.next()){
                data[i][0] = res.getString( "R.idRecepciones" );
               data[i][1] = res.getString( "R.Descripcion" );
           
                java.util.Date dat=res.getDate("R.fecha");
                DateFormat df=new SimpleDateFormat("dd/MM/yyyy");             
                String f=df.format(dat);
                data[i][2] = f;
                
                data[i][3] = res.getString(  "R.idUsuarios" )+" - "+(  "U.usuarios");
                data[i][4] = res.getString(  "R.idTiposEquipos" )+" - "+res.getString(  "T.Descripcion" );
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
                                      
 
    public boolean GuardarRecepcion(String idRecepciones, String Descripcion, Date fecha,  String idUsuarios, String idTiposEquipos)
    {
        SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        
        if( valida_datos(idRecepciones,Descripcion ) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO recepciones( idRecepciones, Descripcion,fecha, idUsuarios, idTiposEquipos ) "
                    + "VALUES ( '" + idRecepciones + "' ,'"  + Descripcion + "','"   +_sdf.format(fecha) + "' ,'"   + idUsuarios +"' ,'"    + idTiposEquipos + "' ) ";
            //se ejecuta la consulta
            try {                PreparedStatement pstm = this.getConnection().prepareStatement(q);
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
    public boolean ModificarRecepcion(String idRecepciones, String Descripcion, Date fecha, String idUsuarios,String idTiposEquipos){
        
         SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        
        String q = "UPDATE recepciones  SET fecha ='"  +_sdf.format(fecha) + "',Descripcion ='" + Descripcion + "',idUsuarios ='" + idUsuarios  + "',idTiposEquipos ='" + idTiposEquipos + "'   WHERE idRecepciones ='" + idRecepciones + "' ";
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
    public boolean EliminarRecepcion( String idRecepciones )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM recepciones WHERE idRecepciones='" + idRecepciones + "' " ;
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
    public void generar_codigo() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idRecepciones) + 1 as idRecepciones from recepciones");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("idRecepciones") == null) {
                idRecepciones = "1";
            } else {
                idRecepciones = rs.getString("idRecepciones");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                   
  
   
    private boolean valida_datos(String idRecepciones,String Descripcion)
    {
        if( idRecepciones.equals("  ") )
            return false;
        else return Descripcion.length() > 0;
    }
    
    
    //Combos
    
    
     
    
    public DefaultComboBoxModel getListaUsuario()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM usuarios ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idusuarios" ) + " - " + res.getString( "usuario" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    
    
  
    
    public DefaultComboBoxModel getListaEquipo()
  
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
    
     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM recepciones");
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
            Logger.getLogger(MRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MRecepcion.class.getName()).log(Level.SEVERE, null, ex);
        } 

 
}
         //---------------------------------------------------------------------
   
    public String getFechaActual() {        
        SimpleDateFormat formateadorf = new SimpleDateFormat("dd/MM/yyyy");       
        return formateadorf.format(new Date());
    }

    public String getHoraActual() {
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(new Date());
    }
  
 
      
    public  Date formatearFecha(String fecha){
		
		String[] bits=fecha.split("/");
		Calendar c=Calendar.getInstance();
		c.set(Integer.parseInt(bits[2]), Integer.parseInt(bits[1])-1, Integer.parseInt(bits[0]));
		
		return c.getTime();
	}

 //---------------------------------------------------------------------

   
  
}

