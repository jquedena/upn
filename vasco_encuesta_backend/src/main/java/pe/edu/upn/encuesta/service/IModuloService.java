package pe.edu.upn.encuesta.service;

import java.util.List;

import pe.edu.upn.encuesta.entidad.Modulo;
import com.indra.core.service.ScrudService2;

public interface IModuloService extends ScrudService2<Modulo> {

    boolean cambiarEstado(List<Modulo> listaPuestoIds, String estado);
}
