package pe.edu.upn.encuesta.controller;

import pe.edu.upn.encuesta.Constante;
import pe.edu.upn.encuesta.entidad.Parametro;
import pe.edu.upn.encuesta.entidad.Periodo;
import pe.edu.upn.encuesta.service.IParametroService;
import com.bbva.balsec.util.RestUtil;
import com.bbva.ws.service.LDAPClient;
import com.indra.core.service.Log4jService;
import com.indra.web.controller.ScrudController2;
import com.indra.web.exception.HttpException;
import com.indra.web.model.Model;
import com.indra.web.model.RequestModel;
import com.indra.web.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("periodoController")
@Scope("prototype")
@RequestMapping("periodo")
public class PeriodoController extends ScrudController2<Periodo> {

	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Log4jService.class);
	
	@Resource(name = "parametroService")
	private IParametroService parametroService;
	
	@Resource(name = "ldapClientWS")
	private transient LDAPClient ldapClientWS;

	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public ResponseModel<Periodo> obtenerPeriodoActual() {
		ResponseModel<Periodo> responseModel = new ResponseModel<Periodo>();
		Model<Periodo> model = new Model<Periodo>();		
		RestUtil restUtil = new RestUtil();
		Parametro parametro = new Parametro();
		List<Periodo> periodos = new ArrayList<Periodo>();
		
		try {
			parametro =  parametroService.obtener(Constante.KeyParametro.RANGO_PERIODOS);
			periodos = restUtil.obtenerPeriodo(parametro);
		} catch(Exception ex) {
            LOGGER.error("obtenerPeriodoActual", ex);
        }
		
		model.setListaEntidades(periodos);
		responseModel.setData(model);
		
		return responseModel;
		
	}


	@Override
	protected RequestModel<Periodo> leerEntradaJSON(HttpServletRequest request)
			throws HttpException {
		return null;
	}
	

}
