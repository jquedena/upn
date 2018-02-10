package pe.edu.upn.encuesta.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.dao.IRolOpcionDAO;
import pe.edu.upn.encuesta.entidad.RolOpcion;
import pe.edu.upn.encuesta.service.IRolOpcionService;
import com.indra.core.seguridad.RolSistema;
import com.indra.core.service.impl.HibernateScrudService2;

@Service("rolOpcionService")
@Transactional(readOnly = true)
public class RolOpcionService 
    extends HibernateScrudService2<RolOpcion, IRolOpcionDAO> 
    implements IRolOpcionService {

    private static final long serialVersionUID = 1L;

    @Autowired
    public void setRolOpcionDAO(@Qualifier("rolOpcionDAO") IRolOpcionDAO rolOpcionDAO) {
        super.setEntidadDAO(rolOpcionDAO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RolOpcion> obtenerOpcionesPorRoles(List<RolSistema> roles) {
        List<RolOpcion> listaOpciones = new ArrayList<RolOpcion>();
        if (roles != null && !roles.isEmpty()) {
            List<String> listaCodigos = new ArrayList<String>();
            for (RolSistema rol : roles) {
                listaCodigos.add(rol.getCodigo());
            }
            listaOpciones = getHibernateDAO().obtenerOpcionesPorRoles(listaCodigos);
        }
        return listaOpciones;
    }

}
