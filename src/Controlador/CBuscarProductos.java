
package Controlador;


import Modelo.MBuscarProductos;
import Vista.VBuscarProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;




public class CBuscarProductos implements ActionListener, MouseListener, KeyListener {
    // instancia a nuestra interfaz de usuario
    VBuscarProductos vista;
 
    // instancia a nuestro modelo
    MBuscarProductos modelo = new MBuscarProductos();
        public enum AccionMVC
           {
                RadioButonNombre,  
                RadioButonRuc,  
                RadioButonMostrarTodo,
                BotonBuscar
              
           }
     @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    public CBuscarProductos(VBuscarProductos vista) {
        this.vista = vista;
    }
 
     //EVENTOS DEL RATON SOBRE LOS COMPONENTES
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = this.vista.tabla_busca.rowAtPoint(e.getPoint());
            if (fila > -1) {               
                
                 this.vista.CampoCodProducto.setText(String.valueOf(this.vista.tabla_busca.getValueAt(fila, 0)));
                 this.vista.CampoDescripcion.setText(String.valueOf(this.vista.tabla_busca.getValueAt(fila, 1)));
                 this.vista.CampoCantidad.setText(String.valueOf(this.vista.tabla_busca.getValueAt(fila, 2)));
                 this.vista.CampoPrecioV.setText(String.valueOf(this.vista.tabla_busca.getValueAt(fila, 3)));
                         
                 
                }
        }
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);
        
      
        this.vista.CampoBusca.setEnabled(false);   
             
        this.vista.tabla_busca.addMouseListener(this);
       
        //para que muestre todos al incializar
        
       this.vista.tabla_busca.setModel(this.modelo.getTablaTodo());
        
       
        this.vista.BotonBuscar.setActionCommand( "BotonBuscar" );
        this.vista.BotonBuscar.addActionListener(this); 
        
        this.vista.RadioButonNombre.setActionCommand( "RadioButonNombre" );
        this.vista.RadioButonNombre.addActionListener(this);
        
                     
        this.vista.RadioButonMostrarTodo.setActionCommand( "RadioButonMostrarTodo" );
        this.vista.RadioButonMostrarTodo.addActionListener(this);
    }

      //Control de eventos de los controles que tienen definido un "ActionCommand"
    @Override
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case BotonBuscar:  
                             
                  
                     if(this.vista.RadioButonNombre.isSelected()==true)
                {
                        this.vista.CampoBusca.setEnabled(true); 
                        this.modelo.Descripcion=(String) this.vista.CampoBusca.getText();
                        this.vista.tabla_busca.setModel(this.modelo.getTablaNombre());
                 }
                 // if(this.vista.RadioButonRuc.isSelected()==true)
                //{
                //        this.vista.CampoBusca.setEnabled(true); 
                //        this.modelo.ruc=(String) this.vista.CampoBusca.getText();
                //        this.vista.tabla_busca.setModel(this.modelo.getTablaRUC());
                // }
                 if(this.vista.RadioButonMostrarTodo.isSelected()==true)
                {
                    {

                           this.vista.tabla_busca.setModel(this.modelo.getTablaTodo());
                    }
                 }
            break;
                 
             case RadioButonNombre:     
                 if(this.vista.RadioButonNombre.isSelected()==true)
                    {
                        this.vista.RadioButonMostrarTodo.setSelected(false);                                                                                       
                        this.vista.CampoBusca.setEnabled(true);                 
                        this.vista.CampoBusca.requestFocus();  
                    }
                 
            break;
                         
            case RadioButonRuc:       
                    {
                        this.vista.RadioButonMostrarTodo.setSelected(false);
                        this.vista.RadioButonNombre.setSelected(false);
                        this.vista.CampoBusca.setText("");
                        this.vista.CampoBusca.setEnabled(true);                 
                        this.vista.CampoBusca.requestFocus();  
                    }
                 
            break;
             case RadioButonMostrarTodo:
                 //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                        this.vista.RadioButonNombre.setSelected(false);
                        
                 this.vista.tabla_busca.setModel( this.modelo.getTablaTodo() );
              
             break; 
                
          
        }
    }
}
