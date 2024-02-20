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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;

public class MTarjeta extends ConexionBD{
     public ResultSet rs;
     public String idTarjetas = "";
     
    /** Constructor de clase */
    public MTarjeta (){}  

                            
    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaTarjeta()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Codigo","Nro_Tarjeta","Monto","Fecha_Emision","Fecha_Cobro","TipoTarjetas","Entidades_Emisoras","Clientes"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConnection().prepareStatement( "SELECT count(*) as total FROM tarjetas Ta, TipoTarjetas TI, entidadesemisoras Es,  clientes cl where Ta.idTipoTarjetas=TI.idTipoTarjetas and Ta.idEntidadesEmisoras=Es.idEntidadesEmisoras and Ta.idClientes=cl.idClientes order by idTarjetas");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
         JOptionPane.showMessageDialog(null, e.getMessage());
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][8];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM tarjetas Ta, TipoTarjetas TI , entidadesemisoras Es,  clientes cl where Ta.idTipoTarjetas=TI.idTipoTarjetas and Ta.idEntidadesEmisoras=Es.idEntidadesEmisoras and Ta.idClientes=cl.idClientes order by idTarjetas");
         ResultSet res = pstm.executeQuery();
         int i=0;                            
         while(res.next()){
                data[i][0] = res.getString( "idTarjetas" );
                data[i][1] = res.getString( "Ta.Nro_Tarjeta" );
                data[i][2] = res.getString( "Ta.Monto" );
                
                 java.util.Date dat=res.getDate("Fecha_Emision");
                DateFormat df=new SimpleDateFormat("dd/MM/yyyy");             
                String f=df.format(dat);
                data[i][3] = f;
               
             
                java.util.Date date=res.getDate("Fecha_Cobro");
                DateFormat dg=new SimpleDateFormat("dd/MM/yyyy");             
                String g=dg.format(dat);
                data[i][4] = g;
                
                data[i][5] = res.getString( "Ta.idTipoTarjetas" )+" - "+res.getString(  "Descripcion" );
                data[i][6] = res.getString( "Ta.idEntidadesEmisoras" ) +" - "+res.getString(  "RazonSocial" );
                data[i][7] = res.getString( "Ta.idClientes" )  +" - "+res.getString(  "cl.Nombre" )+" - "+res.getString(  "cl.Apellido" );
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
                                      // idTarjetas Nro_Tarjeta Clientes sexo Fecha_EmisionN Fech_Nac TipoTarjetas Entidades_Emisoras Email idCargos idBarrios
    /** Registra un nuevo cliente */
    public boolean GuardarTarjeta(String idTarjetas, String Nro_Tarjeta, String Monto, Date Fecha_Emision,  Date Fecha_Cobro, String idTipoTarjetas, String idEntidadesEmisoras, String idClientes)
    {
        SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
        
        SimpleDateFormat _sdg= new SimpleDateFormat("yyyy-MM-dd");
        
        if( valida_datos(idTarjetas, Nro_Tarjeta,Monto,Fecha_Emision,Fecha_Cobro) )
        {
          
            //Se arma la consulta
            String q=" INSERT INTO tarjetas ( idTarjetas, Nro_Tarjeta, Monto, Fecha_Emision, Fecha_Cobro, idTipoTarjetas, idEntidadesEmisoras, idClientes ) "
                    + "VALUES ( '" + idTarjetas + "','" + Nro_Tarjeta + "', '"  + Monto + "', '"  + _sdf.format(Fecha_Emision)+ "','"   +_sdf.format(Fecha_Cobro) + "' ,'"  + idTipoTarjetas + "' ,'"  + idEntidadesEmisoras + "' ,'"  +idClientes + "' ) ";
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
    public boolean ModificarTarjeta(String idTarjetas, String Nro_Tarjeta, String Monto, Date Fecha_Emision, Date Fecha_Cobro, String idTipoTarjetas, String idEntidadesEmisoras, String idClientes){
        
         SimpleDateFormat _sdf= new SimpleDateFormat("yyyy-MM-dd");
          SimpleDateFormat _sdg= new SimpleDateFormat("yyyy-MM-dd");
        
        String q = "UPDATE tarjetas SET Nro_Tarjeta ='" + Nro_Tarjeta  + "',Monto ='" + Monto + "',Fecha_Emision ='" + _sdf.format(Fecha_Emision) + "',fecha_Cobro ='"  +_sdf.format(Fecha_Cobro) + "',idTipoTarjetas ='" + idTipoTarjetas + "',idEntidadesEmisoras ='" + idEntidadesEmisoras + "',idClientes ='" + idClientes + "'   WHERE idTarjetas ='" + idTarjetas + "' ";
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
    public boolean EliminarTarjeta( String idTarjetas )
    {
         boolean res=false;
        //se arma la consulta
        String q = " DELETE FROM tarjetas WHERE idTarjetas='" + idTarjetas + "' " ;
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
            PreparedStatement pstm2 = this.getConnection().prepareStatement("select MAX(idTarjetas) + 1 as idTarjetas from tarjetas");
            rs = pstm2.executeQuery();
            rs.next();
            if (rs.getString("idTarjetas") == null) {
                idTarjetas = "1";
            } else {
                idTarjetas = rs.getString("idTarjetas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                   // idTarjetas Nro_Tarjeta Clientes sexo Fecha_EmisionN Fech_Nac TipoTarjetas Entidades_Emisoras Email idCargos idBarrios
  
    /** Metodo privado para validar datos */
    private boolean valida_datos(String idTarjetas, String Nro_Tarjeta, String Monto, Date Fecha_Emision, Date Fecha_Cobro)
    {
        if( idTarjetas.equals("  ") )
            return false;
        else if( Nro_Tarjeta.length()>0)
        {
            return true;
        }
        else return false;
    }
    
    public DefaultComboBoxModel getListaTipoTarjeta()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM tipotarjetas ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idTipoTarjetas" ) + " - " + res.getString( "Descripcion" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    
    public DefaultComboBoxModel getListaEntidadesEmisoras()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM entidadesemisoras ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idEntidadesEmisoras" ) + " - " + res.getString( "RazonSocial" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    public DefaultComboBoxModel getListaClientes()
    {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
         try{
         PreparedStatement pstm = this.getConnection().prepareStatement("SELECT * FROM clientes ");
         ResultSet res = pstm.executeQuery();
         while(res.next()){
                model.addElement( res.getString( "idClientes" ) + " - " + res.getString( "Nombre" )+ " - " + res.getString( "Apellido" ) );
         }
         res.close();
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return model;
    }
    
     public void Reportes(String archivo) {
        try {
            PreparedStatement psta = this.getConnection().prepareStatement("select * FROM tarjetas");
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
            Logger.getLogger(MTarjeta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MTarjeta.class.getName()).log(Level.SEVERE, null, ex);
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

