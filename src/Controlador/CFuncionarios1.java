package Controlador;




import Modelo.MFuncionarios1;
import Vista.VFuncionario1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//se importa modelo e interfaz

public class CFuncionarios1 implements ActionListener,MouseListener,KeyListener{

    /** instancia a nuestra interfaz de usuario*/
    VFuncionario1 vista ;
    /** instancia a nuestro modelo */
    MFuncionarios1 modelo = new MFuncionarios1();
    private int fila;

    /** Se declaran en un ENUM las acciones que se realizan desde la
 * interfaz de usuario VISTA y posterior ejecución desde el controlador
 */
    public enum AccionMVC
    {
         VER, 
         GUARDAR,         
         ELIMINAR,        
         MODIFICAR,          
         NUEVO,
        // _SELECCIONAR_FOTO,
         IMPRIMIR  
    }

    /** Constrcutor de clase
 * @param vista Instancia de clase interfaz
 */
    public CFuncionarios1( VFuncionario1 vista )
    {
        this.vista = vista;
    }
  //EVENTOS DEL RATON SOBRE LOS COMPONENTES
    @Override                                
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
//            int fila = this.vista.tablaConsulta.rowAtPoint(e.getPoint());
            if (fila > -7) {
                this.vista.CampoCodigo.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 0)));
                this.vista.CampoNombre.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 1)));
                this.vista.CampoApellido.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 2)));
               this.vista.CampoCI.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 3)));
                this.vista.CampoFecha.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 4)));
                this.vista.CampoDireccion.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 5)));
                this.vista.CampoTelefono.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 6)));
                this.vista.CampoSalario.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 7)));
                this.vista.CampoSexo.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 8)));
                this.vista.CampoEmail.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 9)));
                this.vista.ComboCargo.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 10))); 
                this.vista.ComboBarrio.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 11))); 
                
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
   
    public void keyTyped(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e) {}
 public void keyReleased(KeyEvent e) {
    /*Por cada letra insertada en el campo buscar se envia el contenido
        a la variable :filtro: del MODELO para realizar la consulta
        y obtener los datos*/
        modelo.filtro = this.vista.CampoBuscar.getText();
        this.vista.tablaConsulta.setModel(modelo.getTablaBusqueda());
    
    

   }
 
 /* Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar ()
    {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    
        
        cancelar();
       habilitarDeshabilitar();
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.NUEVO.setActionCommand( "NUEVO" );
        this.vista.NUEVO.addActionListener(this);
         //declara una acción y añade un escucha al evento producido por el componente
       // this.vista._VER_CIUDAD.setActionCommand( "_VER_CIUDAD" );
       // this.vista._VER_CIUDAD.addActionListener(this);
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
   
        this.vista.CampoBuscar.addKeyListener(this);
       //combo
        
        this.vista.ComboCargo.setModel(this.modelo.getListaCargos());
         this.vista.ComboBarrio.setModel(this.modelo.getListaBarrios());
         
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaConsulta.addMouseListener(this);
        this.vista.tablaConsulta.setModel( new DefaultTableModel() );
          this.vista.tablaConsulta.setModel( this.modelo.getTablaFuncionario());
                FormateaGrilla();
        
    }
   
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case VER:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tablaConsulta.setModel( this.modelo.getTablaFuncionario());
                FormateaGrilla();
                break;             
            case GUARDAR:
                //añade un nuevo registro
                String ca[] = this.vista.ComboCargo.getSelectedItem().toString().split("-");
                String ba[] = this.vista.ComboBarrio.getSelectedItem().toString().split("-");
                if ( this.modelo.GuardarFuncionario(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoNombre.getText() ,
                        this.vista.CampoApellido.getText() ,
                        this.vista.CampoCI.getText() ,
                       this.modelo.formatearFecha(this.vista.CampoFecha.getText()),
                       this.vista.CampoDireccion.getText() ,
                        this.vista.CampoTelefono.getText() ,
                         this.vista.CampoSalario.getText() ,
                          this.vista.CampoSexo.getText() ,
                        this.vista.CampoEmail.getText() ,
                        ca[0].trim(),
                        ba[0].trim()
                        
                        ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaFuncionario());
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                   cancelar();
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case ELIMINAR:
                 int ax = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR EL REGISTRO?");
                if(ax == JOptionPane.YES_OPTION)
                     if ( this.modelo.EliminarFuncionario(this.vista.CampoCodigo.getText() ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaFuncionario());
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                  cancelar();
                }
                else if(ax == JOptionPane.NO_OPTION)
                    cancelar();
                
                
                   break;
            case MODIFICAR:
                     String ca1[] = this.vista.ComboCargo.getSelectedItem().toString().split("-");
                     String ba1[] = this.vista.ComboBarrio.getSelectedItem().toString().split("-");
                if (this.modelo.ModificarFuncionario(
                         this.vista.CampoCodigo.getText(),
                        this.vista.CampoNombre.getText() ,
                        this.vista.CampoApellido.getText() ,
                        this.vista.CampoCI.getText() ,
                       this.modelo.formatearFecha(this.vista.CampoFecha.getText()),
                       this.vista.CampoDireccion.getText() ,
                        this.vista.CampoTelefono.getText() ,
                         this.vista.CampoSalario.getText() ,
                          this.vista.CampoSexo.getText() ,
                        this.vista.CampoEmail.getText() ,
                      ca1[0].trim(),
                      ba1[0].trim()
                        )){
                    this.vista.tablaConsulta.setModel(this.modelo.getTablaFuncionario());
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    cancelar();
                }
                break; 
                
                 case NUEVO:   
                    
                  cancelar();
                  
                break;
                case IMPRIMIR:
                    modelo.Reportes("InformeFuncionarios");
                 break;
        }
    }                            // idFuncionarios Nombre Salario sexo CIN Fech_Nac Direccion Telefono Email idCargos idBarrios
    
     public void limpiarCampos() {
                  //  this.vista.CampoCodigo.setText("");
                    this.vista.CampoNombre.setText("") ;
                    this.vista.CampoApellido.setText("") ;
                   this.vista.CampoCI.setText("") ;
                    this.vista.CampoFecha.setText(( this.modelo.getFechaActual()));
                    this.vista.CampoDireccion.setText("") ;
                    this.vista.CampoTelefono.setText("") ;
                     this.vista.CampoSalario.setText("") ;
                      this.vista.CampoSexo.setText("") ;
                    this.vista.CampoEmail.setText("") ;
                    
                    this.vista.CampoNombre.requestFocus();
                  
    }
    public void cancelar() {
                     limpiarCampos();
                     
                     this.modelo.generar_codigo();
                     this.vista.CampoCodigo.setText(this.modelo.idFuncionarios);
                     
                     //validacion para no permitir modificar el campo codigo
                     this.vista.CampoCodigo.setEnabled(false);
                     habilitarDeshabilitar();
                  
    }
     public void habilitarDeshabilitar() {
                //HABILITAR O DESHABILITAR BOTONES
        this.vista.ELIMINAR.setEnabled(false);
        this.vista.MODIFICAR.setEnabled(false);

        this.vista.GUARDAR.setEnabled(true);
        this.vista.IMPRIMIR.setEnabled(true);
      //  this.vista._VER_CIUDAD.setEnabled(true);
        this.vista.NUEVO.setEnabled(true);
        this.vista.SALIR.setEnabled(true);
   
    }
    public void FormateaGrilla(){
            this.vista.tablaConsulta.getColumnModel().getColumn(0).setPreferredWidth(40);
            this.vista.tablaConsulta.getColumnModel().getColumn(1).setPreferredWidth(150);
            this.vista.tablaConsulta.getColumnModel().getColumn(2).setPreferredWidth(150);
             this.vista.tablaConsulta.getColumnModel().getColumn(3).setPreferredWidth(150);  
             this.vista.tablaConsulta.getColumnModel().getColumn(4).setPreferredWidth(100);
    }
}



              