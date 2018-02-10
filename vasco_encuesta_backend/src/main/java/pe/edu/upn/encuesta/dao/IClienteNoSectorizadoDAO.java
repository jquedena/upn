/**
 * 
 */
package pe.edu.upn.encuesta.dao;

import java.util.List;

import pe.edu.upn.encuesta.entidad.ClienteNoSectorizado;
import com.indra.core.dao.EntidadDAO2;

/**
 * @author JUAN
 *
 */
public interface IClienteNoSectorizadoDAO extends 
		EntidadDAO2<ClienteNoSectorizado> {

    /**
     * Inserta un cliente no sectorizado
     * @param objClienteNoSectorizado
     */
    void insertar(ClienteNoSectorizado objClienteNoSectorizado);
    
    void modificarSectorMasivo(List<Long> listId, List<ClienteNoSectorizado> listClienteNoSectorizados,String sectorSeleccionado,String fecha);

	//void modificarSectorMasivo(List<Long> listId, String sectorSeleccionado);
    
    
}
