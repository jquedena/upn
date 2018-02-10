package pe.edu.upn.encuesta.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IConsolidadoInputsHostDAO;
import pe.edu.upn.encuesta.entidad.ConsolidadoInputsHost;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.exception.SinResultadosException;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.NamedConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * Anexo1, Anexo8, Saldos Diarios, Otros Disponibles Restringidos
 * @author JUAN
 *
 */
@Repository("consolidadoInputsHostDAO")
public class ConsolidadoInputsHostDAO extends HibernateEntidadDAO2<ConsolidadoInputsHost> implements IConsolidadoInputsHostDAO {

    private static final long serialVersionUID = 1L;

    @Override
    public ConsolidadoInputsHost obtener(long id) {
        return obtener(id, ConsolidadoInputsHost.class);
    }

    @Override
    protected Consulta<ConsolidadoInputsHost> crearConsultaPaginado(
            ConsolidadoInputsHost item) {
        CriteriaConsulta<ConsolidadoInputsHost> consulta = new CriteriaConsulta<ConsolidadoInputsHost>(getSession());
        Objeto oItem = new Objeto(ConsolidadoInputsHost.class.getCanonicalName(), item);
        return consulta.agregarEntidad(oItem)
            .agregarCondicion(Condicion.igualA(oItem, "id"))
            .agregarCondicion(Condicion.igualA(oItem, "cuentaContable"))
            .agregarCondicion(Condicion.contiene(oItem, "descripcion"))
            .agregarCondicion(Condicion.igualA(oItem, "codigoCentral"))
            .agregarCondicion(Condicion.igualA(oItem, "fuente"))
            .agregarCondicion(Condicion.igualA(oItem, "periodo"))
            .agregarCondicion(Condicion.igualA(oItem, "divisa"))
            .ordenarAsc(oItem, "id");
    }

    @Override
    public List<ConsolidadoInputsHost> resumen(
            ConsolidadoInputsHost consolidadoInputsHost, Integer nroFilas,
            Integer pagina) throws SinResultadosException {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("P_PERIODO", consolidadoInputsHost.getPeriodo());
        params.put("P_FUENTE", consolidadoInputsHost.getFuente());
        NamedConsulta<ConsolidadoInputsHost> consulta = new NamedConsulta<ConsolidadoInputsHost>(getSession(), "resumen", params);
        return consulta.listar(nroFilas, pagina);
    }

    @Override
    public Long contarResumen(ConsolidadoInputsHost consolidadoInputsHost) {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("P_PERIODO", consolidadoInputsHost.getPeriodo());
        params.put("P_FUENTE", consolidadoInputsHost.getFuente());
        NamedConsulta<ConsolidadoInputsHost> consulta = new NamedConsulta<ConsolidadoInputsHost>(getSession(), "resumen", params);
        return consulta.contar();
    }
    
}
