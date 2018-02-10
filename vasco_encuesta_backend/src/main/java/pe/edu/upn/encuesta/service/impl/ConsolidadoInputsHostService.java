/**
 * 
 */
package pe.edu.upn.encuesta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.dao.IConsolidadoInputsHostDAO;
import pe.edu.upn.encuesta.entidad.ConsolidadoInputsHost;
import pe.edu.upn.encuesta.service.IConsolidadoInputsHostService;
import com.indra.core.exception.BussinesException;
import com.indra.core.exception.SinResultadosException;
import com.indra.core.service.impl.HibernateScrudService2;
import com.indra.util.NumeroUtil;
import com.indra.util.db.Paginator;

/**
 * @author JUAN
 *
 */
@Service("consolidadoInputsHostService")
public class ConsolidadoInputsHostService extends HibernateScrudService2<ConsolidadoInputsHost, IConsolidadoInputsHostDAO>
        implements IConsolidadoInputsHostService {

    private static final long serialVersionUID = 1L;

    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("consolidadoInputsHostDAO") IConsolidadoInputsHostDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }

    @Transactional(readOnly = true)
    @Override
    public Paginator<ConsolidadoInputsHost> resumen(ConsolidadoInputsHost consolidadoInputsHost, Integer nroFilas,Integer pagina) throws BussinesException {
        Paginator<ConsolidadoInputsHost> paginator=null;
		
			try {
				paginator = createPaginator(nroFilas, pagina, getHibernateDAO().contarResumen(consolidadoInputsHost));
			
        
        if(NumeroUtil.isPositiveWithoutZero(nroFilas) && NumeroUtil.isPositiveWithoutZero(pagina)) {
            paginator.setListaEntidades(getHibernateDAO().resumen(consolidadoInputsHost, nroFilas, pagina));
        } else {
            paginator.setListaEntidades(getHibernateDAO().resumen(consolidadoInputsHost, nroFilas, null));
        }
		
			} catch (SinResultadosException e) {
				
				
				throw new BussinesException("No se encontraron resultados para el Resumen");
			}
		
        return paginator;
    }

}
