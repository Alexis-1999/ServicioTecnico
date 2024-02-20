package Controlador;




import Modelo.MProveedor;
import Vista.VProveedor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//se importa modelo e interfaz

public class CProveedor implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    VProveedor vista ;
    /** instancia a nuestro modelo */
    MProveedor modelo = new MProveedor();

    /** Se declaran en un ENUM las acciones que se realizan desde la
 * interfaz de usuario VISTA y posterior ejecución desde el controlador
 */
    public enum AccionMVC
    {
      VER , 
     GUARDAR ,        
     ELIMINAR,       
     MODIFICAR,             
     NUEVO,
     SALIR,
     IMPRIMIR   
    }

    /** Constrcutor de clase
 * @param vista Instancia de clase interfaz
 */
    public CProveedor( VProveedor vista )
    {
        this.vista = vista;
    }
  //EVENTOS DEL RATON SOBRE LOS COMPONENTES
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.vista.tablaConsulta.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vista.CampoCodigo.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 0)));
                this.vista.CampoRazonSocial.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 1)));
                  this.vista.CampoDireccion.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 2)));
                  this.vista.CampoRuc.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 3)));
                    this.vista.CampoTelefono.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 4)));
                this.vista.ComboBarrio.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 5)));
                
                
                //HABILITAR O DESHABILITAR BOTONES
                 this.vista.GUARDAR.setEnabled(false);
                 
                 this.vista.MODIFICAR.setEnabled(true);
                 this.vista.ELIMINAR.setEnabled(true);
                 this.vista.IMPRIMIR.setEnabled(true);
                 this.vista.VER.setEnabled(true);
                 this.vista.NUEVO.setEnabled(true);
                 this.vista.SALIR.setEnabled(true);
                
                this.vista.CampoCodigo.setEnabled(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) { }
    
    public void jBImagenFotoActionPerformed(java.awt.event.ActionEvent evt) {
   
    }

    /** Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar()
    {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
        cancelar();
       habilitarDeshabilitar();
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.NUEVO.setActionCommand( "NUEVO" );
        this.vista.NUEVO.addActionListener(this);
         //declara una acción y añade un escucha al evento producido por el componente
        this.vista.VER.setActionCommand( "_VER" );
        this.vista.VER.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.GUARDAR.setActionCommand( "GUARDAR" );
        this.vista.GUARDAR.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.MODIFICAR.setActionCommand( "MODIFICAR" );
        this.vista.MODIFICAR.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.ELIMINAR.setActionCommand( "ELIMINAR" );
        this.vista.ELIMINAR.addActionListener(this);
    //declara una acción y añade un escucha al evento producido por el componente
        this.vista.IMPRIMIR.setActionCommand( "IMPRIMIR" );
        this.vista.IMPRIMIR.addActionListener(this);
       //combo
         this.vista.ComboBarrio.setModel(this.modelo.getListaBarrio());
     
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaConsulta.addMouseListener(this);
        this.vista.tablaConsulta.setModel( new DefaultTableModel() );
          this.vista.tablaConsulta.setModel( this.modelo.getTablaConsulta() );
                FormateaGrilla();
        
    }
   
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
        case VER :
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tablaConsulta.setModel( this.modelo.getTablaConsulta() );
                FormateaGrilla();
                break;
            case GUARDAR:
                //añade un nuevo registro
                
                String Ba[] = this.vista.ComboBarrio.getSelectedItem().toString().split("-");
   
                if ( this.modelo.NuevoProveedor(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoRazonSocial.getText(),
                        this.vista.CampoDireccion.getText(),
                        this.vista.CampoRuc.getText(),
                        this.vista.CampoTelefono.getText(),
                        this.vista.CampoEmail.getText(),
                        
                        Ba[0].trim()
                           
                        
                        ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaConsulta() );
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                   limpiarCampos();
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case ELIMINAR:
                 int ax = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR EL REGISTRO?");
                if(ax == JOptionPane.YES_OPTION)
                     if ( this.modelo.EliminarProveedor( this.vista.CampoCodigo.getText() ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaConsulta() );
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                  limpiarCampos();
                }
                else if(ax == JOptionPane.NO_OPTION)
                    cancelar();
                
                
                   break;
            case MODIFICAR:
               
                String Ba1[] = this.vista.ComboBarrio.getSelectedItem().toString().split("-");
              
                if (this.modelo.ActualizarProveedor(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoRazonSocial.getText(),
                        this.vista.CampoDireccion.getText(),
                        this.vista.CampoRuc.getText(),
                        this.vista.CampoTelefono.getText(),
                          this.vista.CampoEmail.getText(),
                        
                        Ba1[0].trim()
                          
                        )){
                    this.vista.tablaConsulta.setModel(this.modelo.getTablaConsulta());
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(null, "Registro actualizado");
                    limpiarCampos();
                }
                break; 
                
            case NUEVO:   
                    
                  cancelar();
                  habilitarDeshabilitar();
                break;
                case IMPRIMIR:
                    modelo.Reportes("InformeBarrio");
                 break;
        }
    }
    
     public void limpiarCampos() {
                   //this.vista.CampoCodigo.setText("");
                    
                      //se debe colocar el laber lbFoto como public
                    this.vista.CampoRazonSocial.requestFocus();
                   
                        this.vista.CampoDireccion.setText("");
                        this.vista.CampoRuc.setText("");
                        this.vista.CampoTelefono.setText("");
                        this.vista.CampoEmail.setText("");
                    
                  
    }
    public void cancelar() {
                     limpiarCampos();
                     
                     this.modelo.GeneraridProveedores();
                     this.vista.CampoCodigo.setText(this.modelo.idProveedores);
                     
                     //validacion para no permitir modificar el campo idBarrios
                     this.vista.CampoCodigo.setEnabled(false);
                  
    }
     public void habilitarDeshabilitar() {
                //HABILITAR O DESHABILITAR BOTONES
        this.vista.ELIMINAR.setEnabled(false);
        this.vista.MODIFICAR.setEnabled(false);
        this.vista.GUARDAR.setEnabled(true);
        this.vista.IMPRIMIR.setEnabled(true);
        this.vista.VER.setEnabled(true);
        this.vista.NUEVO.setEnabled(true);
        this.vista.SALIR.setEnabled(true);
   
    }
    public void FormateaGrilla(){
            this.vista.tablaConsulta.getColumnModel().getColumn(0).setPreferredWidth(70);
            this.vista.tablaConsulta.getColumnModel().getColumn(1).setPreferredWidth(200);
          
          this.vista.tablaConsulta.getColumnModel().getColumn(2).setPreferredWidth(150);
          ;
    }
}

