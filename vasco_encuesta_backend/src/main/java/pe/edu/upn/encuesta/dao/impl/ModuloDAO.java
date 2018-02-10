package pe.edu.upn.encuesta.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IModuloDAO;
import pe.edu.upn.encuesta.entidad.Modulo;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

@Repository("moduloDAO")
public class ModuloDAO extends HibernateEntidadDAO2<Modulo> implements IModuloDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public Modulo obtener(long item) {
        return obtener(item, Modulo.class);
    }

    @Override
    protected Consulta<Modulo> crearConsultaPaginado(Modulo item) {
        Objeto oModulo = new Objeto(Modulo.class.getCanonicalName(), item);
        CriteriaConsulta<Modulo> consulta = new CriteriaConsulta<Modulo>(getSession());
        return consulta.agregarCondicion(Condicion.contiene(oModulo, "nombre"))
                .agregarCondicion(Condicion.contiene(oModulo, "descripcion"))
                .agregarCondicion(Condicion.igualA(oModulo, "idTipo"))
                .agregarCondicion(Condicion.igualA(oModulo, "idModulo"))
                .agregarCondicion(Condicion.igualA(oModulo, "estado"))
                .ordenarAsc(oModulo, "id");
    }

    @Override
    public boolean cambiarEstado(List<Long> listaModuloIds, String estado) {
        Query q = getSession().getNamedQuery("cambiarEstado");
        q.setParameter("estado", estado);
        q.setParameterList("listaModuloIds", listaModuloIds);
        q.executeUpdate();
        return true;
    }

}
