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

public class MFuncionarios1 extends ConexionBD{
     public ResultSet rs;
     public String idFuncionarios = "";
      public String filtro = "";
    /** Constructor de clase */
    public MFuncionarios1 (){}  

                            
    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaFuncionario()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Nombre","Apellido","CI","fechNac","Direccion","Telefono","Salario"," Sexo","Email","Cargos","Barrios"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM funcionarios f, barrios b, cargos c where f.idBarrios=b.idBarrios and f.idCargos=c.idCargos order by f.idFuncionarios");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][12];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM funcionarios f, barrios b, cargos c where f.idBarrios=b.idBarrios and f.idCargos=c.idCargos order by f.idFuncionarios");
         ResultSet res = pstm.executeQuery();
         int i=0;                            
         while(res.next()){
                data[i][0] = res.getString( "f.idFuncionarios" );
                data[i][1] = res.getString( "f.Nombre" );
                data[i][2] = res.getString( "f.Apellido" );
                data[i][3] = res.getString( "f.CI" );
               
             
                java.util.Date dat=res.getDate("f.fechaNac");
                DateFormat df=new SimpleDateFormat("dd/MM/yyyy");             
                String f=df.format(dat);
                data[i][4] = f;
                
                  data[i][5] = res.getString( "f.Direccion" );
                  data[i][6] = res.getString( "f.Telefono" );
                  data[i][7] = res.getString( "f.Salario" );
                  data[i][8] = res.getString( "f.Sexo" );
                  data[i][9] = res.getString( "f.Email" );
                  data[i][10] = res.getString(  "f.idCargos" )+" - "+res.getString(  "c.Descripcion" );
                  data[i][11] = res.getString(  "f.idBarrios" )+" - "+res.getString(  "b.Descripcion" );
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
     public DefaultTableModel getTablaBusqueda()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Nombre","Apellido","CI","fechNac","Direccion","Telefono","Salario"," Sexo","Email","Cargos","Barrios"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM funcionarios f, barrios b, cargos c WHERE f.idBarrios=b.idBarrios and f.idCargos=c.idCargos and f.Nombre LIKE '%"+ filtro +"%' ");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][12];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM funcionarios f, barrios b, cargos c WHERE f.idBarrios=b.idBarrios and f.idCargos=c.idCargos and f.Nombre LIKE '%"+ filtro +"%' ORDER BY idfuncionarios");
         ResultSet res = pstm.executeQuery();
         int i=0;                                                                                                                                                                  
         while(res.next()){
               data[i][0] = res.getString( "f.idFuncionarios" );
                data[i][1] = res.getString( "f.Nombre" );
                data[i][2] = res.getString( "f.Apellido" );
                data[i][3] = res.getString( "f.CI" );
               
             
                java.util.Date dat=res.getDate("f.fechaNac");
                DateFormat df=new SimpleDateFormat("dd/MM/yyyy");             
                String f=df.format(dat);
                data[i][4] = f;
                
                  data[i][5] = res.getString( "f.Direccion" );
                  data[i][6] = res.getString( "f.Telefono" );
                  data[i][7] = res.getString( "f.Salario" );
                  data[i][8] = res.getString( "f.Sexo" );
                  data[i][9] = res.getString( "f.Email" );
                  data[i][10] = res.getString(  "f.idCargos" )+" - "+res.getString(  "c.Descripcion" );
                  data[i][11] = res.getString(  "f.idBarrios" )+" - "+res.getString(  "b.Descripcion" );
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

                                   
    /** Registra un nuevo cliente */
    public boolean GuardarFuncionario(String idFuncionarios, String Nombre, String Apellido, String CI,  Date fechaNac, String Direccion,String Telefono, String Salario, String Sexo,String Email,String idCargos,String idBarrios)
    {
        SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        
        if( valida_datos(idFuncionarios, Nombre,Apellido,CI,fechaNac,Direccion,Telefono, Salario,Sexo,Email ) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO funcionarios ( idFuncionarios,Nombre, Apellido,CI, fechaNac ,Direccion ,Telefono, Salario,Sexo,Email,idCargos,idBarrios ) "
                    + "VALUES ( '" + idFuncionarios + "','" + Nombre + "', '"  + Apellido + "', '"  + CI + "','"   +_sdf.format(fechaNac) + "' ,'"  + Direccion + "' ,'"  + Telefono + "' ,'"  +Salario +"' ,'"  +Sexo +"' ,'"  +Email + "' ,'"  + idCargos + "' , '"  + idBarrios + "' ) ";
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
    public boolean ModificarFuncionario(String idFuncionarios, String Nombre, String Apellido, String CI,  Date fechaNac, String Direccion,String Telefono, String Salario, String Sexo,String Email,String idCargos,String idBarrios){
        
         SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        
        String q = "UPDATE funcionarios SET Nombre ='" + Nombre  + "',Apellido ='" + Apellido + "',CI ='" + CI + "',fechaNac ='"  +_sdf.format(fechaNac) + "',Direccion ='" + Direccion + "',Telefono ='" + Telefono + "',Salario ='" + Salario + "',Sexo ='" + Sexo + "',Email ='" + Email + "',idCargos ='" + idCargos + "',idBarrios ='" + idBarrios + "'   WHERE idFuncionarios ='" + idFuncionarios + "' ";
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
    public boolean EliminarFuncionario( String idFuncionarios )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM funcionarios WHERE idFuncionarios='" + idFuncionarios + "' " ;
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
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idFuncionarios) + 1 as idFuncionarios from funcionarios");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("idFuncionarios") == null) {
                idFuncionarios = "1";
            } else {
                idFuncionarios = rs.getString("idFuncionarios");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MFuncionarios1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                   // idFuncionarios Nombre Salario sexo CIN Fech_Nac Direccion Telefono Email idCargos idBarrios
  
    /** Metodo privado para validar datos */
    private boolean valida_datos(String idFuncionarios, String Nombre, String Apellido, String CI,  Date fechaNac, String Direccion,String Telefono, String Salario, String Sexo,String Email)
    {
        if( idFuncionarios.equals("  ") )
            return false;
        else if( Nombre.length()>0)
        {
            return true;
        }
        else return false;
    }
    
    public DefaultComboBoxModel getListaBarrios()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM barrios ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idBarrios" ) + " - " + res.getString( "Descripcion" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    
    public DefaultComboBoxModel getListaCargos()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM cargos ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idCargos" ) + " - " + res.getString( "Descripcion" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    
     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM funcionarios");
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
            Logger.getLogger(MFuncionarios1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MFuncionarios1.class.getName()).log(Level.SEVERE, null, ex);
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

