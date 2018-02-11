package pe.edu.upn.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());
    
    public  static Connection getConexion(){
        Connection cone;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String user="root";
            String password="root";
            String url="jdbc:mysql://localhost:3306/vasco_encuesta";
            cone = DriverManager.getConnection(url, user, password);
        }catch(SQLException | ClassNotFoundException ex){
            LOGGER.log(Level.SEVERE, "No se pudo establecer conexion", ex);
            return null;
        }
        return cone;
    }
}
