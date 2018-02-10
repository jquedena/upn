/**
 * 
 */
package pe.edu.upn.encuesta.dao.impl;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IConsolidadoBsiDAO;
import pe.edu.upn.encuesta.entidad.ConsolidadoBsi;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.exception.SinResultadosException;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.NamedConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * @author JUAN
 *
 */

@Repository("consolidadoBsiDAO")
public class ConsolidadoBsiDAO extends HibernateEntidadDAO2<ConsolidadoBsi> implements IConsolidadoBsiDAO {
    
    private static final long serialVersionUID = 1L;

    @Override
    public ConsolidadoBsi obtener(long id) {
        return obtener(id, ConsolidadoBsi.class);
    }

    @Override
    protected Consulta<ConsolidadoBsi> crearConsultaPaginado(
            ConsolidadoBsi item) {
        CriteriaConsulta<ConsolidadoBsi> consulta = new CriteriaConsulta<ConsolidadoBsi>(getSession());
        Objeto oItem = new Objeto(ConsolidadoBsi.class.getCanonicalName(), item);
        
        if (item.getColOrden() != null)
        {
        	if(item.getOrden().equals("asc")){
        		
	        	return consulta.agregarEntidad(oItem)
	                    .agregarCondicion(Condicion.igualA(oItem, "id"))
	                    .agregarCondicion(Condicion.igualA(oItem, "periodo"))
	                    .agregarCondicion(Condicion.igualA(oItem, "versionBsi"))
	                    .agregarCondicion(Condicion.comienzaCon(oItem, "cuentaContable"))
	                    .agregarCondicion(Condicion.comienzaCon(oItem, "codigoSector"))
	                    .agregarCondicion(Condicion.igualA(oItem, "opcion"))
	                    .agregarCondicion(Condicion.mayorA(oItem, "diferencia"))
	                    .ordenarAsc(oItem, item.getColOrden());
        	}
        	if(item.getOrden().equals("desc")){
        		return consulta.agregarEntidad(oItem)
        	            .agregarCondicion(Condicion.igualA(oItem, "id"))
        	            .agregarCondicion(Condicion.igualA(oItem, "periodo"))
        	            .agregarCondicion(Condicion.igualA(oItem, "versionBsi"))
        	            .agregarCondicion(Condicion.comienzaCon(oItem, "cuentaContable"))
        	            .agregarCondicion(Condicion.comienzaCon(oItem, "codigoSector"))
        	            .agregarCondicion(Condicion.igualA(oItem, "opcion"))
        	            .agregarCondicion(Condicion.mayorA(oItem, "diferencia"))
        	        	.ordenarDesc(oItem, item.getColOrden());		
        	}
        	
        	else {
        		return null;
        	}
        }
        else{
	        return consulta.agregarEntidad(oItem)
	            .agregarCondicion(Condicion.igualA(oItem, "id"))
	            .agregarCondicion(Condicion.igualA(oItem, "periodo"))
	            .agregarCondicion(Condicion.igualA(oItem, "versionBsi"))
	            .agregarCondicion(Condicion.comienzaCon(oItem, "cuentaContable"))
	            .agregarCondicion(Condicion.comienzaCon(oItem, "codigoSector"))
	            .agregarCondicion(Condicion.igualA(oItem, "opcion"))
	            .agregarCondicion(Condicion.mayorA(oItem, "diferencia"));
	            //.ordenarDesc(oItem, "orden");
        }
            	//.ordenarDesc(oItem, "saldoFin");
    }
    
    @Override
    public boolean cuadrarDiferencias(List<Long> listaIds) {
        Query q = getSession().getNamedQuery("cuadrar");
        q.setParameterList("listaIds", listaIds);
        q.executeUpdate();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BigDecimal> obtenerSectorMayorSaldo(List<String> listaCuentas, String periodo, String usaCuenta){
        SQLQuery q = (SQLQuery) getSession().getNamedQuery("obtenerSector");
        q.setParameterList("listaCuentas", listaCuentas);
        q.setParameter("p_periodo", periodo);
        q.setParameter("p_usa_cuenta", usaCuenta);
        return (List<BigDecimal>)q.list();
    }
    
    //@SuppressWarnings("unchecked")
    @Override
    public boolean cuadrarActualizado(List<String> listaCuentas, String periodo, String usaCuenta){
        SQLQuery q = (SQLQuery) getSession().getNamedQuery("cuadrarActualizado");
        q.setParameterList("listaCuentas", listaCuentas);
        q.setParameter("p_periodo", periodo);
        q.setParameter("p_usa_cuenta", usaCuenta);
        q.executeUpdate();
        return true;
    }
    
    @Override
    public Integer generarConsolidado(String periodo) {
        Query q = getSession().getNamedQuery("generar");
        q.setParameter("periodo", periodo);
        return (Integer)q.uniqueResult();
    }
    
    @Override
    public Integer generarClientesNoSectorizados(String periodo) {
        Query q = getSession().getNamedQuery("generarNoSectorizados");
        q.setParameter("periodo", periodo);
        return (Integer)q.uniqueResult();
    }

    @Override
    public List<ConsolidadoBsi> consultarCuadre(
            ConsolidadoBsi consolidadoBsi, Integer nroFilas, Integer pagina)
            throws SinResultadosException {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("P_PERIODO", consolidadoBsi.getPeriodo());
        params.put("P_CTA_CBLE", consolidadoBsi.getCuentaContable());
        params.put("P_FLAG_DIFERENTE", consolidadoBsi.getDiferencia());
        
//        if((consolidadoBsi.getColOrden() == "diferencia") && (consolidadoBsi.getOrden().toString() == "desc")){
//        	NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaDesc", params);
//        	return consulta.listar(nroFilas, pagina);
//        }
//        if((consolidadoBsi.getColOrden() == "diferencia") && (consolidadoBsi.getOrden().toString() == "asc")){
//        	NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaAsc", params);
//        	return consulta.listar(nroFilas, pagina);
//        }
//        
//        NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "obtenerCuadre", params);
//        return consulta.listar(nroFilas, pagina);
        
        if("diferencia".equals(consolidadoBsi.getColOrden())){	
        	if("desc".equals(consolidadoBsi.getOrden())){
        		NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaDesc", params);
        		return consulta.listar(nroFilas, pagina);
        	}
        	if("asc".equals(consolidadoBsi.getOrden())){
        		NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaAsc", params);
        		return consulta.listar(nroFilas, pagina);
        	}
        	else{
        		return null;
        	}
        	
        }
        else{
	        NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "obtenerCuadre", params);
	        return consulta.listar(nroFilas, pagina);
        }
    }

    @Override
    public Long contarConsultarCuadre(ConsolidadoBsi consolidadoBsi) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("P_PERIODO", consolidadoBsi.getPeriodo());
        params.put("P_CTA_CBLE", consolidadoBsi.getCuentaContable());
        params.put("P_FLAG_DIFERENTE", consolidadoBsi.getDiferencia());
        
//        if((consolidadoBsi.getColOrden() == "diferencia") && (consolidadoBsi.getOrden().toString() == "desc")){
//        	NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaDesc", params);
//        	return consulta.contar();
//        }
//        if((consolidadoBsi.getColOrden() == "diferencia") && (consolidadoBsi.getOrden().toString() == "asc")){
//        	NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaAsc", params);
//        	return consulta.contar();
//        }
        
        if("diferencia".equals(consolidadoBsi.getColOrden())){
        	if("desc".equals(consolidadoBsi.getOrden())){
        		NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaDesc", params);
        		return consulta.contar();
        	}
        	if("asc".equals(consolidadoBsi.getOrden())){
        		NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "CuadreDiferenciaAsc", params);
            	return consulta.contar();
        	}
        	else{
        		return null;
        	}
        	
        }
        else{
	        NamedConsulta<ConsolidadoBsi> consulta = new NamedConsulta<ConsolidadoBsi>(getSession(), "obtenerCuadre", params);
	        return consulta.contar();
        }
    }
 
}
