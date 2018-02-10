package pe.edu.upn.encuesta.dao;

import java.math.BigDecimal;
import java.util.List;

import pe.edu.upn.encuesta.entidad.ConsolidadoBsi;
import com.indra.core.dao.EntidadDAO2;
import com.indra.core.exception.SinResultadosException;

public interface IConsolidadoBsiDAO extends EntidadDAO2<ConsolidadoBsi> {

    boolean cuadrarDiferencias(List<Long> listaIds);
    
    boolean cuadrarActualizado(List<String> listaCuentas, String periodo, String usaCuenta);

    List<BigDecimal> obtenerSectorMayorSaldo(List<String> listaCuentas, String periodo, String usaCuenta);

    Integer generarConsolidado(String periodo);

    Integer generarClientesNoSectorizados(String periodo);
    
    List<ConsolidadoBsi> consultarCuadre(ConsolidadoBsi consolidadoBsi, Integer nroFilas, Integer pagina) throws SinResultadosException;
    
    Long contarConsultarCuadre(ConsolidadoBsi consolidadoBsi);

}