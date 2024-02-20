package Controlador;


import Modelo.MUsuario;
import Vista.VUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//se importa modelo e interfaz

public class CUsuario implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    VUsuarios vista ;
    /** instancia a nuestro modelo */
    MUsuario modelo = new MUsuario();

    /** Se declaran en un ENUM las acciones que se realizan desde la
 * interfaz de usuario VISTA y posterior ejecución desde el controlador
 */
    public enum AccionMVC
    {
        VER , 
       GUARDAR ,        
       ELIMINAR ,       
       MODIFICAR,             
        NUEVO,
   IMPRIMIR   
    }

    /** Constrcutor de clase
 * @param vista Instancia de clase interfaz
 */
    public CUsuario( VUsuarios vista )
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
                this.vista.CampoUsuario.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 1)));
               this.vista.CampoContraseña.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 2)));
                this.vista.ComboFuncionario.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 3)));
                this.vista.ComboPermiso.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 4))); 
                
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
        this.vista.VER.setActionCommand( "VER" );
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
         this.vista.ComboFuncionario.setModel(this.modelo.getListaFuncionario());
         this.vista.ComboPermiso.setModel(this.modelo.getListaPermiso());
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaConsulta.addMouseListener(this);
        this.vista.tablaConsulta.setModel( new DefaultTableModel() );
          this.vista.tablaConsulta.setModel( this.modelo.getTablaUsuario() );
                FormateaGrilla();
        
    }
   
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case VER:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tablaConsulta.setModel( this.modelo.getTablaUsuario() );
                FormateaGrilla();
                break;
            case GUARDAR:
                //añade un nuevo registro
                String Fu[] = this.vista.ComboFuncionario.getSelectedItem().toString().split("-");
                 String Pe[] = this.vista.ComboPermiso.getSelectedItem().toString().split("-");
                if ( this.modelo.NuevoUsuario(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoUsuario.getText(),
                        
                        this.vista.CampoContraseña.getText() ,
                       
                        Fu[0].trim() ,
                      
                        Pe[0].trim()    
                        
                        ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaUsuario() );
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
                     if ( this.modelo.EliminarUsuario( this.vista.CampoCodigo.getText() ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaUsuario() );
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                  limpiarCampos();
                }
                else if(ax == JOptionPane.NO_OPTION)
                    cancelar();
                
                
                   break;
            case MODIFICAR:
                String Fu1[] = this.vista.ComboFuncionario.getSelectedItem().toString().split("-");
              
                     String Pe1[] = this.vista.ComboPermiso.getSelectedItem().toString().split("-");
                if (this.modelo.ActualizarUsuario(
                        this.vista.CampoCodigo.getText(),
                         this.vista.CampoUsuario.getText(),
                        this.vista.CampoContraseña.getText(),
                       
                        Fu1[0].trim(),
                        Pe1[0].trim()    
                        )){
                    this.vista.tablaConsulta.setModel(this.modelo.getTablaUsuario());
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
                    modelo.Reportes("InformeUsuario");
                 break;
        }
    }
    
     public void limpiarCampos() {
                  //  this.vista.CampoCodigo.setText("");
                    this.vista.CampoContraseña.setText("") ;
                 this.vista.CampoUsuario.setText("") ;
                      //se debe colocar el laber lbFoto como public
                    this.vista.CampoContraseña.requestFocus();
                  
    }
    public void cancelar() {
                     limpiarCampos();
                     
                     this.modelo.generar_codigo();
                     this.vista.CampoCodigo.setText(this.modelo.idUsuarios);
                     
                     //validacion para no permitir modificar el campo codigo
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
   
            
       
            
    }
}

