/**
 * 
 */
package pe.edu.upn.encuesta.dao;

import pe.edu.upn.encuesta.entidad.DetalleClienteSectorizado;
import com.indra.core.dao.EntidadDAO2;

/**
 * @author JUAN
 *
 */
public interface IDetalleClienteSectorizadoDAO extends
		EntidadDAO2<DetalleClienteSectorizado> {

    /**
     * Modifica un detalle de cliente sectorizado
     * @param objDetalleClienteSectorizado
     */
    void modificar(DetalleClienteSectorizado objDetalleClienteSectorizado);

}