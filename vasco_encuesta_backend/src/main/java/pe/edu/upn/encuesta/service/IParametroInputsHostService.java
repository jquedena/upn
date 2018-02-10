package pe.edu.upn.encuesta.service;

import pe.edu.upn.encuesta.entidad.ParametroInputsHost;
import com.indra.core.service.ScrudService2;

public interface IParametroInputsHostService extends ScrudService2<ParametroInputsHost> {

    ParametroInputsHost obtener(long idParametroInputsHost);
    void modificar(ParametroInputsHost objParametroInputsHost);
}
