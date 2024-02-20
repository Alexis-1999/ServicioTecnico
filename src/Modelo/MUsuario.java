

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

public class MUsuario extends ConexionBD{
     public ResultSet rs;
     public String idUsuarios = "";
     
    /** Constructor de clase */
    public MUsuario (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaUsuario()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo", "Usuario","Contraseña","Funcionario","Permiso"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM usuarios u, funcionarios f, permisos p where u.idFuncionarios=f.idFuncionarios and u.idPermisos=p.idPermisos  order by u.idUsuarios");
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
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM usuarios u, funcionarios f, permisos p where u.idFuncionarios=f.idFuncionarios and u.idPermisos=p.idPermisos  order by u.idUsuarios");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "u.idUsuarios" );
                 data[i][1] = res.getString( "u.usuario" );
                data[i][2] = res.getString( "u.contrasenha" );
               //combo
                data[i][3] = res.getString(  "u.idFuncionarios" )+" - "+res.getString(  "f.Nombre" )+" - "+res.getString(  "f.Apellido" );
                data[i][4] = res.getString(  "u.idPermisos" )+" - "+res.getString(  "p.NivelAcceso" );
                
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
    public boolean NuevoUsuario(String idUsuarios,String usuario, String Contrasenha, String idFuncionarios, String idPermisos)
    {
        if( valida_datos(idUsuarios,Contrasenha  ) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO usuarios ( idUsuarios,usuario,Contrasenha,idFuncionarios,idPermisos ) "
                    + "VALUES ( '" +idUsuarios+ "','"  +usuario+ "','"  +Contrasenha+ "','" +idFuncionarios+ "','" +idPermisos+ "' ) ";
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
    public boolean ActualizarUsuario(String idUsuarios, String usuario, String Contrasenha,String idFuncionarios,String idPermisos){
        String q = "UPDATE usuarios SET usuario'" + usuario + "',Contrasenha ='" + Contrasenha  +  "',idFuncionarios ='" +idFuncionarios+ "',idPermisos='" +idPermisos+  "'  WHERE  idUsuarios ='" + idUsuarios + "' ";
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
    public boolean EliminarUsuario( String idUsuarios )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM usuarios WHERE idUsuarios='" + idUsuarios + "' " ;
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
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idUsuarios) + 1 as idUsuarios from usuarios");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("idUsuarios") == null) {
                idUsuarios = "1";
            } else {
                idUsuarios = rs.getString("idUsuarios");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos(String idUsuarios, String Contrasenha )
    {
        if( idUsuarios.equals("  ") )
            return false;
        else if( Contrasenha.length()>0)
        {
            return true;
        }
        else return false;
    }
    
    public DefaultComboBoxModel getListaFuncionario()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM funcionarios ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idFuncionarios" ) + " - " + res.getString( "Nombre" ) + " - " + res.getString( "Apellido" ));
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }

    
    public DefaultComboBoxModel getListaPermiso()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM permisos ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idPermisos" ) + " - " + res.getString( "NivelAcceso" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
          public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM usuarios");
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
            Logger.getLogger(MUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } 

 
}

}
