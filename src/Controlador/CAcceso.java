package Controlador;

import Modelo.MAcceso;
import Vista.VAcceso;
import Vista.VMenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class CAcceso implements ActionListener {
    // instancia a nuestra interfaz de usuario

    VAcceso vista;
    // instancia a nuestro modelo
    MAcceso modelo = new MAcceso();

    //Se declaran en un ENUM las acciones que se realizan desde la interfaz de usuario VISTA y 
    //posterior ejecución desde el controlador.
//    Un enum, también llamado enumeración o tipo enumerado es un tipo de dato definido 
//    por el usuario que solo puede tomar como valores los definidos en una lista.
    public enum AccionMVC {

        INGRESAR,
        SALIR,}

    // Constrcutor de clase vista Instancia de clase interfaz
    public CAcceso(VAcceso vista) {
        this.vista = vista;
    }

    public void iniciar() {
        this.vista.setLocationRelativeTo(null);
        vista.setVisible(true);

        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.btningresar.setActionCommand("INGRESAR");
        this.vista.btningresar.addActionListener(this);
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.btnSalir.setActionCommand("SALIR");
        this.vista.btnSalir.addActionListener(this);
    }

    //Control de eventos de los controles que tienen definido un "ActionCommand"
    public void actionPerformed(ActionEvent e) {
        switch (CAcceso.AccionMVC.valueOf(e.getActionCommand())) {
            case INGRESAR:
                if (this.vista.txtusuario.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Favor ingrese Usuario",
                            "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    this.vista.txtusuario.requestFocus();
                } else {
                    if (this.modelo.accesoUsuario(this.vista.txtusuario.getText())) {
                        if (this.vista.txtcontrasena.getText().equals("")) {
                            JOptionPane.showMessageDialog(null, "Favor ingrese la Clave",
                                    "Atencion", JOptionPane.INFORMATION_MESSAGE);
                            this.vista.txtcontrasena.requestFocus();
                        } else {
                            if (this.modelo.accesoClave(this.vista.txtusuario.getText(), this.vista.txtcontrasena.getText())) {
                                
                                VMenuPrincipal vista2= new VMenuPrincipal();
                                   vista.txtusuario.setText(this.vista.txtusuario.getText());
                                  // JOptionPane.showMessageDialog(null, "usuario  :  "+this.vista.txtusuario.getText());
             
                                  new VMenuPrincipal().setVisible(true);
                                  this.vista.dispose();
                                
                            } else {
                                this.vista.txtcontrasena.setText("");
                                this.vista.txtcontrasena.requestFocus();
                            }
                        }
                    } else {
                        this.vista.txtusuario.setText("");
                        this.vista.txtusuario.requestFocus();
                    }
                }
                break;
            case SALIR:
                System.exit(0);
                break;
        }
    }
}
