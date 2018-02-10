package pe.edu.upn.encuesta.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtilsBean2;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.dao.IParametroDAO;
import pe.edu.upn.encuesta.entidad.Parametro;
import pe.edu.upn.encuesta.entidad.ParametroAux;
import pe.edu.upn.encuesta.service.IParametroService;
import com.indra.core.dao.EntidadDAO2;
import com.indra.core.exception.SinResultadosException;
import com.indra.core.exception.ValidadorException;
import com.indra.core.service.impl.HibernateScrudService2;

/**
 * 
 * @author JUAN
 *
 */
@Service("parametroService")
public class ParametroService extends HibernateScrudService2<Parametro, IParametroDAO> implements IParametroService {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ParametroService.class);
    private static final String ERROR_CODIGO_EXISTE = "El c\u00F3digo personalizado ingresado ya existe, ingrese uno diferente.";
    private static final String ERROR_NOMBRE_EXISTE = "El nombre del par\u00E1metro ingresado ya existe.";
    
    @Resource(name = "parametroAuxDAO")
    private EntidadDAO2<ParametroAux> parametroAuxDAO;
    
    public ParametroService() {
        ConvertUtils.register(new StringConverter(), String.class);
        //date 
        ConvertUtils.register(new DateConverter(null),java.util.Date.class);
        ConvertUtils.register(new SqlDateConverter(null),java.sql.Date.class);
        ConvertUtils.register(new SqlTimeConverter(null),Time.class);
        ConvertUtils.register(new SqlTimestampConverter(null),Timestamp.class);
        //number
        ConvertUtils.register(new BooleanConverter(null), Boolean.class);
        ConvertUtils.register(new ShortConverter(null), Short.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class); 
        ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class); 
    }
    
    @Autowired
    public void setAplicacionDAO(@Qualifier("parametroDAO") IParametroDAO parametroDAO) {
        super.setEntidadDAO(parametroDAO);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, List<Parametro>> listarPorIDsPadres(List<Long> ids) {
        List<Parametro> parametros = getHibernateDAO().listarPorIDsPadres(ids);
        Map<Long, List<Parametro>> resultado = new LinkedHashMap<Long, List<Parametro>>();
        
        for(Parametro p : parametros) {
            Long key = p.getParametro().getId();
            if(!resultado.containsKey(key)) {
                resultado.put(key, new ArrayList<Parametro>());
            }
            resultado.get(key).add(p);
        }
        
        return resultado;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<Long, Parametro> listarPorIDs(List<Long> ids) {
        List<Parametro> parametros = getHibernateDAO().listarPorIDs(ids);
        Map<Long, Parametro> resultado = new LinkedHashMap<Long, Parametro>();
        
        for(Parametro p : parametros) {
            resultado.put(p.getId(), p);
        }
        
        return resultado;
    }

    @Override
    @Transactional(readOnly = true)
    public Parametro obtener(long idParametro) {
        return getHibernateDAO().obtener(idParametro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Parametro> listarPadres() {
        return getHibernateDAO().listarPadres();
    }

    private boolean codigoExiste(String codigo, long idTipo, long idParametro, long idPadre) {
        return getHibernateDAO().codigoExiste(codigo, idTipo, idParametro, idPadre);
    }

    private boolean nombreExiste(String nombre, long idTipo, long idParametro, long idPadre) {
        return getHibernateDAO().nombreExiste(nombre, idTipo, idParametro, idPadre);
    }

    @Override
    @Transactional
    public void eliminar(Parametro param) {
        Parametro temp = getHibernateDAO().obtener(param.getId());
        getHibernateDAO().eliminar(temp);
    }

    @Override
    @Transactional
    public Parametro actualizar(Parametro o, Parametro paramRepos) {
        Parametro param = o;
        
        if (param.getId() == 0L) {
            transfer1(param, paramRepos);

            /*if (nombreExiste(param.getNombre(), param.getTipo(), -1L, param.getParametro().getId())) {
                throw new ValidadorException(ERROR_NOMBRE_EXISTE);
            } else*/
            if ("S".equalsIgnoreCase(param.getValidarCodigo()) && param.getCodigoHabil() == 'S' && codigoExiste(param.getCodigo(), param.getTipo(), -1L, param.getParametro().getId())) {
                throw new ValidadorException(ERROR_CODIGO_EXISTE);
            } else {
                ParametroAux dest = new ParametroAux();
                try {
                    BeanUtilsBean2.getInstance().copyProperties(dest, param);
                } catch (IllegalAccessException e) {
                    LOG.error("", e);
                } catch (InvocationTargetException e) {
                    LOG.error("", e);
                }
                dest.setId(null);
                dest.setIdParametroPadre(param.getParametro().getId());
                parametroAuxDAO.agregar(dest);
            }
        } else {
            try {
                paramRepos = obtener(param.getId());
                transfer2(paramRepos, param);
            } catch (SinResultadosException e) {
                throw new ValidadorException("Parametro padre no registrado", e);
            }
            /*if (nombreExiste(param.getNombre(), param.getTipo(), param.getId(), param.getParametro() == null ? -1L : param.getParametro().getId())) {
                throw new ValidadorException(ERROR_NOMBRE_EXISTE);
            } else 
            */if ("S".equalsIgnoreCase(param.getValidarCodigo()) && param.getCodigo() != null
                    && codigoExiste(param.getCodigo(), param.getTipo(), param.getId(), param.getParametro() == null ? -1L : param.getParametro().getId())) {
                throw new ValidadorException(ERROR_CODIGO_EXISTE);
            } else {
                getHibernateDAO().actualizar(paramRepos);
            }
        }
        
        return param;
    }

    private void transfer2(Parametro param, Parametro paramRepos) {
        param.setCodigo(paramRepos.getCodigo());
        param.setEntero(paramRepos.getEntero());
        param.setDecimales(paramRepos.getDecimales());
        param.setTexto(paramRepos.getTexto());
        param.setTexto2(paramRepos.getTexto2());
        param.setTexto3(paramRepos.getTexto3());
        param.setTexto4(paramRepos.getTexto4());
        param.setFecha(paramRepos.getFecha());
        param.setHora(paramRepos.getHora());
        param.setBooleano(paramRepos.getBooleano());
        param.setBooleano2(paramRepos.getBooleano2());
        param.setBlobVal(paramRepos.getBlobVal());
        param.setNombre(paramRepos.getNombre());
    }
    
    private void transfer1(Parametro param, Parametro paramRepos) {
        param.setCodigoHabil(paramRepos.getCodigoHabil());
        param.setCodigoEti(paramRepos.getCodigoEti());
        param.setEnteroHabil(paramRepos.getEnteroHabil());
        param.setEnteroEti(paramRepos.getEnteroEti());
        param.setDecimalesHabil(paramRepos.getDecimalesHabil());
        param.setDecimalesEti(paramRepos.getDecimalesEti());
        param.setTextoHabil(paramRepos.getTextoHabil());
        param.setTextoEti(paramRepos.getTextoEti());
        param.setTextoHabil2(paramRepos.getTextoHabil2());
        param.setTextoEti2(paramRepos.getTextoEti2());
        param.setTextoHabil3(paramRepos.getTextoHabil3());
        param.setTextoEti3(paramRepos.getTextoEti3());
        param.setTextoHabil4(paramRepos.getTextoHabil4());
        param.setTextoEti4(paramRepos.getTextoEti4());
        param.setFechaHabil(paramRepos.getFechaHabil());
        param.setFechaEti(paramRepos.getFechaEti());
        param.setHoraHabil(paramRepos.getHoraHabil());
        param.setHoraEti(paramRepos.getHoraEti());
        param.setBooleanoHabil(paramRepos.getBooleanoHabil());
        param.setBooleanoEti(paramRepos.getBooleanoEti());
        param.setBooleanoHabil2(paramRepos.getBooleanoHabil2());
        param.setBooleanoEti2(paramRepos.getBooleanoEti2());
        param.setValidarCodigo(paramRepos.getValidarCodigo());
        param.setPermiteEliminar(paramRepos.getPermiteEliminar());
    }
}
