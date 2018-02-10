/**
 * 
 */
package pe.edu.upn.encuesta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.dao.IClienteNoSectorizadoDAO;
import pe.edu.upn.encuesta.entidad.AgentesEconomicos;
import pe.edu.upn.encuesta.entidad.ClienteNoSectorizado;
import pe.edu.upn.encuesta.service.IClienteNoSectorizadoService;
import com.indra.core.service.impl.HibernateScrudService2;

/**
 * @author JUAN
 *
 */

@Service("clienteNoSectorizadoService")
public class ClienteNoSectorizadoService extends HibernateScrudService2<ClienteNoSectorizado, IClienteNoSectorizadoDAO> implements IClienteNoSectorizadoService {

    private static final long serialVersionUID = 1L;

    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("clienteNoSectorizadoDAO") IClienteNoSectorizadoDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }

    @Override
    @Transactional
    public void modificarSector(List<ClienteNoSectorizado> listClienteNoSectorizados, String sectorSeleccionado,Date fecha,String nombreFuente) {
    	
    	List<AgentesEconomicos> listAgentesEconomicos = new ArrayList<AgentesEconomicos>();
    	
    	for (ClienteNoSectorizado clienteNoSectorizado : listClienteNoSectorizados) {
    		AgentesEconomicos agenteEconomico = new AgentesEconomicos();
    		agenteEconomico.setDocumento(clienteNoSectorizado.getDocumento());
    		agenteEconomico.setCliente(clienteNoSectorizado.getNombres());
    		agenteEconomico.setSector(sectorSeleccionado);
    		agenteEconomico.setPeriodo(fecha);
    		
    		listAgentesEconomicos.add(agenteEconomico);
		}
    	
    	
    	
    	//getHibernateDAO().modificarSectorMasivo(listClienteNoSectorizados,listId,sectorSeleccionado,fecha);
    	//if(listClienteNoSectorizados != null && !listClienteNoSectorizados.isEmpty()){
        //getHibernateDAO().modificarSector(listClienteNoSectorizados);
    	//}
    }
}

