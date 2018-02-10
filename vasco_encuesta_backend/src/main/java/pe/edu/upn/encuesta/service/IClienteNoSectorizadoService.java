package pe.edu.upn.encuesta.service;

import java.util.Date;
import java.util.List;

import pe.edu.upn.encuesta.entidad.ClienteNoSectorizado;
import com.indra.core.service.ScrudService2;

public interface IClienteNoSectorizadoService extends ScrudService2<ClienteNoSectorizado>{

    void modificarSector(List<ClienteNoSectorizado> listClienteNoSectorizados, String sectorSeleccionado,Date fecha,String nombreFuente);
}
