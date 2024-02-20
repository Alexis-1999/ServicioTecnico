package Controlador;


import Modelo.MMotivoAjuste;
import Vista.VMotivoAjuste;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//se importa modelo e interfaz

public class CMotivoAjuste implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    VMotivoAjuste vista ;
    /** instancia a nuestro modelo */
    MMotivoAjuste modelo = new MMotivoAjuste();

    /** Se declaran en un ENUM las acciones que se realizan desde la
 * interfaz de usuario VISTA y posterior ejecución desde el controlador
 */
    public enum AccionMVC
    {
         BotonVer, 
         BotonGuardar,         
         BotonEliminar,          
         BotonModificar,             
         BotonCancelar,
         BotonImprimir   
    }

    /** Constrcutor de clase
 * @param vista Instancia de clase interfaz
 */
    public CMotivoAjuste( VMotivoAjuste vista )
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
                this.vista.CampoDescripcion.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 1)));
              
                 
                this.vista.CampoCodigo.setEnabled(false);
                
                //HABILITAR O DESHABILITAR BOTONES
                 this.vista.BotonGuardar.setEnabled(false);                 
                 this.vista.BotonModificar.setEnabled(true);
                 this.vista.BotonEliminar.setEnabled(true);
                 this.vista.BotonImprimir.setEnabled(true);
                 this.vista.BotonVer.setEnabled(true);
                 this.vista.BotonCancelar.setEnabled(true);
                 this.vista.BotonSalir.setEnabled(true);
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
         //HABILITAR O DESHABILITAR BOTONES
        this.vista.BotonEliminar.setEnabled(false);
        this.vista.BotonModificar.setEnabled(false);

        this.vista.BotonGuardar.setEnabled(true);
        this.vista.BotonImprimir.setEnabled(true);
        this.vista.BotonVer.setEnabled(true);
        this.vista.BotonCancelar.setEnabled(true);
        this.vista.BotonSalir.setEnabled(true);
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.BotonCancelar.setActionCommand( "BotonCancelar" );
        this.vista.BotonCancelar.addActionListener(this);
         //declara una acción y añade un escucha al evento producido por el componente
        this.vista.BotonVer.setActionCommand( "BotonVer" );
        this.vista.BotonVer.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.BotonGuardar.setActionCommand( "BotonGuardar" );
        this.vista.BotonGuardar.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.BotonModificar.setActionCommand( "BotonModificar" );
        this.vista.BotonModificar.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.BotonEliminar.setActionCommand( "BotonEliminar" );
        this.vista.BotonEliminar.addActionListener(this);
       //declara una acción y añade un escucha al evento producido por el componente
        this.vista.BotonImprimir.setActionCommand( "BotonImprimir" );
        this.vista.BotonImprimir.addActionListener(this);
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaConsulta.addMouseListener(this);
        this.vista.tablaConsulta.setModel( new DefaultTableModel() );
     
    }
   
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case BotonVer:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tablaConsulta.setModel( this.modelo.tablaConsulta() );
                break;
            case BotonGuardar:
                //añade un nuevo registro
              
                if ( this.modelo.Guardar(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoDescripcion.getText() 
                        ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.tablaConsulta() );
                    JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                   limpiarCampos();
                }
                else //ocurrio un error
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                break;
            case BotonEliminar:
                if ( this.modelo.Eliminar( this.vista.CampoCodigo.getText() ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.tablaConsulta() );
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                  limpiarCampos();
                }
                   break;
            case BotonModificar:
             
                if (this.modelo.Modificar(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoDescripcion.getText()
                        )){
                    this.vista.tablaConsulta.setModel(this.modelo.tablaConsulta());
                    JOptionPane.showMessageDialog(null, "Registro actualizado");
                    limpiarCampos();
                }
                break; 
                
                 case BotonCancelar:   
                    cancelar();
                     //HABILITAR O DESHABILITAR BOTONES
                    this.vista.BotonEliminar.setEnabled(false);
                    this.vista.BotonModificar.setEnabled(false);

                    this.vista.BotonGuardar.setEnabled(true);
                    this.vista.BotonImprimir.setEnabled(true);
                    this.vista.BotonVer.setEnabled(true);
                    this.vista.BotonCancelar.setEnabled(true);
                    this.vista.BotonSalir.setEnabled(true);
                break;
                case BotonImprimir:
                    modelo.Reportes("InformeNacionalidad");
                 break;
        }
    }
    
     public void limpiarCampos() {
                 //   this.vista.CampoCodigo.setText("");
                    this.vista.CampoDescripcion.setText("") ;
                    this.vista.CampoDescripcion.requestFocus();
                  
    }
    public void cancelar() {
                    limpiarCampos();
                     
                     this.modelo.generar_idMotivoAjuste();
                     this.vista.CampoCodigo.setText(this.modelo.Cod);
                     
                     //validacion para no permitir modificar el campo codigo
                     this.vista.CampoCodigo.setEnabled(false);
                  
    }

}

