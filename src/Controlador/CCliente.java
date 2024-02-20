package Controlador;

import Modelo.MCliente;
import Vista.VCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//se importa modelo e interfaz

public class CCliente implements ActionListener,MouseListener, KeyListener{

    /** instancia a nuestra interfaz de usuario*/
    VCliente vista ;
    /** instancia a nuestro modelo */
    MCliente modelo = new MCliente();

 

    /** Se declaran en un ENUM las acciones que se realizan desde la
 * interfaz de usuario VISTA y posterior ejecución desde el controlador
 */
    public enum AccionMVC
    {
        VERCLIENTE , 
        GUARDARCLIENTE ,        
        ELIMINARCLIENTE ,       
        MODIFICARCLIENTE,             
        NUEVOCLIENTE,
        IMPRIMIRCLIENTE,
        CANCELAR
    }

    /** Constrcutor de clase
 * @param vista Instancia de clase interfaz
 */
    public CCliente( VCliente vista )
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
                this.vista.CampoNombre.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 1)));
                this.vista.CampoApellido.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 2)));
                this.vista.CampoCI.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 3)));
                this.vista.CampoDireccion.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 4)));
                this.vista.CampoTelefono.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 5)));
                this.vista.CampoEmail.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 6)));
                this.vista.ComboBarrio.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 7)));
                this.vista.ComboNacionalidad.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 8))); 
                
                //HABILITAR O DESHABILITAR BOTONES
                 this.vista.GUARDARCLIENTE.setEnabled(false);
                 
                 this.vista.MODIFICARCLIENTE.setEnabled(true);
                 this.vista.ELIMINARCLIENTE.setEnabled(true);
                 this.vista.IMPRIMIRCLIENTE.setEnabled(true);
                 this.vista.VERCLIENTE.setEnabled(true);
                 this.vista.NUEVOCLIENTE.setEnabled(true);
                 this.vista.CANCELAR.setEnabled(true);
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
    //esto se agrego para el filtro
     @Override
    public void keyTyped(KeyEvent e) {}
    
   @Override
    public void keyPressed(KeyEvent e) {}
    @Override

    public void keyReleased(KeyEvent e) {
    /*Por cada letra insertada en el campo buscar se envia el contenido
        a la variable :filtro: del MODELO para realizar la consulta
        y obtener los datos*/
        modelo.filtro = this.vista.CampoBuscar1.getText();
        this.vista.tablaConsulta.setModel(modelo.getTablaCliente());
    }

    /** Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar()
    {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
        cancelar();
        habilitarDeshabilitar();
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.NUEVOCLIENTE.setActionCommand( "NUEVOCLIENTE" );
        this.vista.NUEVOCLIENTE.addActionListener(this);
         //declara una acción y añade un escucha al evento producido por el componente
        this.vista.VERCLIENTE.setActionCommand( "VERCLIENTE" );
        this.vista.VERCLIENTE.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.GUARDARCLIENTE.setActionCommand( "GUARDARCLIENTE" );
        this.vista.GUARDARCLIENTE.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.MODIFICARCLIENTE.setActionCommand( "MODIFICARCLIENTE" );
        this.vista.MODIFICARCLIENTE.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.ELIMINARCLIENTE.setActionCommand( "ELIMINARCLIENTE" );
        this.vista.ELIMINARCLIENTE.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.CANCELAR.setActionCommand( "CANCELAR" );
        this.vista.CANCELAR.addActionListener(this);
        // Boton Cancelar Accion
        this.vista.IMPRIMIRCLIENTE.setActionCommand( "IMPRIMIRCLIENTE" );
        this.vista.IMPRIMIRCLIENTE.addActionListener(this);
        //esto EL CAMPO BUSCAR AGREGA LOS EVENTOS DEL TECLADO (addKeyListener)
        this.vista.CampoBuscar1.addKeyListener(this);
        //combo
         this.vista.ComboBarrio.setModel(this.modelo.getListaBarrio());
         this.vista.ComboNacionalidad.setModel(this.modelo.getListaNacionalidad());
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaConsulta.addMouseListener(this);
//        this.vista.tablaConsulta.setModel( new DefaultTableModel() );
//          this.vista.tablaConsulta.setModel( this.modelo.getTablaCliente() );
                FormateaGrilla();
        
    }
   
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case VERCLIENTE:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tablaConsulta.setModel( this.modelo.getTablaCliente() );
                FormateaGrilla();
                break;
            case GUARDARCLIENTE:
                //añade un nuevo registro
                String ciu[] = this.vista.ComboBarrio.getSelectedItem().toString().split("-");
                 String nac[] = this.vista.ComboNacionalidad.getSelectedItem().toString().split("-");
                if ( this.modelo.NuevoCliente(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoNombre.getText() ,
                        this.vista.CampoApellido.getText() ,
                        this.vista.CampoCI.getText(),
                        this.vista.CampoDireccion.getText(),
                        this.vista.CampoTelefono.getText(),
                        this.vista.CampoEmail.getText(),
                        ciu[0].trim() ,
                      
                        nac[0].trim()    
                        
                        ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaCliente() );
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                   limpiarCampos();
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case ELIMINARCLIENTE:
                 int ax = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR EL REGISTRO?");
                if(ax == JOptionPane.YES_OPTION)
                     if ( this.modelo.EliminarCliente( this.vista.CampoCodigo.getText() ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaCliente() );
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                  limpiarCampos();
                }
                else if(ax == JOptionPane.NO_OPTION)
                    cancelar();
                
                
                   break;
            case MODIFICARCLIENTE:
                String ciu1[] = this.vista.ComboBarrio.getSelectedItem().toString().split("-");
              
                     String nac1[] = this.vista.ComboNacionalidad.getSelectedItem().toString().split("-");
                if (this.modelo.ActualizarCliente(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoNombre.getText(),
                        this.vista.CampoApellido.getText(),
                        this.vista.CampoCI.getText(),
                        this.vista.CampoDireccion.getText(),
                        this.vista.CampoTelefono.getText(),
                        this.vista.CampoEmail.getText(),
                        ciu1[0].trim(),
                        nac1[0].trim()    
                        )){
                    this.vista.tablaConsulta.setModel(this.modelo.getTablaCliente());
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(null, "Registro actualizado");
                    limpiarCampos();
                }
                break; 
                
            case NUEVOCLIENTE:   
                    
                  cancelar();
                  habilitarDeshabilitar();
                break;
                case IMPRIMIRCLIENTE:
                    modelo.Reportes("InformeCliente");
                 break;
            case CANCELAR:
                habilitarDeshabilitar();
                limpiarCampos();
                break;
        }
    }
    

    
     public void limpiarCampos() {
        //this.vista.CampoCodigo.setText("");
        this.vista.CampoNombre.setText("") ;
        this.vista.CampoApellido.setText("");
        this.vista.CampoCI.setText("");
        this.vista.CampoDireccion.setText("");
        this.vista.CampoTelefono.setText("") ;
        this.vista.CampoEmail.setText("");
        //se debe colocar el laber lbFoto como public
        this.vista.CampoNombre.requestFocus();
                  
    }
     
    public void cancelar() {
        limpiarCampos();

        this.modelo.generaridClientes();
        this.vista.CampoCodigo.setText(this.modelo.idClientes);
//
//        //validacion para no permitir modificar el campo codigo
        this.vista.CampoCodigo.setEnabled(false);
                  
    }
    
    public void habilitarDeshabilitar() {
        //HABILITAR O DESHABILITAR BOTONES
        this.vista.ELIMINARCLIENTE.setEnabled(false);
        this.vista.MODIFICARCLIENTE.setEnabled(false);
        this.vista.GUARDARCLIENTE.setEnabled(true);
        this.vista.IMPRIMIRCLIENTE.setEnabled(true);
        this.vista.VERCLIENTE.setEnabled(true);
        this.vista.NUEVOCLIENTE.setEnabled(true);
        this.vista.CANCELAR.setEnabled(true);
        this.vista.SALIR.setEnabled(true);
   
    }
    public void FormateaGrilla(){
            this.vista.tablaConsulta.getColumnModel().getColumn(0).setPreferredWidth(70);
            this.vista.tablaConsulta.getColumnModel().getColumn(1).setPreferredWidth(200);
            this.vista.tablaConsulta.getColumnModel().getColumn(2).setPreferredWidth(150);
            this.vista.tablaConsulta.getColumnModel().getColumn(3).setPreferredWidth(100);
           this.vista.tablaConsulta.getColumnModel().getColumn(4).setPreferredWidth(150);
            this.vista.tablaConsulta.getColumnModel().getColumn(5).setPreferredWidth(200);
            this.vista.tablaConsulta.getColumnModel().getColumn(6).setPreferredWidth(200);
            this.vista.tablaConsulta.getColumnModel().getColumn(7).setPreferredWidth(150);
            this.vista.tablaConsulta.getColumnModel().getColumn(8).setPreferredWidth(150);
           
       
            
    }
}

