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

public class MInsumo extends ConexionBD{
     public ResultSet rs;
     public String idInsumos = "";
     
    /** Constructor de clase */
    public MInsumo (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaInsumo()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Descripcion","Cantidad", "PrecioCompra","PrecioVenta","Marcas","Categorias","Colores","Modelos","Medidas"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM insumos I, marcas M, categorias Ca, colores CO, modelos MO, medidas ME   where I.idMarcas=M.idMarcas and I.idCategorias=Ca.idCategorias  and I.idColores=CO.idColores and I.idModelos=MO.idModelos and I.idMedidas=ME.idMedidas order by I.idInsumos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][10];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM insumos I, marcas M, categorias Ca, colores CO, modelos MO, medidas ME   where I.idMarcas=M.idMarcas and I.idCategorias=Ca.idCategorias  and I.idColores=CO.idColores and I.idModelos=MO.idModelos and I.idMedidas=ME.idMedidas order by I.idInsumos");
         ResultSet res = pstm.executeQuery();                         
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "I.idInsumos" );
                data[i][1] = res.getString(  "I.Descripcion" );
                data[i][2] = res.getString(  "I.cantidad" );
                data[i][3] = res.getString(  "I.precioCompra" );
                data[i][4] = res.getString(  "I.PrecioVenta" );
                data[i][5] = res.getString(  "I.idMarcas" )+" - "+res.getString(  "M.Marca" );
                data[i][6] = res.getString(  "I.idCategorias" )+" - "+res.getString(  "Ca.Categoria" );
                data[i][7] = res.getString(  "I.idColores" )+" - "+res.getString(  "CO.Color" );
                data[i][8] = res.getString(  "I.idModelos" )+" - "+res.getString(  "MO.Modelo" );
                data[i][9] = res.getString(  "I.idMedidas" )+" - "+res.getString(  "ME.Medida" );
               
                
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

    /** Registra un nuevo insumos */
    public boolean NuevoInsumo(String idInsumos, String Descripcion, String Cantidad, String PrecioCompra, String PrecioVenta, String idMarcas, String idCategorias, String idColores, String idModelos, String idMedidas )
    {
         
        if( valida_datos( Descripcion) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO insumos ( idInsumos, Descripcion, Cantidad, PrecioCompra, PrecioVenta, idMarcas, idCategorias, idColores, idModelos, idMedidas  ) "
                    + "VALUES ( '" + idInsumos + "','"+ Descripcion + "','"+ Cantidad + "','"+ PrecioCompra + "','"+ PrecioVenta + "','" + idMarcas + "', '" + idCategorias + "','" + idColores +"','"+idModelos+"','"+ idMedidas +  "' ) ";
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
    public boolean ActualizarInsumo(String idInsumos, String Descripcion, String Cantidad, String PrecioCompra, String PrecioVenta, String idMarcas, String idCategorias, String idColores, String idModelos, String idMedidas){
        String q = "UPDATE insumos SET idInsumos ='" + idInsumos +"',Descripcion ='" + Descripcion +"',Cantidad ='" + Cantidad + "',PrecioCompra ='" + PrecioCompra +"',PrecioVenta ='" + PrecioVenta +"',idMarcas ='" + idMarcas + "',idCategorias ='" + idCategorias + "',idColores ='" +idColores+ "',idModelos ='" + idModelos +  "',idMedidas ='" + idMedidas + "'   WHERE idInsumos ='" + idInsumos + "' ";
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
    public boolean EliminarInsumos( String idInsumos )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM insumos WHERE idInsumos='" + idInsumos+ "' " ;
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
    public void generaridInsumos() {
        try {
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idInsumos) + 1 as id from insumos");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("id") == null) {
               idInsumos = "1";
            } else {
               idInsumos = rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MInsumo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** Metodo privado para validar datos */
    private boolean valida_datos( String Descripcion)
    {
        if( idInsumos.equals("  ") )
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
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM marcas ");
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
    public DefaultComboBoxModel getListaCategorias()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM categorias ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idCategorias" ) + " - " + res.getString( "Categoria" ) );
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
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM insumos");
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
            Logger.getLogger(MInsumo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MInsumo.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
 
}


