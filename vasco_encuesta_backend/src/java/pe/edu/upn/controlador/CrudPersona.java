package pe.edu.upn.controlador;

import pe.edu.upn.modelo.Conexion;
import pe.edu.upn.modelo.EntityPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudPersona {
    //operaciones CRUD

    //agregar
    public String insertar(EntityPersona e) {
        String msj = null;

        try {

            Connection cone = Conexion.getConexion();

            //objeto para sentencias preparadas
            String sql = "insert into persona values(?,?,?,?,?,?)";
            PreparedStatement ps = cone.prepareStatement(sql);

            //pasar parametros a la sentencia preparada
            ps.setInt(1, e.getCodigo());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellidos());
            ps.setString(4, e.getDni());
            ps.setInt(5, e.getTelefono());
            ps.setString(6, e.getDireccion());

            //ejecutar la sentencia preparada
            ps.executeUpdate();

            msj = "Persona registrado correctamente";

        } catch (SQLException ex) {
            msj = "error al insertar" + ex.getMessage();
        }
        return msj;
    }

}
