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

public class MCliente extends ConexionBD{
     public ResultSet rs;
     public String filtro = "";
     public String idClientes = "";
     
    /** Constructor de clase */
    public MCliente (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaCliente()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Nombre","Apellido","CI","Direccion","Telefono","Email","Barrios","Nacionalidad"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM clientes C, barrios B, Ciudades ci, nacionalidades N where C.idBarrios=B.idBarrios and C.idNacionalidad=N.idNacionalidad and C.Nombre LIKE '%"+ filtro +"%'  order by C.idClientes");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][9];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM clientes C, barrios B, nacionalidades N where C.idBarrios=B.idBarrios and C.idNacionalidad=N.idNacionalidad and C.Nombre LIKE '%"+ filtro +"%'  order by C.idClientes");
         ResultSet res = pstm.executeQuery();                         
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "C.idClientes" );
                data[i][1] = res.getString( "C.Nombre" );
                data[i][2] = res.getString( "C.Apellido" );
                data[i][3] = res.getString( "C.CI" );
                data[i][4] = res.getString( "C.Direccion" ); 
                data[i][5] = res.getString(  "C.Telefono" );
                data[i][6] = res.getString(  "C.Email" );
                //Combo
                data[i][7] = res.getString(  "C.idBarrios" )+" - "+res.getString(  "B.descripcion" );
                data[i][8] = res.getString(  "C.idNacionalidad" )+" - "+res.getString(  "N.descripcion" );
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

    /** Registra un nuevo clientes */
    public boolean NuevoCliente(String idClientes, String Nombre , String Apellido, String CI, String Direccion, String Telefono, String Email, String idBarrios, String idNacionalidad)
    {
         //SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        if( valida_datos(idClientes, Nombre, Apellido, CI, Direccion, Telefono,Email) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO clientes ( idClientes,Nombre,Apellido,CI,Direccion,Telefono,Email,idBarrios, idNacionalidad ) "
                    + "VALUES ( '" + idClientes + "','" + Nombre + "', '" + Apellido + "','" + CI +"','"+Direccion+"','"+ Telefono + "','"+Email+"','" + idBarrios + "','" + idNacionalidad + "' ) ";
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
    public boolean ActualizarCliente(String idClientes, String Nombre , String Apellido, String CI,  String Direccion,String Telefono,String Email, String idBarrios,String idNacionalidad){
        String q = "UPDATE clientes SET Nombre ='" + Nombre + "',Apellido ='" + Apellido + "',CI ='" + CI + "',Direccion ='" +Direccion+ "',Telefono ='" + Telefono + "',Email ='" + Email +"',idBarrios ='" + idBarrios + "',idNacionalidad ='" + idNacionalidad + "'   WHERE idClientes ='" + idClientes + "' ";
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
    public boolean EliminarCliente( String idClientes )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM clientes WHERE idClientes='" + idClientes+ "' " ;
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
    public void generaridClientes() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idClientes) + 1 as id from clientes");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("id") == null) {
               idClientes = "1";
            } else {
               idClientes = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos(String id, String nombre , String apellido, String CI, String Direccion, String Telefono, String Email)
    {
        if( id.equals("  ") )
            return false;
        else if( nombre.length() > 0 && apellido.length()>0 && CI.length() >0 && Direccion.length() >0 && Telefono.length() >0 && Email.length() >0)
        {
            return true;
        }
        else return false;
    }
     public DefaultComboBoxModel getListaBarrio()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM barrios b, ciudades ci where b.idCiudades=ci.idCiudades order by b.idBarrios");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "b.idBarrios" ) + " - " + res.getString( "b.descripcion" )+ " - " + res.getString( "ci.Nombre" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    public DefaultComboBoxModel getListaNacionalidad()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM nacionalidades ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idNacionalidad" ) + " - " + res.getString( "descripcion" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM clientes");
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
            Logger.getLogger(MCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MCliente.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
 
}


