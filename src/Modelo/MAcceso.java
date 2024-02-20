package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MAcceso extends ConexionBD {
    
public static String xCodUsuario= "";
public static String xNombreUsuario= "";
    public boolean accesoUsuario(String usuario) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("select * from usuarios where usuario = '" + usuario + "'");
            ResultSet res = pstm.executeQuery();
            boolean r = res.next();
            if (r) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El Usuario no se encuentra Registrado..", "Atencion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean accesoClave(String usuario, String clave) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("select * from usuarios where usuario = '" + usuario + "'");
            ResultSet res = pstm.executeQuery();
            res.next();
             xCodUsuario = res.getString(1);
             xNombreUsuario = res.getString(2);
              //JOptionPane.showMessageDialog(null, "CodUsuario   "+xNombreUsuario);
            String xclave = res.getString(3);
            if (xclave.equals(clave)) {
               
                return true;
                  
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta", "Atencion",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MAcceso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
