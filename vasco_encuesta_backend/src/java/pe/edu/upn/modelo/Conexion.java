package pe.edu.upn.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    public  static Connection getConexion(){

        Connection cone=null;
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            String user="root";
            String password="root";
            String url="jdbc:mysql://localhost:3306/vasco_encuesta";
            cone = DriverManager.getConnection(url, user, password);
            
        }catch(SQLException ex1){
            return null;  
        }catch(Exception ex2){
            return null;
        }
        return cone;
    }
}
