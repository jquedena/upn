/**
 * 
 */
package pe.edu.upn.encuesta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.dao.IDetalleClienteSectorizadoDAO;
import pe.edu.upn.encuesta.entidad.DetalleClienteSectorizado;
import pe.edu.upn.encuesta.service.IDetalleClienteSectorizadoService;
import com.indra.core.service.impl.HibernateScrudService2;

/**
 * @author JUAN
 *
 */
@Service("detalleClienteSectorizadoService")
public class DetalleClienteSectorizadoService 
    extends HibernateScrudService2<DetalleClienteSectorizado, IDetalleClienteSectorizadoDAO> 
    implements IDetalleClienteSectorizadoService {

    private static final long serialVersionUID = 1L;

    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("detalleClienteSectorizadoDAO") IDetalleClienteSectorizadoDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleClienteSectorizado obtener(long idDetalleClienteSectorizado) {
        return getHibernateDAO().obtener(idDetalleClienteSectorizado);
    }

    @Override
    @Transactional
    public void modificar(DetalleClienteSectorizado objDetalleClienteSectorizado) {
        getHibernateDAO().modificar(objDetalleClienteSectorizado);
    }

}
