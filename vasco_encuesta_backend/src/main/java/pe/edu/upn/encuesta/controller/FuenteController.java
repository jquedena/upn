/**
 * 
 */
package pe.edu.upn.encuesta.controller;

import java.io.IOException;
import java.net.URLDecoder;

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
import pe.edu.upn.encuesta.entidad.ConsolidadoInputsHost;
import com.bbva.balsec.facade.IFuenteFacade;
import pe.edu.upn.encuesta.service.IConsolidadoInputsHostService;
import com.bbva.balsec.util.RestUtil;
import com.indra.core.domain.Entidad;
import com.indra.core.exception.BussinesException;
import com.indra.core.exception.CampoUnicoException;
import com.indra.core.exception.SinResultadosException;
import com.indra.util.JsonPathUtil;
import com.indra.util.db.Paginator;
import com.indra.web.controller.ErrorHandlerController;
import com.indra.web.exception.HttpException;
import com.indra.web.model.RequestModel;
import com.indra.web.model.ResponseModel;
import com.jayway.jsonpath.TypeRef;

/**
 * @author JUAN
 *
 */

@Controller("fuenteController")
@Scope("prototype")
@RequestMapping("fuente")
public class FuenteController extends ErrorHandlerController<Entidad> {
    
    private static final long serialVersionUID = 1L;
    
    @Resource(name = "fuenteFacade")
    private IFuenteFacade fuenteFacade;
    
    @Resource(name = "consolidadoInputsHostService")
    private IConsolidadoInputsHostService consolidadoInputsHostService;
    
    @Resource(name = "restUtil")
    private RestUtil restUtil;
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "{idPeriodo}/{idFuente}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<Entidad> obtener(@PathVariable("idPeriodo") String idPeriodo
            , @PathVariable("idFuente") String idFuente
            , HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        configureResponse(response, request);
        ResponseModel<Entidad> responseModel = new ResponseModel<Entidad>();
        String parameters = IOUtils.toString(request.getReader());
        Paginator<Entidad> paginator = (Paginator<Entidad>) fuenteFacade.listar(idFuente, idPeriodo, URLDecoder.decode(parameters, "UTF-8")); // TODO: Decodificar
        responseModel.getData().setListaEntidades(paginator.getListaEntidades());
        responseModel.getData().put(TOTAL_FILAS, paginator.getTotal());
        responseModel.getData().put(PAGINA, paginator.getPagina());
        responseModel.getData().put(TOTAL_PAGINAS, paginator.getNroPaginas());
        return responseModel;
    }
    
    @RequestMapping(value = "resumen/{idPeriodo}/{idFuente}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<ConsolidadoInputsHost> resumen(@PathVariable("idPeriodo") String idPeriodo, @PathVariable("idFuente") String idFuente, HttpServletRequest request, HttpServletResponse response){
  
        configureResponse(response, request);
        ResponseModel<ConsolidadoInputsHost> responseModel = null;
        try {
            responseModel = new ResponseModel<ConsolidadoInputsHost>();
            String parameters = IOUtils.toString(request.getReader());
            Integer pagina = JsonPathUtil.read(parameters, "$.data.props.page");
            Integer nroFilas = JsonPathUtil.read(parameters, "$.data.props.rows");
            ConsolidadoInputsHost consolidadoInputsHost = JsonPathUtil.read(parameters, "$.data.entidad", new TypeRef<ConsolidadoInputsHost>() {});
            if(consolidadoInputsHost == null) {
                consolidadoInputsHost = new ConsolidadoInputsHost();
            }
            consolidadoInputsHost.setFuente(idFuente);
            consolidadoInputsHost.setPeriodo(restUtil.obtenerFecha(idPeriodo));
            Paginator<ConsolidadoInputsHost> paginator = consolidadoInputsHostService.resumen(consolidadoInputsHost, nroFilas, pagina);
            responseModel.getData().setListaEntidades(paginator.getListaEntidades());
            responseModel.getData().put(TOTAL_FILAS, paginator.getTotal());
            responseModel.getData().put(PAGINA, paginator.getPagina());
            responseModel.getData().put(TOTAL_PAGINAS,
                    paginator.getNroPaginas());
            
        } catch (BussinesException  e) {
        	//controlError(response, e);
        	response.setContentType("application/json");
        	response.addHeader("errorCode", "sinContenido");
		    response.addHeader("errorMessage", e.getMessage());
		    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } catch (Exception  e) {
        	controlError(response, e);
        	
        }
        return responseModel;
    }
    
    @RequestMapping(value = "{idPeriodo}/{idFuente}/{accion}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel<Entidad> procesar(@PathVariable("idPeriodo") String idPeriodo, @PathVariable("idFuente") String idFuente, @PathVariable("accion") String accion, HttpServletRequest request, HttpServletResponse response) throws HttpException {
        configureResponse(response, request);
        ResponseModel<Entidad> responseModel = null;
        configureResponse(response, request);
        try {
            responseModel = new ResponseModel<Entidad>();
            String paramters = IOUtils.toString(request.getReader());
 
            if (accion.equals(Constante.KeyParametro.ACCION_REGISTRAR)) {
            	try{
	                fuenteFacade.insertar(idFuente, idPeriodo, URLDecoder.decode(paramters, "UTF-8"));
            		//fuenteFacade.insertar(idFuente, idPeriodo,paramters);
	                adicionarError(response, "operacionExitosa", "El registro ha sido ingresado exitosamente", HttpServletResponse.SC_CREATED);
	                responseModel.getData().setMensaje(EXITO_AGREGAR);
            	}
            	catch(CampoUnicoException cuE){
            		adicionarError(response, "operacionConError", cuE.getMensaje(), HttpServletResponse.SC_ACCEPTED);
	                responseModel.getData().setMensaje(ERR_CODE_TECNICO);
            	}
            } else if (accion.equals(Constante.KeyParametro.ACCION_ACTUALIZAR)) {
            	try{
	                fuenteFacade.modificar(idFuente, idPeriodo, URLDecoder.decode(paramters, "UTF-8"));
	                //fuenteFacade.modificar(idFuente, idPeriodo,paramters);
	                adicionarError(response, "operacionExitosa", "El registro ha sido modificado exitosamente", HttpServletResponse.SC_CREATED);
	                responseModel.getData().setMensaje(EXITO_ACTUALIZAR);
            	}
            	catch(CampoUnicoException cuE){
            		adicionarError(response, "operacionConError", cuE.getMensaje(), HttpServletResponse.SC_ACCEPTED);
	                responseModel.getData().setMensaje(ERR_CODE_TECNICO);
            	}
            } else if (accion.equals(Constante.KeyParametro.ACCION_ELIMINAR)) {
                fuenteFacade.eliminar(idFuente, idPeriodo, URLDecoder.decode(paramters, "UTF-8"));
                adicionarError(response, "operacionExitosa", "El registro ha sido eliminado exitosamente", HttpServletResponse.SC_CREATED);
                responseModel.getData().setMensaje(EXITO_ELIMINAR);  
            }else if (accion.equals(Constante.KeyParametro.AGREGAR_AGENTE_ECONO)) {
                try{
                	fuenteFacade.modificar(idFuente, idPeriodo, URLDecoder.decode(paramters, "UTF-8"));
                    adicionarError(response, "operacionExitosa", "Se han a\u00f1adido los agentes econ\u00f3micos ", HttpServletResponse.SC_CREATED);
                    responseModel.getData().setMensaje(EXITO_ACTUALIZAR);
            	}
            	catch(CampoUnicoException cuE){
            		adicionarError(response, "operacionConError", cuE.getMensaje(), HttpServletResponse.SC_CREATED);
	                responseModel.getData().setMensaje(EXITO_ACTUALIZAR);
            	}
            }
            
            
        } catch (Exception e) {
            throw new HttpException(e);
        }
        return responseModel; 
    }
    
    @Override
    protected RequestModel<Entidad> leerEntradaJSON(HttpServletRequest request) throws HttpException {
        throw new UnsupportedOperationException();
    }
    
}
