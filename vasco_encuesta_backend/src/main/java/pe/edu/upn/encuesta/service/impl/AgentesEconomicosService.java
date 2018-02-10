/**
 * 
 */
package pe.edu.upn.encuesta.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.dao.IAgentesEconomicosDAO;
import pe.edu.upn.encuesta.entidad.AgentesEconomicos;
import pe.edu.upn.encuesta.service.IAgentesEconomicosService;
import com.indra.core.service.impl.HibernateScrudService2;

/**
 * @author JUAN
 *
 */
@Service("agentesEconomicosService")
public class AgentesEconomicosService extends HibernateScrudService2<AgentesEconomicos, IAgentesEconomicosDAO> implements IAgentesEconomicosService {

    private static final long serialVersionUID = 1L;

    @Resource(name = "agentesEconomicosDAO")
    private IAgentesEconomicosDAO agentesEconomicosDAO;
    
    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("agentesEconomicosDAO") IAgentesEconomicosDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }

    @Override
    @Transactional(readOnly = true)
    public AgentesEconomicos obtener(long idAgentesEconomicos) {
        return agentesEconomicosDAO.obtener(idAgentesEconomicos);
    }

    @Override
    @Transactional
    public void modificar(AgentesEconomicos objAgentesEconomicos) {
        agentesEconomicosDAO.modificar(objAgentesEconomicos);
    }

    @Override
    @Transactional
    public void eliminar(AgentesEconomicos agente) {
        AgentesEconomicos temp = agentesEconomicosDAO.obtener(agente.getId());
        agentesEconomicosDAO.eliminar(temp);
    }
}
