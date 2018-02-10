package pe.edu.upn.encuesta.seguridad.aop;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.indra.core.domain.Entidad;
import com.indra.core.exception.BussinesException;
import com.indra.core.seguridad.IUsuario;
import com.indra.util.jackson.HibernateAwareObjectMapper;
import com.indra.web.listener.WebServletContextListener;

@Aspect
public class AuditoriaTablaInterceptor {

    private static final Logger LOGGER = Logger.getLogger(AuditoriaTablaInterceptor.class);
    private static final List<String> METHODS = Arrays.asList("agregarOActualizarTodos", "agregarOActualizar", "actualizar", "agregar");

    @SuppressWarnings("rawtypes")
    private void completeAuditFields(Object o) {
        IUsuario u = WebServletContextListener.getBean("usuarioAutentificado");
        Entidad e = (Entidad) o;
        if (e.getFechaCreacion() == null) {
            e.setFechaCreacion(new Timestamp(new Date().getTime()));
            e.setUsuarioCreacion(u.getCodigoRegistro());
        } else {
            e.setFechaModificacion(new Timestamp(new Date().getTime()));
            e.setUsuarioModificacion(u.getCodigoRegistro());
        }
    }

    /**
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.bbva.*.dao.*.* (..))")
    public Object invokeMethodsApplication(ProceedingJoinPoint joinPoint) throws Throwable {
        return invokeAround(joinPoint, "invokeMethodsApplication");
    }

    /**
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.bbva.*.service.*.* (..))")
    public Object invokeMethodsServiceApplication(ProceedingJoinPoint joinPoint) throws Throwable {
        long i1 = System.currentTimeMillis();
        
        Object o = joinPoint.proceed(joinPoint.getArgs());
        long i2 = System.currentTimeMillis();
        
        if(LOGGER.isTraceEnabled()) {
            timeExecution(joinPoint, i2 - i1, "invokeMethodsServiceApplication");
        }
        
        return o;
    }

    
    /**
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.indra.*.dao.impl.*.* (..))")
    public Object invokeMethodsGeneric(ProceedingJoinPoint joinPoint) throws Throwable {
        return invokeAround(joinPoint, "invokeMethodsGeneric");
    }

    private void timeExecution(ProceedingJoinPoint joinPoint, long time, String source) throws JsonProcessingException {
        HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Class: ");
        sb.append(joinPoint.getTarget().getClass().getName());
        sb.append(", Method Invoke: ");
        sb.append(joinPoint.getSignature().getName());
        sb.append(", Args: ");
        sb.append(mapper.writeValueAsString(joinPoint.getArgs()));
        sb.append(", Time: ");
        sb.append(time);
        sb.append("ms, Source: ");
        sb.append(source);
        LOGGER.trace(sb.toString());
    }
    
    /**
     * Intercepta los m\u00E9todos de la capa de persistencia que contengan como
     * par\u00E1metros final <b>boolean lazy</b>
     * 
     * @param joinPoint, objeto que contiene la informaci\u00F3n del m\u00E9todo 
     * que ha realizado la invocaci\u00F3n
     * @return Object, objetos de salida del m\u00E9todo invocado
     * @throws BussinesException
     */
    private Object invokeAround(ProceedingJoinPoint joinPoint, String source) throws Throwable {
        long i1 = System.currentTimeMillis();
        /*
         * saveOrUpdateAll, saveOrUpdate, save, update, merge
         */
        if (METHODS.indexOf(joinPoint.getSignature().getName()) > -1) {
            if ("agregarOActualizarTodos".equalsIgnoreCase(joinPoint.getSignature().getName())) {
                List<?> entities = (List<?>) joinPoint.getArgs()[0];
                for (Object e : entities) {
                    completeAuditFields(e);
                }
            } else {
                completeAuditFields(joinPoint.getArgs()[0]);
            }
            // "No se pudo establecer los datos del usuario para la auditoria"
        }
        Object o = joinPoint.proceed(joinPoint.getArgs());
        long i2 = System.currentTimeMillis();
        
        if(LOGGER.isTraceEnabled()) {
            timeExecution(joinPoint, i2 - i1, source);
        }
        
        return o;
    }
}
