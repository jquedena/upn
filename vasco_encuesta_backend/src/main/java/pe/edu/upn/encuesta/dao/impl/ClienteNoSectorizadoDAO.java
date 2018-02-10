/**
 * 
 */
package pe.edu.upn.encuesta.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IClienteNoSectorizadoDAO;
import pe.edu.upn.encuesta.entidad.ClienteNoSectorizado;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * @author JUAN
 *
 */

@Repository("clienteNoSectorizadoDAO")
public class ClienteNoSectorizadoDAO extends HibernateEntidadDAO2<ClienteNoSectorizado> implements IClienteNoSectorizadoDAO {
	
	private static final long serialVersionUID = 1L;

	@Override
    public ClienteNoSectorizado obtener(long id) {
        return obtener(id, ClienteNoSectorizado.class);
    }
	
	@Override
	protected Consulta<ClienteNoSectorizado> crearConsultaPaginado(ClienteNoSectorizado item) {
		Objeto oCliente = new Objeto(ClienteNoSectorizado.class.getCanonicalName(), item);
        CriteriaConsulta<ClienteNoSectorizado> consulta = new CriteriaConsulta<ClienteNoSectorizado>(getSession());
        return consulta.agregarEntidad(oCliente)
	            .agregarCondicion(Condicion.igualA(oCliente, "id"))
	            .agregarCondicion(Condicion.igualA(oCliente, "periodo"))
	            .agregarCondicion(Condicion.igualA(oCliente, "codigoCentral"))
	            .agregarCondicion(Condicion.igualA(oCliente, "documento"))
	            .agregarCondicion(Condicion.igualA(oCliente, "saldo"))
	            .ordenarAsc(oCliente, "documento");
	}

	@Override
	public void insertar(ClienteNoSectorizado objClienteNoSectorizado) {
		super.agregar(objClienteNoSectorizado);
		
	}
	
	public void modificarSectorMasivo(List<Long> listId,List<ClienteNoSectorizado> listClienteNoSectorizados,String sectorSeleccionado,String fecha){
		
		Query q = getSession().getNamedQuery("actualizarSector");
		q.setParameterList("listClienteNoSectorizados", listClienteNoSectorizados);
        q.setParameterList("listaId", listId);
        q.setParameter("fecha", fecha);
        q.setParameter("sectorSeleccionado", sectorSeleccionado);
        q.executeUpdate();
	}
	
	
}
