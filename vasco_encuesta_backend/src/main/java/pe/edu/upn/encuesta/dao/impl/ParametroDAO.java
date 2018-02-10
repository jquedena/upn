package pe.edu.upn.encuesta.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.Constante;
import pe.edu.upn.encuesta.dao.IParametroDAO;
import pe.edu.upn.encuesta.entidad.Parametro;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * 
 * @author JUAN
 *
 */
@Repository("parametroDAO")
public class ParametroDAO extends HibernateEntidadDAO2<Parametro> implements IParametroDAO {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ParametroDAO.class);
    private static final String ID = "id";
    private static final String PARAMETRO = "parametro";
    private static final String PARAMETRO_ID = "parametro.id";
    private static final String PARAMETRO_TIPO = "parametroTipo";
    private static final String TIPO = "tipo";
    private static final String NOMBRE = "nombre";
    private static final String CODIGO = "codigo";
    private static final String ESTADO = "estado";
    private static final String BOOLEANO = "booleano";
    private static final String TEXTO3 = "texto3";
    private static final String PERMITE_HIJO = "permiteHijo";
    
    @Override
    protected Consulta<Parametro> crearConsultaPaginado(Parametro item) {
        Objeto oParam = new Objeto(Parametro.class.getCanonicalName(), item);
        Objeto oParamPadre = new Objeto(PARAMETRO, oParam.getNombreEntidad(), item.getParametro());
        Objeto oParamTipo = new Objeto(PARAMETRO_TIPO, oParam.getNombreEntidad(), item.getParametroTipo());
        CriteriaConsulta<Parametro> consulta = new CriteriaConsulta<Parametro>(getSession());
        return consulta.agregarEntidad(oParam)
            .agregarEntidad(oParamTipo)
            .agregarCondicion(Condicion.igualA(oParam, ID))
            .agregarCondicion(Condicion.igualA(oParam, TIPO))
            .agregarCondicion(Condicion.contiene(oParam, NOMBRE))
            .agregarCondicion(Condicion.igualA(oParam, BOOLEANO))
            .agregarCondicion(Condicion.igualA(oParam, TEXTO3))
            .agregarCondicion(Condicion.igualA(oParam, ESTADO))
            .agregarCondicion(Condicion.igualA(oParamPadre, ID))
            .agregarCondicion(Condicion.igualA(oParam, PERMITE_HIJO))
            .agregarCondicion(Condicion.contiene(oParamPadre, NOMBRE))
            .agregarCondicion(Condicion.igualA(oParam, "blobHabil"))
            .ordenarAsc(oParam, ID);
    }

    private List<Parametro> listarPorID(List<Long> idHijos, List<Long> idPadres) {
        Objeto oParam = new Objeto(Parametro.class.getCanonicalName(), null);
        Objeto oParamPadre = new Objeto(PARAMETRO, oParam.getNombreEntidad(), null);
        CriteriaConsulta<Parametro> consulta = new CriteriaConsulta<Parametro>(getSession());
        consulta.agregarEntidad(oParam)
            .agregarEntidad(oParamPadre)
            .agregarCondicion(Condicion.igualA(oParam, ESTADO))
            .agregarCondicion(Condicion.en(oParam, ID, idHijos))
            .agregarCondicion(Condicion.en(oParamPadre, ID, idPadres))
            .ordenarAsc(oParam, NOMBRE);
        
        return consulta.listar();
    }
    
    @Override
    public List<Parametro> listarPorIDs(List<Long> ids) {
        return listarPorID(ids, null);
    }

    @Override
    public List<Parametro> listarPorIDsPadres(List<Long> ids) {
        return listarPorID(null, ids);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Parametro> listarPadres() {
        Criteria criterioParametro = super.getCriteria(Parametro.class);
        criterioParametro.setFetchMode(PARAMETRO, FetchMode.JOIN);
        criterioParametro.setFetchMode(PARAMETRO_TIPO, FetchMode.JOIN);
        criterioParametro.add(Restrictions.eq(TIPO, Constante.KeyParametro.PB_TIPO_PADRE));
        criterioParametro.add(Restrictions.eq(ESTADO, "A"));
        criterioParametro.addOrder(Order.asc(NOMBRE));
        return (List<Parametro>) criterioParametro.list();
    }

    @Override
    public Parametro obtener(long idParametro) {
        Criteria criterioParametro = super.getCriteria(Parametro.class);
        criterioParametro.setFetchMode(PARAMETRO, FetchMode.JOIN);
        criterioParametro.setFetchMode(PARAMETRO_TIPO, FetchMode.JOIN);
        criterioParametro.add(Restrictions.idEq(idParametro));

        return (Parametro) criterioParametro.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Parametro> listar(long idTipo, List<Long> idPadre, String nombre, String idEstado) {
        Criteria criterioParametro = super.getCriteria(Parametro.class);
        criterioParametro.setFetchMode(PARAMETRO, FetchMode.JOIN);
        criterioParametro.setFetchMode(PARAMETRO_TIPO, FetchMode.JOIN);

        if (idPadre != null && !idPadre.isEmpty()) {
            criterioParametro.add(Restrictions.in(PARAMETRO_ID, idPadre));
        }

        if (idTipo != -1L) {
            criterioParametro.add(Restrictions.eq(TIPO, idTipo));
        }

        criterioParametro.add(Restrictions.ilike(NOMBRE, "%" + nombre + "%"));

        if (!"-1".equalsIgnoreCase(idEstado)) {
            criterioParametro.add(Restrictions.eq(ESTADO, idEstado));
        }

        criterioParametro.addOrder(Order.asc(ID));

        return (List<Parametro>) criterioParametro.list();
    }
    
    @Override
    public boolean codigoExiste(String codigo, long idTipo, long idParametro, long idPadre) {
        Criteria criterioParametro = super.getCriteria(Parametro.class);

        criterioParametro.add(Restrictions.eq(TIPO, idTipo));
        if (idParametro != -1L) {
            criterioParametro.add(Restrictions.ne(ID, idParametro));
        }
        if (idPadre != -1L) {
            criterioParametro.add(Restrictions.eq(PARAMETRO_ID, idPadre));
        }
        criterioParametro.add(Restrictions.eq(CODIGO, codigo.trim()).ignoreCase());

        return !criterioParametro.list().isEmpty();
    }

    @Override
    public boolean nombreExiste(String nombre, long idTipo, long idParametro, long idPadre) {
        Criteria criterioParametro = super.getCriteria(Parametro.class);

        criterioParametro.add(Restrictions.eq(TIPO, idTipo));
        if (idParametro != -1L) {
            criterioParametro.add(Restrictions.ne(ID, idParametro));
        }
        if (idPadre != -1L) {
            criterioParametro.add(Restrictions.eq(PARAMETRO_ID, idPadre));
        }
        criterioParametro.add(Restrictions.eq(NOMBRE, nombre.trim()).ignoreCase());

        return !criterioParametro.list().isEmpty();
    }

    @Override
    public Parametro obtenerParametroPorCodigo(Long idPadre, String codigo) {
        Criteria criterioParametro = super.getCriteria(Parametro.class);
        if (idPadre != -1L) {
            criterioParametro.add(Restrictions.eq(PARAMETRO_ID, idPadre));
        }
        if (codigo != null) {
            criterioParametro.add(Restrictions.eq(CODIGO, codigo.trim()).ignoreCase());
        }
        return (Parametro) criterioParametro.uniqueResult();
    }

    protected void setRestrictions(Object o, String[] fields, Criteria criteria) {
        for (String field : fields) {
            try {
                Object value = getProperty(o, field);
                Class<?> clase = getTypeProperty(o, field);
                if (value != null) {
                    criteria.add(addRestriction(clase, value, field));
                }
            } catch (NullValueInNestedPathException e) {
                LOGGER.debug(field + ":null", e);
            }
        }

    }

    private Criterion addRestriction(Class<?> clase, Object value, String field) {
        Criterion expresion = null;
        boolean valido = clase.equals(Integer.class);
        
        valido = valido || clase.equals(Long.class);
        valido = valido || clase.equals(Boolean.class);
        valido = valido || clase.equals(Character.class);
        valido = valido || clase.equals(int.class);
        valido = valido || clase.equals(long.class);
        valido = valido || clase.equals(boolean.class);
        valido = valido || clase.equals(char.class);
        
        if (valido) {
            expresion = Restrictions.eq(field, value);
        } else if (clase.equals(String.class)) {
            expresion = Restrictions.ilike(field, value.toString(), MatchMode.ANYWHERE);
        }
        return expresion;
    }
    
    @SuppressWarnings("unchecked")
    private <E> E getProperty(Object o, String field) {
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(o);
        Object value = wrapper.getPropertyValue(field);
        return value == null ? null : (E) value;
    }

    private Class<?> getTypeProperty(Object o, String field) {
        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(o);
        return wrapper.getPropertyType(field);
    }
}
