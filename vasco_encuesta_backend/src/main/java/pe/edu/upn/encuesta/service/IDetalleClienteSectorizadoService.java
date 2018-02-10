package pe.edu.upn.encuesta.service;

import pe.edu.upn.encuesta.entidad.DetalleClienteSectorizado;
import com.indra.core.service.ScrudService2;

public interface IDetalleClienteSectorizadoService extends ScrudService2<DetalleClienteSectorizado> {

	/**
     * Ejecuta el m\u00E9todo 
     * @param idDetalleClienteSectorizado
     * @return Detalle de un Cliente Sectorizado, nulo si no existe
     */
    DetalleClienteSectorizado obtener(long idDetalleClienteSectorizado);
    
    /**
     * Ejecuta el m\u00E9todo modificar
     * @param objDetalleClienteSectorizado
     */
    void modificar(DetalleClienteSectorizado objDetalleClienteSectorizado);
    
}
