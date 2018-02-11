package pe.edu.upn.controlador;

import pe.edu.upn.modelo.Conexion;
import pe.edu.upn.modelo.EntityPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudPersona {

    private static final Logger LOGGER = Logger.getLogger(CrudPersona.class.getName());

    public static String insertar(EntityPersona e) {
        String msj;
        try {
            Connection cone = Conexion.getConexion();

            //objeto para sentencias preparadas
            String sql = "insert into persona(nombre, apellido_paterno, apellido_materno, dni, direccion, telefono) values(?,?,?,?,?,?)";
            PreparedStatement ps = cone.prepareStatement(sql);

            //pasar parametros a la sentencia preparada
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellidoPaterno());
            ps.setString(3, e.getApellidoMaterno());
            ps.setString(4, e.getDni());
            ps.setString(5, e.getDireccion());
            ps.setString(6, e.getTelefono());

            //ejecutar la sentencia preparada
            ps.executeUpdate();

            msj = "Persona registrado correctamente";
        } catch (SQLException | NullPointerException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo registrar a la persona", ex);
            msj = "Error al insertar <b style='color: red;'>" + ex.getMessage() + "</b>";
        }
        return msj;
    }

}
