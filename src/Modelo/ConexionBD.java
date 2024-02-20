/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public String db = "tecno";
    public String user = "root";
    public String password = "123456";
    public String url = "jdbc:mysql://localhost/" + db;
    public Connection conn = null;

    public ConexionBD() {
        this.url = "jdbc:mysql://localhost/" + this.db;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    public Connection getConnection(){
        return this.conn;
    }
    
    
}
