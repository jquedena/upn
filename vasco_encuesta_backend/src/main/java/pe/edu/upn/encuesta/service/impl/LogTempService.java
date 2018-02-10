/**
 * 
 */
package pe.edu.upn.encuesta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pe.edu.upn.encuesta.dao.ILogTempDAO;
import pe.edu.upn.encuesta.entidad.LogTemp;
import pe.edu.upn.encuesta.service.ILogTempService;
import com.indra.core.service.impl.HibernateScrudService2;

/**
 * @author JUAN
 *
 */
@Service("logTempService")
public class LogTempService 
    extends HibernateScrudService2<LogTemp, ILogTempDAO>
    implements ILogTempService {

    private static final long serialVersionUID = 1L;

    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("logTempDAO") ILogTempDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }

}
