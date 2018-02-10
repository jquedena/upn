package pe.edu.upn.encuesta.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IRolOpcionDAO;
import pe.edu.upn.encuesta.dao.transformer.PrimerElementoTransformer;
import pe.edu.upn.encuesta.entidad.RolOpcion;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

@Repository("rolOpcionDAO")
public class RolOpcionDAO extends HibernateEntidadDAO2<RolOpcion> implements IRolOpcionDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public RolOpcion obtener(long item) {
        return obtener(item, RolOpcion.class);
    }

    @Override
    protected Consulta<RolOpcion> crearConsultaPaginado(RolOpcion item) {
        Objeto oModulo = new Objeto(RolOpcion.class.getCanonicalName(), item);
        CriteriaConsulta<RolOpcion> consulta = new CriteriaConsulta<RolOpcion>(getSession());
        return consulta
            .agregarCondicion(Condicion.igualA(oModulo, "rolIdm"))
            .ordenarAsc(oModulo, "id");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RolOpcion> obtenerOpcionesPorRoles(List<String> roles) {
        Query q = getSession().getNamedQuery("obtenerOpcionesPorRoles");
        q.setParameterList("listaRoles", roles);
        q.setResultTransformer(new PrimerElementoTransformer());
        return (List<RolOpcion>) q.list();
    }

}
