package pe.edu.upn.encuesta.service;

import java.util.List;

import pe.edu.upn.encuesta.entidad.RolOpcion;
import com.indra.core.seguridad.RolSistema;
import com.indra.core.service.ScrudService2;

public interface IRolOpcionService extends ScrudService2<RolOpcion> {
    public List<RolOpcion> obtenerOpcionesPorRoles(List<RolSistema> roles);
}
