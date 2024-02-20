package Controlador;


import Modelo.MEquipo;
import Vista.VEquipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//se importa modelo e interfaz

public class CEquipo implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    VEquipo vista ;
    /** instancia a nuestro modelo */
    MEquipo modelo = new MEquipo();

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
   IMPRIMIR ,
   SALIR
    
    }

    /** Constrcutor de clase
 * @param vista Instancia de clase interfaz
 */
    public CEquipo( VEquipo vista )
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
                  this.vista.ComboMarca.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 1)));
                this.vista.ComboTipoEquipo.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 2)));
                  this.vista.ComboMedida.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 3)));
                this.vista.ComboColor.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 4)));
                  this.vista.ComboModelo.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 5)));
            
                this.vista.CampoDescripcion.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 6)));
          
                
                
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
         this.vista.ComboMarca.setModel(this.modelo.getListaMarca());
         this.vista.ComboTipoEquipo.setModel(this.modelo.getListaTiposEquipos());
          this.vista.ComboMedida.setModel(this.modelo.getListaMedidas());
          this.vista.ComboColor.setModel(this.modelo.getListaColores());
          this.vista.ComboModelo.setModel(this.modelo.getListaModelos());
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaConsulta.addMouseListener(this);
        this.vista.tablaConsulta.setModel( new DefaultTableModel() );
          this.vista.tablaConsulta.setModel( this.modelo.getTablaEquipo() );
                FormateaGrilla();
        
    }
   
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
        case VER:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tablaConsulta.setModel( this.modelo.getTablaEquipo() );
                FormateaGrilla();
                break;
            case GUARDAR:
                //añade un nuevo registro
                String Ma[] = this.vista.ComboMarca.getSelectedItem().toString().split("-");
                 String Te[] = this.vista.ComboTipoEquipo.getSelectedItem().toString().split("-");
                 String Me[] = this.vista.ComboMedida.getSelectedItem().toString().split("-");
                String Co[] = this.vista.ComboColor.getSelectedItem().toString().split("-");
                 String Mo[] = this.vista.ComboModelo.getSelectedItem().toString().split("-");
                if ( this.modelo.NuevoEquipo(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoDescripcion.getText(),
                        
                        
                        Ma[0].trim() ,
                         Te[0].trim() ,
                       Me[0].trim() ,
                        Co[0].trim() ,
                        Mo[0].trim()    
                        
                        ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaEquipo() );
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
                     if ( this.modelo.EliminarCliente( this.vista.CampoCodigo.getText() ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaEquipo() );
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                  limpiarCampos();
                }
                else if(ax == JOptionPane.NO_OPTION)
                    cancelar();
                
                
                   break;
            case MODIFICAR:
               String Ma1[] = this.vista.ComboMarca.getSelectedItem().toString().split("-");
                String Te1[] = this.vista.ComboTipoEquipo.getSelectedItem().toString().split("-");
                String Me1[] = this.vista.ComboMedida.getSelectedItem().toString().split("-");
                 String Co1[] = this.vista.ComboColor.getSelectedItem().toString().split("-");
                  String Mo1[] = this.vista.ComboModelo.getSelectedItem().toString().split("-");
                if (this.modelo.ActualizarEquipo(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoDescripcion.getText(),
                       
                        Ma1[0].trim(),
                        Te1[0].trim(),
                         Me1[0].trim(),
                          Co1[0].trim(), 
                           Mo1[0].trim() 
                          
                        )){
                    this.vista.tablaConsulta.setModel(this.modelo.getTablaEquipo());
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
                    modelo.Reportes("Informeequipos");
                 break;
        }
    }
    
     public void limpiarCampos() {
                    //this.vista.CampoCodigo.setText("");
                    this.vista.CampoDescripcion.setText("") ;
                  
                      //se debe colocar el laber lbFoto como public
                    this.vista.CampoDescripcion.requestFocus();
                  
    }
    public void cancelar() {
                     limpiarCampos();
                     
                     this.modelo.generaridEquipos();
                     this.vista.CampoCodigo.setText(this.modelo.idEquipos);
                     
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
            this.vista.tablaConsulta.getColumnModel().getColumn(3).setPreferredWidth(70);
            this.vista.tablaConsulta.getColumnModel().getColumn(4).setPreferredWidth(200);
            this.vista.tablaConsulta.getColumnModel().getColumn(5).setPreferredWidth(150);
             this.vista.tablaConsulta.getColumnModel().getColumn(6).setPreferredWidth(150);
       
            
    }
}

