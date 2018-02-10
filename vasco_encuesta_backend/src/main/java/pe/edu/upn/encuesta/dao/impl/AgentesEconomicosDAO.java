/**
 * 
 */
package pe.edu.upn.encuesta.dao.impl;

import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IAgentesEconomicosDAO;
import pe.edu.upn.encuesta.entidad.AgentesEconomicos;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * @author JUAN
 *
 */
@Repository("agentesEconomicosDAO")
public class AgentesEconomicosDAO extends HibernateEntidadDAO2<AgentesEconomicos> implements IAgentesEconomicosDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public AgentesEconomicos obtener(long id) {
        return obtener(id, AgentesEconomicos.class);
    }
    
    @Override
    protected Consulta<AgentesEconomicos> crearConsultaPaginado(AgentesEconomicos item) {
        Objeto oAgente = new Objeto(AgentesEconomicos.class.getCanonicalName(), item);
        CriteriaConsulta<AgentesEconomicos> consulta = new CriteriaConsulta<AgentesEconomicos>(getSession());
        return consulta.agregarEntidad(oAgente)
                .agregarCondicion(Condicion.igualA(oAgente, "id"))
                .agregarCondicion(Condicion.igualA(oAgente, "periodo"))
                .agregarCondicion(Condicion.igualA(oAgente, "documento"))
                .agregarCondicion(Condicion.comienzaCon(oAgente, "sector"))
                .agregarCondicion(Condicion.contiene(oAgente, "cliente"))
                .ordenarAsc(oAgente, "documento");
    }
    
    @Override
    public void insertar(AgentesEconomicos objAgenteEconomico) {
        super.agregar(objAgenteEconomico);
    }
    
    @Override
    public void modificar(AgentesEconomicos objAgenteEconomico) {
        AgentesEconomicos objAgenteRepositorio = this.obtener(objAgenteEconomico.getId());

        if (objAgenteEconomico.getPeriodo() != null) {
            objAgenteRepositorio.setPeriodo(objAgenteEconomico.getPeriodo());
        }
        
        if (objAgenteEconomico.getJobInstanceId() != null) {
            objAgenteRepositorio.setJobInstanceId(objAgenteEconomico.getJobInstanceId());
        }

        if (objAgenteEconomico.getSector() != null) {
            objAgenteRepositorio.setSector(objAgenteEconomico.getSector());
        }

        if (objAgenteEconomico.getDocumento() != null) {
            objAgenteRepositorio.setDocumento(objAgenteEconomico.getDocumento());
        }

        if (objAgenteEconomico.getCliente() != null) {
            objAgenteRepositorio.setCliente(objAgenteEconomico.getCliente());
        }

        if (objAgenteEconomico.getFuente() != null) {
            objAgenteRepositorio.setFuente(objAgenteEconomico.getFuente());
        }

        if (objAgenteEconomico.getEstado() != null) {
            objAgenteRepositorio.setEstado(objAgenteEconomico.getEstado());
        }
        
        objAgenteRepositorio.setUsuarioModificacion(objAgenteEconomico.getUsuarioModificacion());
        objAgenteRepositorio.setFechaModificacion(objAgenteEconomico.getFechaModificacion());

        super.actualizar(objAgenteRepositorio);
    }

}
