package pe.edu.upn.encuesta.service;

import java.math.BigDecimal;
import java.util.List;

import pe.edu.upn.encuesta.entidad.ConsolidadoBsi;
import com.indra.core.exception.SinResultadosException;
import com.indra.core.service.ScrudService2;
import com.indra.util.db.Paginator;

/**
 * @author JUAN
 *
 */
public interface IConsolidadoBsiService extends ScrudService2<ConsolidadoBsi> {
    
    boolean cuadrarDiferencias(List<BigDecimal> listaIds);
    
    List<BigDecimal> obtenerSectorMayorSaldo(List<ConsolidadoBsi> listConsolidadoBsi, String periodo);
    
    boolean cuadrarActualizado(List<ConsolidadoBsi> listConsolidadoBsi, String periodo);
    
    Integer generarConsolidado(String periodo);
    
    Integer generarClientesNoSectorizados(String periodo);
    
    Paginator<ConsolidadoBsi> consultarCuadre(ConsolidadoBsi consolidado, Integer nroFilas, Integer pagina) throws SinResultadosException;

}
