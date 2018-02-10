package pe.edu.upn.encuesta.dao;

import java.util.List;

import pe.edu.upn.encuesta.entidad.Modulo;
import com.indra.core.dao.EntidadDAO2;

public interface IModuloDAO extends EntidadDAO2<Modulo> {

	boolean cambiarEstado(List<Long> listaModulosIds, String estado);
}
