

package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
public class MBuscarProductos extends ConexionBD {
public ResultSet rs;
public String registro;


        //    Obtiene registros de la tabla Lugar y los devuelve en un DefaultTableModel
     public DefaultTableModel getTablaTodo() 
     {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Descripcion","Foto","Cantidad","PrecioVenta"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
          PreparedStatement pstm = this.getConnection().prepareStatement("SELECT count(*) as total FROM insumos i order by i.idInsumos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM insumos i order by i.idInsumos ");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                                data[i][0] = res.getString( "i.idInsumos" );
                                data[i][1] = res.getString( "i.Descripcion" );                                
                                data[i][2] = res.getString( "i.foto" );
                                data[i][3] = res.getString( "i.cantidad" );
                                data[i][4] = res.getString( "i.PrecioVenta" );
                            
                           
            i++;         }
         int nro_fila = tablemodel.getRowCount();
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
           
   

public  String Descripcion= "";

              public DefaultTableModel getTablaNombre() {
                DefaultTableModel tablemodel = new DefaultTableModel();
                int registros = 0;
                String[] columNames = {"Codigo","Descripcion","Foto","Cantidad","PrecioVenta"};
                //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
                //para formar la matriz de datos
                try {
                    PreparedStatement pstm = this.getConnection().prepareStatement("SELECT count(*) as total FROM insumos i where i.Descripcion like '%"+ Descripcion+ "%' order by i.idInsumos");
                                                                                                           
                        
                    ResultSet res = pstm.executeQuery();
                    res.next();
                    registros = res.getInt("total");
                    res.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
                //se crea una matriz con tantas filas y columnas que necesite
                Object[][] data = new String[registros][5];
                try {
                    //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
                    PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM insumos i where i.Descripcion like '%"+ Descripcion+ "%' order by i.idInsumos");
                                                                                                                                                              
                    ResultSet res = pstm.executeQuery();                                                                                                             

                    int i = 0;
                    while (res.next()) {
                        
                                data[i][0] = res.getString( "i.idInsumos" );
                                data[i][1] = res.getString( "i.Descripcion" );                                
                                data[i][2] = res.getString( "i.foto" );
                                data[i][3] = res.getString( "i.cantidad" );
                                data[i][4] = res.getString( "i.PrecioVenta" );
                    }
                    int nro_fila = tablemodel.getRowCount();
                    res.close();
                    //se añade la matriz de datos en el DefaultTableModel
                    tablemodel.setDataVector(data, columNames);
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                   }
        return tablemodel;
    }
  

 }

