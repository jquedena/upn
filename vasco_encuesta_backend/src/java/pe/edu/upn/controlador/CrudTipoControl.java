package pe.edu.upn.controlador;

import pe.edu.upn.modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import pe.edu.upn.modelo.EntityTipoControl;

public class CrudTipoControl {
    //operaciones CRUD

    //agregar
    public String insertar(EntityTipoControl e) {
        String msj = null;

        try {

            Connection cone = Conexion.getConexion();

            //objeto para sentencias preparadas
            String sql = "insert into Pregunta_Respuesta values(?,?,?,?,?,?)";
            PreparedStatement ps = cone.prepareStatement(sql);

            //pasar parametros a la sentencia preparada
            ps.setInt(1, e.getidTip_Con());
            ps.setString(2, e.getDescripcion());
           
           
            //ejecutar la sentencia preparada
            ps.executeUpdate();

            msj = "Tipo_Control registradas correctamente";

        } catch (SQLException ex) {
            msj = "error al insertar" + ex.getMessage();
        }
        return msj;
    }

}
