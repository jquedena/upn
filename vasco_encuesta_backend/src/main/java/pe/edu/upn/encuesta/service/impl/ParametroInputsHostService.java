package pe.edu.upn.encuesta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




import pe.edu.upn.encuesta.dao.IParametroInputsHostDAO;
import pe.edu.upn.encuesta.entidad.ParametroInputsHost;
import pe.edu.upn.encuesta.service.IParametroInputsHostService;
import com.indra.core.service.impl.HibernateScrudService2;

@Service("parametroInputsHostService")
public class ParametroInputsHostService 
    extends HibernateScrudService2<ParametroInputsHost, IParametroInputsHostDAO> 
    implements IParametroInputsHostService {

    private static final long serialVersionUID = 1L;

    
    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("parametroInputsHostDAO") IParametroInputsHostDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }

    @Override
    @Transactional(readOnly = true)
    public ParametroInputsHost obtener(long idParametroInputsHost) {
        return getHibernateDAO().obtener(idParametroInputsHost);
    }
    
    @Override
    @Transactional
    public void modificar(ParametroInputsHost objParametroInputsHost) {
        getHibernateDAO().modificar(objParametroInputsHost);
    }

}
