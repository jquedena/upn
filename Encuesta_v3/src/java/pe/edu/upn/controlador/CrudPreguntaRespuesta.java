package pe.edu.upn.controlador;

import pe.edu.upn.modelo.Conexion;
import pe.edu.upn.modelo.EntityPreguntaRespuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudPreguntaRespuesta {
    //operaciones CRUD

    //agregar
    public String insertar(EntityPreguntaRespuesta e) {
        String msj = null;

        try {

            Connection cone = Conexion.getConexion();

            //objeto para sentencias preparadas
            String sql = "insert into Pregunta_Respuesta values(?,?,?,?,?,?)";
            PreparedStatement ps = cone.prepareStatement(sql);

            //pasar parametros a la sentencia preparada
            ps.setInt(1, e.getIdPre_Res());
            ps.setString(2, e.getRpta());
            ps.setInt(3, e.getIdEnc_Res());
            ps.setInt(4, e.getIdPre());
           
            //ejecutar la sentencia preparada
            ps.executeUpdate();

            msj = "Pregunta_Respuesta registradas correctamente";

        } catch (SQLException ex) {
            msj = "error al insertar" + ex.getMessage();
        }
        return msj;
    }

}
