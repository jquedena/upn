/**
 * 
 */
package pe.edu.upn.encuesta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.edu.upn.encuesta.dao.IArchivoModeloDAO;
import pe.edu.upn.encuesta.entidad.ArchivoModelo;
import pe.edu.upn.encuesta.service.IArchivoModeloService;
import com.indra.core.service.impl.HibernateScrudService2;

/**
 * @author JUAN
 *
 */
@Service("archivoModeloService")
public class ArchivoModeloService  extends HibernateScrudService2<ArchivoModelo, IArchivoModeloDAO> implements IArchivoModeloService {

    private static final long serialVersionUID = 1L;

    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("archivoModeloDAO") IArchivoModeloDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }

}
