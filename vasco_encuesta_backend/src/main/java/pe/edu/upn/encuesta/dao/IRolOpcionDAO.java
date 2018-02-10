package pe.edu.upn.encuesta.dao;

import java.util.List;

import pe.edu.upn.encuesta.entidad.RolOpcion;
import com.indra.core.dao.EntidadDAO2;

public interface IRolOpcionDAO extends EntidadDAO2<RolOpcion> {

	public List<RolOpcion> obtenerOpcionesPorRoles(List<String> roles);
	
}
