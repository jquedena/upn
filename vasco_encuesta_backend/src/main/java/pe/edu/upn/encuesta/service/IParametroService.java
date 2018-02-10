package pe.edu.upn.encuesta.service;

import java.util.List;
import java.util.Map;

import pe.edu.upn.encuesta.entidad.Parametro;
import com.indra.core.service.ScrudService2;

/**
 * 
 * @author JUAN
 *
 */
public interface IParametroService extends ScrudService2<Parametro> {

    /**
     * 
     * @param ids
     * @return
     */
    Map<Long, Parametro> listarPorIDs(List<Long> ids);
    
    /**
     * 
     * @param ids
     * @return
     */
    Map<Long, List<Parametro>> listarPorIDsPadres(List<Long> ids);
    
    /**
     * Ejecuta el m\u00E9todo 
     * @param idParametro
     * @return Un p\u00E1rametros, nulo si no existe
     */
    Parametro obtener(long idParametro);

    /**
     * Ejecuta el m\u00E9todo listarPadres
     * @return Lista de p\u00E1rametros
     */
    List<Parametro> listarPadres();
    
    Parametro actualizar(Parametro o, Parametro paramRepos);
}
