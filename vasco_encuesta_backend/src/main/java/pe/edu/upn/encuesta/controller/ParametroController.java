package pe.edu.upn.encuesta.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.upn.encuesta.Constante;
import com.bbva.balsec.batch.ConfigureFileLoadTasklet;
import com.bbva.balsec.bean.ConfigureTaskletParametro;
import pe.edu.upn.encuesta.entidad.Parametro;
import pe.edu.upn.encuesta.service.IParametroService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.indra.batch.item.util.TextField;
import com.indra.core.exception.SinResultadosException;
import com.indra.util.JsonPathUtil;
import com.indra.util.db.Paginator;
import com.indra.validator2.FormatoValidator;
import com.indra.validator2.ObligatorioValidator;
import com.indra.validator2.Validator;
import com.indra.web.controller.ScrudController2;
import com.indra.web.exception.HttpException;
import com.indra.web.model.RequestModel;
import com.indra.web.model.ResponseModel;
import com.jayway.jsonpath.TypeRef;

/**
 * 
 * @author JUAN
 *
 */
@Controller("parametroController")
@Scope("prototype")
@RequestMapping("parametro")
public class ParametroController extends ScrudController2<Parametro> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ParametroController.class);
    
    @Autowired
    public void setParametroService(@Qualifier("parametroService") IParametroService parametroService) {
        super.setServicio(parametroService);
    }

    public IParametroService getParametroService() {
        return (IParametroService) getServicio();
    }
    
    @Override
    protected RequestModel<Parametro> leerEntradaJSON(HttpServletRequest request) throws HttpException {
        return leerEntradaJSON(request, new TypeReference<RequestModel<Parametro>>() {});
    }

    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws HttpException
     * @throws SinResultadosException
     * @throws IOException
     */
    @RequestMapping(value = "buscarSimultaneo", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public ResponseModel<Map<Long, List<Parametro>>> buscarSimultaneo(HttpServletRequest request, HttpServletResponse response) throws HttpException, SinResultadosException, IOException {
        String content = IOUtils.toString(request.getReader());
        List<Long> ids = JsonPathUtil.read(content, "$.data.listaEntidades[*].parametro.id", new TypeRef<List<Long>>() {});
        
        ResponseModel<Map<Long, List<Parametro>>> responseModel = new ResponseModel<Map<Long,List<Parametro>>>();
        responseModel.getData().setEntidad(getParametroService().listarPorIDsPadres(ids));
        
        configureResponse(response, request);
        return responseModel;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws HttpException
     * @throws SinResultadosException
     * @throws IOException
     */
    @RequestMapping(value = "buscarPadres", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    public ResponseModel<Parametro> buscarPadres(HttpServletRequest request, HttpServletResponse response) throws HttpException, SinResultadosException, IOException {
        ResponseModel<Parametro> responseModel = new ResponseModel<Parametro>();
        responseModel.getData().setListaEntidades(getParametroService().listarPadres());
        
        configureResponse(response, request);
        return responseModel;
    }

    @Override
    protected ResponseModel<Parametro> internalInsertarActualizar(
            RequestModel<Parametro> model, boolean insertar) {
        Parametro p = model.getData().getEntidad();
        ResponseModel<Parametro> responseModel = new ResponseModel<Parametro>();
        responseModel.getData().setMensaje(p.getId() == null || p.getId().longValue() == 0 ? "El registro se ha insertado exitosamente" : "El registro se ha actualizado exitosamente");
        Parametro paramRepos = null;
        try {
        	if(p.getParametro().getId()!=null){
                paramRepos = getParametroService().obtener(p.getParametro().getId());
        	}
        } catch (SinResultadosException e) {
            LOG.error("", e);
        }
        getParametroService().actualizar(p, paramRepos);
        return responseModel;
    }
    
    @RequestMapping(value = "obtenerConfiguracionArchivo", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    protected List<ConfigureFileLoadTasklet> obtenerConfiguracionArchivo(HttpServletRequest request, HttpServletResponse response) throws HttpException, SinResultadosException, JsonParseException, JsonMappingException, IOException {
        List<ConfigureFileLoadTasklet> result = new ArrayList<ConfigureFileLoadTasklet>();
        RequestModel<Parametro> requestModel = leerEntradaJSON(request);
        Parametro param = getServicio().obtener(requestModel.getData().getEntidad().getId());
        String blobVal = param.getBlobVal();
        
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        if (blobVal!=null){
            result = mapper.readValue(String.valueOf(blobVal.substring(0, (int)blobVal.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {});
        }
        
        return result;
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "obtenerConfiguracionesArchivo", method = {RequestMethod.POST,RequestMethod.GET}, produces = {"application/json"})
    @ResponseBody
    protected List<ConfigureTaskletParametro> obtenerConfiguracionesArchivo(HttpServletRequest request, HttpServletResponse response) throws HttpException, SinResultadosException, JsonParseException, JsonMappingException, IOException {
        List<ConfigureTaskletParametro> result = new ArrayList<ConfigureTaskletParametro>();
        Parametro parametro = new Parametro();
        Parametro parametroPadre = new Parametro();
        parametroPadre.setId(Constante.KeyParametro.ARCHIVO_INPUTS);
        parametro.setParametro(parametroPadre);
        parametro.setBlobHabil('S');
        Paginator<Parametro> param = getServicio().buscarPaginado(parametro, null, null);
        
        
        for (Parametro paramIter : param.getListaEntidades()) {
            ConfigureTaskletParametro configTaskParam = new ConfigureTaskletParametro();
            configTaskParam.setIdParametro(paramIter.getId());
            String sBlob = paramIter.getBlobVal();
            
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule("test", Version.unknownVersion());
            module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
            module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
            mapper.registerModule(module);
            if(sBlob!=null){
                configTaskParam.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlob.substring(0, (int)sBlob.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
                result.add(configTaskParam);
            }
        }
        
        return result;
    }
   
    @RequestMapping(value = "actualizarConfiguracionArchivo/{idParametro}", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseBody
    protected void actualizarConfiguracionArchivo(HttpServletRequest request, HttpServletResponse response, @PathVariable("idParametro") Long idParametro) throws Exception {
        Parametro paramUpdate = this.getServicio().obtener(idParametro);
        String params = IOUtils.toString(request.getReader());
        String newParams = URLDecoder.decode(params, "UTF-8");

        
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        List<ConfigureFileLoadTasklet> configTemp = mapper.readValue(paramUpdate.getBlobVal(), new TypeReference<List<ConfigureFileLoadTasklet>>() {});
        List<ConfigureFileLoadTasklet> config = new ArrayList<ConfigureFileLoadTasklet>();
        
        RequestModel<ConfigureFileLoadTasklet> model = mapper.readValue(newParams, new TypeReference<RequestModel<ConfigureFileLoadTasklet>>() {});
        ConfigureFileLoadTasklet blobVal = model.getData().getListaEntidades().get(0);
        
        for(ConfigureFileLoadTasklet c: configTemp) {
            if(c.getNameTasklet().equalsIgnoreCase(blobVal.getNameTasklet())) {
                for(TextField tDest : blobVal.getCampos()) {
                    for(TextField tOrig : c.getCampos()) {
                        if(tDest.getName().equalsIgnoreCase(tOrig.getName())) {
                            tDest.setValidador(tOrig.getValidador());
                            break;
                        }
                    }
                }
                config.add(blobVal);
            } else {
                config.add(c);
            }
        }
        
        paramUpdate.setBlobVal(imprimir(config));
        System.out.println(paramUpdate.getBlobVal());
        this.getServicio().actualizar(paramUpdate);
        adicionarError(response, ERR_CODE_EXITO, "Par\u00e1metro actualizado", HttpServletResponse.SC_CREATED);
        configureResponse(response, request);
    }

}
