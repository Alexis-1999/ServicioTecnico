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

public class MCategoria extends ConexionBD{
     public ResultSet rs;
     public String Cod = "";
     
    /** Constructor de clase */
    public MCategoria (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel tablaConsulta()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Descripcion"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM categorias  order by idCategorias");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][2];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM categorias   order by idCategorias");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "idCategorias" );
                data[i][1] = res.getString( "descripcion" );
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

    /** Registra un nuevo categorias */
    public boolean Guardar(String idCategorias, String descripcion )
    {
        if( valida_datos(idCategorias, descripcion) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO categorias ( idCategorias,descripcion ) "
                    + "VALUES ( '" + idCategorias + "','" + descripcion + "' ) ";
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
    public boolean Modificar(String idCategorias, String descripcion ){
        String q = "UPDATE categorias SET descripcion ='" + descripcion + "'    WHERE idCategorias ='" + idCategorias + "' ";
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
    public boolean Eliminar( String idCategorias )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM categorias WHERE idCategorias='" + idCategorias + "' " ;
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
    public void generar_idCategorias() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idCategorias) + 1 as id from categorias");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("id") == null) {
                Cod = "1";
            } else {
                Cod = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos(String idCategorias, String descripcion)
    {
        if( idCategorias.equals("  ") )
            return false;
        else if( descripcion.length() > 0 )
        {
            return true;
        }
        else return false;
    }
    

     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM categorias order by idCategorias");
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
            Logger.getLogger(MCategoria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MCategoria.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }


 
}


