package pe.edu.upn.encuesta.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.entidad.Parametro;
import pe.edu.upn.encuesta.entidad.ParametroAux;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Consulta;

/**
 * 
 * @author JUAN
 *
 */
@Repository("parametroAuxDAO")
public class ParametroAuxDAO extends HibernateEntidadDAO2<ParametroAux> {

    private static final long serialVersionUID = 1L;
    private static final String PARAMETRO = "parametro";
    private static final String PARAMETRO_TIPO = "parametroTipo";
    
    @Override
    protected Consulta<ParametroAux> crearConsultaPaginado(ParametroAux item) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ParametroAux obtener(long idParametro) {
        Criteria criterioParametro = super.getCriteria(Parametro.class);
        criterioParametro.setFetchMode(PARAMETRO, FetchMode.JOIN);
        criterioParametro.setFetchMode(PARAMETRO_TIPO, FetchMode.JOIN);
        criterioParametro.add(Restrictions.idEq(idParametro));

        return (ParametroAux) criterioParametro.uniqueResult();
    }
}
