package pe.edu.upn.encuesta.dao;

import java.util.List;

import pe.edu.upn.encuesta.entidad.ConsolidadoInputsHost;
import com.indra.core.dao.EntidadDAO2;
import com.indra.core.exception.BussinesException;
import com.indra.core.exception.SinResultadosException;

/**
 * IConsolidadoInputsHostDAO
 * @author JUAN
 *
 */
public interface IConsolidadoInputsHostDAO extends
        EntidadDAO2<ConsolidadoInputsHost> {
    
    /**
     * 
     * @param item
     * @param nroFilas
     * @param pagina
     * @throws SinResultadosException
     */    
    List<ConsolidadoInputsHost> resumen(ConsolidadoInputsHost consolidadoInputsHost, Integer nroFilas, Integer pagina)  throws SinResultadosException;
    
    /**
     * 
     * @param consolidadoInputsHost
     * @return
     */
    Long contarResumen(ConsolidadoInputsHost consolidadoInputsHost);
}
