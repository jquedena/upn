/**
 * 
 */
package pe.edu.upn.encuesta.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.upn.encuesta.Constante;
import com.bbva.balsec.batch.ConfigureProcedureTasklet;
import com.bbva.balsec.batch.ConfigureTasklet;
import com.bbva.balsec.batch.JobLoadFactory;
import pe.edu.upn.encuesta.entidad.ConsolidadoBsi;
import pe.edu.upn.encuesta.service.IConsolidadoBsiService;
import com.bbva.balsec.util.RestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.indra.core.exception.SinResultadosException;
import com.indra.exception.UtilException;
import com.indra.util.FechaUtil;
import com.indra.util.JsonPathUtil;
import com.indra.util.db.Paginator;
import com.indra.web.controller.ScrudController2;
import com.indra.web.exception.HttpException;
import com.indra.web.model.RequestModel;
import com.indra.web.model.ResponseModel;
import com.jayway.jsonpath.TypeRef;

/**
 * @author JUAN
 *
 */
@Controller("consolidadoBsiController")
@Scope("prototype")
public class ConsolidadoBsiController extends ScrudController2<ConsolidadoBsi> {

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "consolidadoBsiService")
	private IConsolidadoBsiService consolidadoBsiService;
	
    @Resource(name="jobLoadFactory")
    private JobLoadFactory jobLoadFactory;

	@Resource(name = "restUtil")
	private RestUtil restUtil;
	
	@Resource(name = "periodoController")
    private PeriodoController periodoController;
	
	@RequestMapping(value = "consolidado/{idPeriodo}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<ConsolidadoBsi> obtener(@PathVariable("idPeriodo") String idPeriodo, HttpServletRequest request, HttpServletResponse response) throws HttpException, SinResultadosException {
		ResponseModel<ConsolidadoBsi> responseModel = null;
		configureResponse(response, request);
		try {
			responseModel = new ResponseModel<ConsolidadoBsi>();
			String parameters = IOUtils.toString(request.getReader());
			parameters = URLDecoder.decode(parameters, "UTF-8");
	        Integer pagina = JsonPathUtil.read(parameters, "$.data.props.page");
	        Integer nroFilas = JsonPathUtil.read(parameters, "$.data.props.rows");
	        String ColOrden = JsonPathUtil.read(parameters, "$.data.props.sidx");
	        String Orden = JsonPathUtil.read(parameters, "$.data.props.sord");
	        
	        ConsolidadoBsi consolidadoBsi = JsonPathUtil.read(parameters, "$.data.entidad", new TypeRef<ConsolidadoBsi>() {});
			consolidadoBsi.setPeriodo(restUtil.obtenerFecha(idPeriodo));
			
			Paginator<ConsolidadoBsi> paginator = null;
			if (consolidadoBsi.getPestania().equals("CONSOLIDADO")){
				if(ColOrden.isEmpty()){

					paginator = consolidadoBsiService.buscarPaginado(consolidadoBsi, nroFilas, pagina);
				}
				else{
					consolidadoBsi.setColOrden(ColOrden);
					consolidadoBsi.setOrden(Orden);
					paginator = consolidadoBsiService.buscarPaginado(consolidadoBsi, nroFilas, pagina);
				}	
			}else if (consolidadoBsi.getPestania().equals("CUADRE")){
				if(ColOrden.isEmpty()){
					paginator = consolidadoBsiService.consultarCuadre(consolidadoBsi, nroFilas, pagina);
				}
				else{
					consolidadoBsi.setColOrden(ColOrden);
					consolidadoBsi.setOrden(Orden);
					paginator = consolidadoBsiService.consultarCuadre(consolidadoBsi, nroFilas, pagina);
				}
			}
			
	        responseModel.getData().setListaEntidades(paginator.getListaEntidades());	        
	        
	        responseModel.getData().put(TOTAL_FILAS, paginator.getTotal()); 
	        responseModel.getData().put(PAGINA, paginator.getPagina()); 
	        responseModel.getData().put(TOTAL_PAGINAS, paginator.getNroPaginas()); 
	        response.setStatus(HttpServletResponse.SC_CREATED);
	        
		}  catch (Exception e) {
			controlError(response, e);
		}
        return responseModel; 
    }
	
	@RequestMapping(value = "consolidado/{idPeriodo}/diferencias", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<ConsolidadoBsi> diferencias(@PathVariable("idPeriodo") String idPeriodo, HttpServletRequest request, HttpServletResponse response) throws HttpException, SinResultadosException {
		ResponseModel<ConsolidadoBsi> responseModel = null;   
		try {
			responseModel = new ResponseModel<ConsolidadoBsi>();
			String parameters = IOUtils.toString(request.getReader());
			parameters = URLDecoder.decode(parameters, "UTF-8");
	        Integer pagina = JsonPathUtil.read(parameters, "$.data.props.page");
	        Integer nroFilas = JsonPathUtil.read(parameters, "$.data.props.rows");
	        String ColOrden = JsonPathUtil.read(parameters, "$.data.props.sidx");
	        String Orden = JsonPathUtil.read(parameters, "$.data.props.sord");
	        
	        ConsolidadoBsi consolidadoBsi = JsonPathUtil.read(parameters, "$.data.entidad", new TypeRef<ConsolidadoBsi>() {});
			consolidadoBsi.setPeriodo(restUtil.obtenerFecha(idPeriodo));
			
			Paginator<ConsolidadoBsi> paginator = null;
			paginator = consolidadoBsiService.consultarCuadre(consolidadoBsi,  nroFilas, pagina);
			
	        responseModel.getData().setListaEntidades(paginator.getListaEntidades());	        
	        
	        responseModel.getData().put(TOTAL_FILAS, paginator.getTotal()); 
	        responseModel.getData().put(PAGINA, paginator.getPagina()); 
	        responseModel.getData().put(TOTAL_PAGINAS, paginator.getNroPaginas()); 
	        
		}  catch (Exception e) {
			controlError(response, e);
		}
        return responseModel; 
    }
	
	@RequestMapping(value = "consolidado/{idPeriodo}/cuadrar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<ConsolidadoBsi> cuadrar(@PathVariable("idPeriodo") String idPeriodo, HttpServletRequest request, HttpServletResponse response) throws HttpException, SinResultadosException, UtilException  {
		ResponseModel<ConsolidadoBsi> responseModel = null;    
		try {
			String peridoActual = restUtil.obtenerCodigoPeriodoActual(periodoController);
			
			String parameters = IOUtils.toString(request.getReader());
			parameters = URLDecoder.decode(parameters, "UTF-8");
			List<ConsolidadoBsi> listConsolidadoBsi = JsonPathUtil.read(parameters, "$.data.listaEntidades", new TypeRef<List<ConsolidadoBsi>>() {});	 
	        
			//List<BigDecimal> listaIds = (List<BigDecimal>)consolidadoBsiService.obtenerSectorMayorSaldo(listConsolidadoBsiTmp, idPeriodo);
	        //consolidadoBsiService.cuadrarDiferencias(listaIds);			
			//consolidadoBsiService.cuadrarActualizado(listConsolidadoBsiTmp, idPeriodo);
			//List<String> listaCuentas = new ArrayList<String>();
			//LinkedHashSet<String> listaCuentas = new LinkedHashSet <String>();
			
			String lista = null;
			
	        String usaCuenta = "-1";
	        if(listConsolidadoBsi != null && !listConsolidadoBsi.isEmpty()){
	           lista = restUtil.MultiplicaCuentas(listConsolidadoBsi);
	           usaCuenta = "0";
	        } else {
	            lista = "-1";
	        }			
			jobLoadFactory.createAndExecuteJob(loadCuadre(lista, peridoActual, usaCuenta), UUID.randomUUID().toString());
			
		} catch (IOException e) {
			controlError(response, e);
		}	
        return responseModel; 
    }
	
	public ConfigureTasklet loadCuadre(String listaCuentas, String periodo, String usaCuenta) throws JsonProcessingException, SinResultadosException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet("PROCESAR_CUADRE");
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo);
        mapEntradas.put("p_cuentas", listaCuentas);
        mapEntradas.put("p_usaCuenta", usaCuenta);
        //mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        //mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("PR_CUADRAR");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_CUADRAR");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }
	
	@Override
	protected RequestModel<ConsolidadoBsi> leerEntradaJSON(HttpServletRequest request)
			throws HttpException {
		return leerEntradaJSON(request, new TypeReference<RequestModel<ConsolidadoBsi>>() {});
	}

}