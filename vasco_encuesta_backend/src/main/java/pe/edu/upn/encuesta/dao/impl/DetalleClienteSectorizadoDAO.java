/**
 * 
 */
package pe.edu.upn.encuesta.dao.impl;

import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IDetalleClienteSectorizadoDAO;
import pe.edu.upn.encuesta.entidad.DetalleClienteSectorizado;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * @author JUAN
 *
 */

@Repository("detalleClienteSectorizadoDAO")
public class DetalleClienteSectorizadoDAO extends HibernateEntidadDAO2<DetalleClienteSectorizado> implements IDetalleClienteSectorizadoDAO {

	private static final long serialVersionUID = 1L;

	@Override
    public DetalleClienteSectorizado obtener(long id) {
        return obtener(id, DetalleClienteSectorizado.class);
    }
	
	

	@Override
	public void modificar(DetalleClienteSectorizado objDetalleClienteSectorizado) {
		DetalleClienteSectorizado objDetalleClienteSectorizadoRepositorio = this.obtener(objDetalleClienteSectorizado.getId());

		if (objDetalleClienteSectorizado.getNombres() != null) {
            objDetalleClienteSectorizadoRepositorio.setNombres(objDetalleClienteSectorizado.getNombres());
        }
		
        if (objDetalleClienteSectorizado.getPeriodo() != null) {
            objDetalleClienteSectorizadoRepositorio.setPeriodo(objDetalleClienteSectorizado.getPeriodo());
        }
        
        if (objDetalleClienteSectorizado.getCuentaContable() != null) {
            objDetalleClienteSectorizadoRepositorio.setCuentaContable(objDetalleClienteSectorizado.getCuentaContable());
        }

        if (objDetalleClienteSectorizado.getCodigoSector() != null) {
            objDetalleClienteSectorizadoRepositorio.setCodigoSector(objDetalleClienteSectorizado.getCodigoSector());
        }

        if (objDetalleClienteSectorizado.getCodigoCentral() != null) {
            objDetalleClienteSectorizadoRepositorio.setCodigoCentral(objDetalleClienteSectorizado.getCodigoCentral());
        }

        if (objDetalleClienteSectorizado.getNumeroDocumento() != null) {
            objDetalleClienteSectorizadoRepositorio.setNumeroDocumento(objDetalleClienteSectorizado.getNumeroDocumento());
        }

        if (objDetalleClienteSectorizado.getSaldoMonedaNacional()!= null) {
            objDetalleClienteSectorizadoRepositorio.setSaldoMonedaNacional(objDetalleClienteSectorizado.getSaldoMonedaNacional());
        }
        
        if (objDetalleClienteSectorizado.getCiiu()!= null) {
            objDetalleClienteSectorizadoRepositorio.setCiiu(objDetalleClienteSectorizado.getCiiu());
        }
        
        if (objDetalleClienteSectorizado.getCorasu()!= null) {
            objDetalleClienteSectorizadoRepositorio.setCorasu(objDetalleClienteSectorizado.getCorasu());
        }
        
        if (objDetalleClienteSectorizado.getOrigen()!= null) {
            objDetalleClienteSectorizadoRepositorio.setOrigen(objDetalleClienteSectorizado.getOrigen());
        }
        
        if (objDetalleClienteSectorizado.getPecnares()!= null) {
            objDetalleClienteSectorizadoRepositorio.setPecnares(objDetalleClienteSectorizado.getPecnares());
        }
        
        if (objDetalleClienteSectorizado.getTipoDocumento()!= null) {
            objDetalleClienteSectorizadoRepositorio.setTipoDocumento(objDetalleClienteSectorizado.getTipoDocumento());
        }

        if (objDetalleClienteSectorizado.getEstado() != null) {
            objDetalleClienteSectorizadoRepositorio.setEstado(objDetalleClienteSectorizado.getEstado());
        }
        
        objDetalleClienteSectorizadoRepositorio.setUsuarioModificacion(objDetalleClienteSectorizado.getUsuarioModificacion());
        objDetalleClienteSectorizadoRepositorio.setFechaModificacion(objDetalleClienteSectorizado.getFechaModificacion());

        super.actualizar(objDetalleClienteSectorizadoRepositorio);
		
	}



	@Override
	protected Consulta<DetalleClienteSectorizado> crearConsultaPaginado(DetalleClienteSectorizado item) {
		Objeto oCliente = new Objeto(DetalleClienteSectorizado.class.getCanonicalName(), item);
        CriteriaConsulta<DetalleClienteSectorizado> consulta = new CriteriaConsulta<DetalleClienteSectorizado>(getSession());
        return consulta.agregarEntidad(oCliente)
	            .agregarCondicion(Condicion.igualA(oCliente, "id"))
	            .agregarCondicion(Condicion.igualA(oCliente, "periodo"))
	            .agregarCondicion(Condicion.comienzaCon(oCliente, "cuentaContable"))
	            .agregarCondicion(Condicion.comienzaCon(oCliente, "codigoSector"))
	            .agregarCondicion(Condicion.igualA(oCliente, "codigoCentral"))
	            .agregarCondicion(Condicion.igualA(oCliente, "numeroDocumento"))
	            .agregarCondicion(Condicion.igualA(oCliente, "saldoMonedaNacional"))
	            .ordenarAsc(oCliente, "numeroDocumento");
	}

}