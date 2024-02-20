package Controlador;





import Modelo.MTarjeta;
import Vista.VTarjeta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//se importa modelo e interfaz

public class CTarjeta implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    VTarjeta vista ;
    /** instancia a nuestro modelo */
    MTarjeta modelo = new MTarjeta();

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
    public CTarjeta( VTarjeta vista )
    {
        this.vista = vista;
    }
  //EVENTOS DEL RATON SOBRE LOS COMPONENTES
    @Override                                
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.vista.tablaConsulta.rowAtPoint(e.getPoint());
            if (fila > -7) {
                this.vista.CampoCodigo.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 0)));
                this.vista.CampoNroTarjeta.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 1)));
                this.vista.CampoMonto.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 2)));
               this.vista.CampoFechaEmision.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 3)));
                this.vista.CampoFechaCobro.setText(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 4)));
                
                this.vista.ComboTipotarjeta.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 5))); 
                this.vista.ComboEntidadEmisora.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 6))); 
                this.vista.ComboCliente.setSelectedItem(String.valueOf(this.vista.tablaConsulta.getValueAt(fila, 7))); 
                
                //HABILITAR O DESHABILITAR BOTONES
                 this.vista.GUARDAR.setEnabled(false);
                 
                 this.vista.MODIFICAR.setEnabled(true);
                 this.vista.ELIMINAR.setEnabled(true);
                 this.vista.IMPRIMIR.setEnabled(true);
                // this.vista._VER_CIUDAD.setEnabled(true);
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
       //combo
        
        this.vista.ComboTipotarjeta.setModel(this.modelo.getListaTipoTarjeta());
         this.vista.ComboEntidadEmisora.setModel(this.modelo.getListaEntidadesEmisoras());
          this.vista.ComboCliente.setModel(this.modelo.getListaClientes());
         
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaConsulta.addMouseListener(this);
        this.vista.tablaConsulta.setModel( new DefaultTableModel() );
          this.vista.tablaConsulta.setModel( this.modelo.getTablaTarjeta());
                FormateaGrilla();
        
    }
   
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case VER:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vista.tablaConsulta.setModel( this.modelo.getTablaTarjeta());
                FormateaGrilla();
                break;             
            case GUARDAR:
                //añade un nuevo registro
                String Ti[] = this.vista.ComboTipotarjeta.getSelectedItem().toString().split("-");
                String En[] = this.vista.ComboEntidadEmisora.getSelectedItem().toString().split("-");
                String cl[] = this.vista.ComboCliente.getSelectedItem().toString().split("-");
                if ( this.modelo.GuardarTarjeta(
                        this.vista.CampoCodigo.getText(),
                        this.vista.CampoNroTarjeta.getText() ,
                        this.vista.CampoMonto.getText() ,
                       this.modelo.formatearFecha(this.vista.CampoFechaEmision.getText()),
                       this.modelo.formatearFecha(this.vista.CampoFechaCobro.getText()),
                     Ti[0].trim(),
                     En[0].trim(),
                     cl[0].trim()
                        ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaTarjeta());
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
                     if ( this.modelo.EliminarTarjeta(this.vista.CampoCodigo.getText() ) )
                {
                    this.vista.tablaConsulta.setModel( this.modelo.getTablaTarjeta());
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                  cancelar();
                }
                else if(ax == JOptionPane.NO_OPTION)
                    cancelar();
                
                
                   break;
            case MODIFICAR:
                     String Ti1[] = this.vista.ComboTipotarjeta.getSelectedItem().toString().split("-");
                     String En1[] = this.vista.ComboEntidadEmisora.getSelectedItem().toString().split("-");
                     String cl1[] = this.vista.ComboEntidadEmisora.getSelectedItem().toString().split("-");
                if (this.modelo.ModificarTarjeta(
                         this.vista.CampoCodigo.getText(),
                        this.vista.CampoNroTarjeta.getText() ,
                        this.vista.CampoMonto.getText() ,
                         this.modelo.formatearFecha(this.vista.CampoFechaEmision.getText()),
                       this.modelo.formatearFecha(this.vista.CampoFechaCobro.getText()),
                       
                      Ti1[0].trim(),
                     En1[0].trim(),
                    cl1[0].trim()
                        )){
                    this.vista.tablaConsulta.setModel(this.modelo.getTablaTarjeta());
                    FormateaGrilla();
                    JOptionPane.showMessageDialog(null, "Registro Modificado");
                    cancelar();
                }
                break; 
                
                 case NUEVO:   
                    
                  cancelar();
                  
                break;
                case IMPRIMIR:
                    modelo.Reportes("Informetarjetas");
                 break;
        }
    }                            // idFuncionarios Nombre Salario sexo CIN Fech_Nac Direccion Telefono Email idCargos idBarrios
    
     public void limpiarCampos() {
                  //  this.vista.CampoCodigo.setText("");
                    this.vista.CampoNroTarjeta.setText("") ;
                    this.vista.CampoMonto.setText("") ;
                    this.vista.CampoFechaEmision.setText(( this.modelo.getFechaActual()));
                    this.vista.CampoFechaCobro.setText(( this.modelo.getFechaActual()));
                    
                    
                    this.vista.CampoNroTarjeta.requestFocus();
                  
    }
    public void cancelar() {
                     limpiarCampos();
                     
                     this.modelo.generar_codigo();
                     this.vista.CampoCodigo.setText(this.modelo.idTarjetas);
                     
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
            this.vista.tablaConsulta.getColumnModel().getColumn(0).setPreferredWidth(70);
            this.vista.tablaConsulta.getColumnModel().getColumn(1).setPreferredWidth(200);
            this.vista.tablaConsulta.getColumnModel().getColumn(2).setPreferredWidth(150);
           
    }
}



              