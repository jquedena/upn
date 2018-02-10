/**
 * 
 */
package pe.edu.upn.encuesta.dao;

import pe.edu.upn.encuesta.entidad.AgentesEconomicos;
import com.indra.core.dao.EntidadDAO2;

/**
 * @author JUAN
 *
 */
public interface IAgentesEconomicosDAO extends 
		EntidadDAO2<AgentesEconomicos> {
	
    /**
     * Inserta un agente econ\u00f3mico
     * @param objAgenteEconomico
     */
    void insertar(AgentesEconomicos objAgenteEconomico);

    /**
     * Modifica un agente econ\u00f3mico
     * @param objAgenteEconomico
     */
    void modificar(AgentesEconomicos objAgenteEconomico);

}
