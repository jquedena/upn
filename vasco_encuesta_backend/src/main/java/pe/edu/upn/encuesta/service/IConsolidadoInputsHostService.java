package pe.edu.upn.encuesta.service;

import pe.edu.upn.encuesta.entidad.ConsolidadoInputsHost;
import com.indra.core.exception.BussinesException;
import com.indra.core.exception.SinResultadosException;
import com.indra.core.service.ScrudService2;
import com.indra.util.db.Paginator;

public interface IConsolidadoInputsHostService extends
        ScrudService2<ConsolidadoInputsHost> {

    /**
     * 
     * @param item
     * @param nroFilas
     * @param pagina
     * @throws SinResultadosException
     */    
    Paginator<ConsolidadoInputsHost> resumen(ConsolidadoInputsHost consolidadoInputsHost, Integer nroFilas, Integer pagina) throws BussinesException;
    
}
