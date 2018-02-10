package pe.edu.upn.encuesta.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.dao.IConsolidadoBsiDAO;
import pe.edu.upn.encuesta.entidad.ConsolidadoBsi;
import pe.edu.upn.encuesta.service.IConsolidadoBsiService;
import com.indra.core.exception.SinResultadosException;
import com.indra.core.service.impl.HibernateScrudService2;
import com.indra.util.NumeroUtil;
import com.indra.util.db.Paginator;

/**
 * @author JUAN
 *
 */
@Service("consolidadoBsiService")
public class ConsolidadoBsiService extends HibernateScrudService2<ConsolidadoBsi, IConsolidadoBsiDAO> implements IConsolidadoBsiService {

    private static final long serialVersionUID = 1L;

    @Autowired
    @Override
    protected void setEntidadDAO(@Qualifier("consolidadoBsiDAO") IConsolidadoBsiDAO entidadDAO) {
        super.setEntidadDAO(entidadDAO);
    }
    
    @Override
    @Transactional
    public boolean cuadrarDiferencias(List<BigDecimal> listaIds) {
        List<Long> listaIdsBsi = new ArrayList<Long>();
        for (BigDecimal idIter : listaIds) {
            listaIdsBsi.add(idIter.longValue());
        }
        
        return getHibernateDAO().cuadrarDiferencias(listaIdsBsi);
    }
    
    @Override
    @Transactional
    public List<BigDecimal> obtenerSectorMayorSaldo(List<ConsolidadoBsi> listConsolidadoBsi, String periodo){
         List<String> listaCuentas = new ArrayList<String>();
         String usaCuenta = "-1";
         if(listConsolidadoBsi != null && !listConsolidadoBsi.isEmpty()){
            for (ConsolidadoBsi c : listConsolidadoBsi) {
                listaCuentas.add(c.getCuentaContable());
            }
            usaCuenta = "0";
         } else {
             listaCuentas.add("-1");
         }
         
         return getHibernateDAO().obtenerSectorMayorSaldo(listaCuentas, periodo, usaCuenta);
    }
    
    /* -------  */
    
    @Override
    @Transactional
    public boolean cuadrarActualizado(List<ConsolidadoBsi> listConsolidadoBsi, String periodo){
        List<String> listaCuentas = new ArrayList<String>();

        String usaCuenta = "-1";
        if(listConsolidadoBsi != null && !listConsolidadoBsi.isEmpty()){
           for (ConsolidadoBsi c : listConsolidadoBsi) {
               listaCuentas.add(c.getCuentaContable());
           }
           usaCuenta = "0";
        } else {
            listaCuentas.add("-1");
        }
        
        return getHibernateDAO().cuadrarActualizado(listaCuentas, periodo, usaCuenta);
   }
    
    /* -------  */

    @Override
    @Transactional
    public Integer generarConsolidado(String periodo) {
        return getHibernateDAO().generarConsolidado(periodo);
    }

    @Override
    @Transactional
    public Integer generarClientesNoSectorizados(String periodo) {
        return getHibernateDAO().generarClientesNoSectorizados(periodo);
    }

    @Override
    @Transactional(readOnly = true)
    public Paginator<ConsolidadoBsi> consultarCuadre(ConsolidadoBsi consolidadoBsi, Integer nroFilas, Integer pagina) throws SinResultadosException {
        Paginator<ConsolidadoBsi> paginator = createPaginator(nroFilas, pagina, getHibernateDAO().contarConsultarCuadre(consolidadoBsi));
        
        if(NumeroUtil.isPositiveWithoutZero(nroFilas) && NumeroUtil.isPositiveWithoutZero(pagina)) {
            paginator.setListaEntidades(getHibernateDAO().consultarCuadre(consolidadoBsi, nroFilas, pagina));
        } else {
            paginator.setListaEntidades(getHibernateDAO().consultarCuadre(consolidadoBsi, nroFilas, null));
        }
        
        return paginator;
    }
 
}
