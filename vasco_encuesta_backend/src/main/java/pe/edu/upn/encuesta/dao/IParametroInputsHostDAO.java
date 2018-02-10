package pe.edu.upn.encuesta.dao;


import pe.edu.upn.encuesta.entidad.ParametroInputsHost;
import com.indra.core.dao.EntidadDAO2;

public interface IParametroInputsHostDAO extends
EntidadDAO2<ParametroInputsHost>{
	
	void modificar(ParametroInputsHost objParametroInputsHost);

}
