/**
 * 
 */
package pe.edu.upn.encuesta.service;

import pe.edu.upn.encuesta.entidad.AgentesEconomicos;
import com.indra.core.service.ScrudService2;

/**
 * @author JUAN
 *
 */
public interface IAgentesEconomicosService extends ScrudService2<AgentesEconomicos> {
	
	/**
     * Ejecuta el m\u00E9todo 
     * @param idAgentesEconomicos
     * @return Un Agentes Econcon\u00f3micomicos, nulo si no existe
     */
    AgentesEconomicos obtener(long idAgentesEconomicos);
    
    /**
     * Ejecuta el m\u00E9todo modificar
     * @param objAgentesEconomicos
     */
    void modificar(AgentesEconomicos objAgentesEconomicos);
    
    void eliminar(AgentesEconomicos agente);

}
