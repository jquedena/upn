package pe.edu.upn.encuesta.dao.impl;

import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IParametroInputsHostDAO;
import pe.edu.upn.encuesta.entidad.ParametroInputsHost;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;


@Repository("parametroInputsHostDAO")

public class ParametroInputsHostDAO extends HibernateEntidadDAO2<ParametroInputsHost> implements IParametroInputsHostDAO{

	private static final long serialVersionUID = 1L;
	
	@Override
    public ParametroInputsHost obtener(long id) {
        return obtener(id, ParametroInputsHost.class);
    }

	@Override
	protected Consulta<ParametroInputsHost> crearConsultaPaginado(ParametroInputsHost item) {
		Objeto oCliente = new Objeto(ParametroInputsHost.class.getCanonicalName(), item);
		CriteriaConsulta<ParametroInputsHost> consulta = new CriteriaConsulta<ParametroInputsHost>(getSession());
		// TODO Auto-generated method stub
		return consulta.agregarEntidad(oCliente)
	            .agregarCondicion(Condicion.igualA(oCliente, "id"))
	            //.agregarCondicion(Condicion.igualA(oCliente, "periodo"))
	            .agregarCondicion(Condicion.igualA(oCliente, "tipoParametro"))
	            .agregarCondicion(Condicion.igualA(oCliente, "codigoCentral"))
	            .agregarCondicion(Condicion.igualA(oCliente, "doi"))
	            .agregarCondicion(Condicion.igualA(oCliente, "cuentaContable"))
				.agregarCondicion(Condicion.igualA(oCliente, "estado"))
				.agregarCondicion(Condicion.contiene(oCliente, "emisor"))
				.agregarCondicion(Condicion.contiene(oCliente, "descripcion"))
				.agregarCondicion(Condicion.igualA(oCliente, "tipoDerivado"))
				.agregarCondicion(Condicion.contiene(oCliente, "producto"))
				.agregarCondicion(Condicion.igualA(oCliente, "codigoProducto"))
	            .ordenarAsc(oCliente, "id");
	}
	

	
	@Override
	public void modificar(ParametroInputsHost objParametroInputsHost) {
		ParametroInputsHost objParametroInputsHostRepositorio = this.obtener(objParametroInputsHost.getId());

		if (objParametroInputsHost.getTipoParametro() != null) {
            objParametroInputsHostRepositorio.setTipoParametro(objParametroInputsHost.getTipoParametro());
        }
		
        if (objParametroInputsHost.getDigito() != null) {
        	objParametroInputsHostRepositorio.setDigito(objParametroInputsHost.getDigito());
        }
        
        if (objParametroInputsHost.getProducto() != null) {
        	objParametroInputsHostRepositorio.setProducto(objParametroInputsHost.getProducto());
        }

        if (objParametroInputsHost.getMoneda() != null) {
        	objParametroInputsHostRepositorio.setMoneda(objParametroInputsHost.getMoneda());
        }

        if (objParametroInputsHost.getTipoDerivado() != null) {
        	objParametroInputsHostRepositorio.setTipoDerivado(objParametroInputsHost.getTipoDerivado());
        }

        if (objParametroInputsHost.getCuentaContable() != null) {
        	objParametroInputsHostRepositorio.setCuentaContable(objParametroInputsHost.getCuentaContable());
        }

        if (objParametroInputsHost.getEmisor() != null) {
        	objParametroInputsHostRepositorio.setEmisor(objParametroInputsHost.getEmisor());
        }

        if (objParametroInputsHost.getCodigoCentral() != null) {
        	objParametroInputsHostRepositorio.setCodigoCentral(objParametroInputsHost.getCodigoCentral());
        }
        
        if (objParametroInputsHost.getDoi() != null) {
        	objParametroInputsHostRepositorio.setDoi(objParametroInputsHost.getDoi());
        }
        
        if (objParametroInputsHost.getDescripcion() != null) {
        	objParametroInputsHostRepositorio.setDescripcion(objParametroInputsHost.getDescripcion());
        }
        
        if (objParametroInputsHost.getCodigoProducto() != null) {
        	objParametroInputsHostRepositorio.setCodigoProducto(objParametroInputsHost.getCodigoProducto());
        }
        
        if (objParametroInputsHost.getEstado() != null) {
        	objParametroInputsHostRepositorio.setEstado(objParametroInputsHost.getEstado());
        }
        
        objParametroInputsHostRepositorio.setUsuarioModificacion(objParametroInputsHost.getUsuarioModificacion());
        objParametroInputsHostRepositorio.setFechaModificacion(objParametroInputsHost.getFechaModificacion());

        super.actualizar(objParametroInputsHostRepositorio);
		
	}
	
}
