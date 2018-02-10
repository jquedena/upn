package pe.edu.upn.encuesta.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.upn.encuesta.Constante;
import com.bbva.balsec.batch.ConfigureFileLoadTasklet;
import com.bbva.balsec.batch.ConfigureProcedureTasklet;
import com.bbva.balsec.batch.ConfigureTasklet;
import com.bbva.balsec.batch.JobLoadFactory;
import pe.edu.upn.encuesta.entidad.Parametro;
import pe.edu.upn.encuesta.service.IParametroService;
import com.bbva.balsec.util.RestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.indra.batch.item.util.FieldType;
import com.indra.batch.item.util.TextField;
import com.indra.core.exception.SinResultadosException;
import com.indra.exception.UtilException;
import com.indra.util.ArchivoUtil;
import com.indra.util.FechaUtil;
import com.indra.util.JsonPathUtil;
import com.indra.validator2.FormatoValidator;
import com.indra.validator2.ObligatorioValidator;
import com.indra.validator2.Validator;
import com.indra.web.controller.AbstractController;
import com.indra.web.exception.HttpException;

@Controller("procesoController")
@Scope("prototype")
public class ProcesoController extends AbstractController {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = Logger.getLogger(CargaController.class);
    
    @Resource(name = "parametroService")
    private IParametroService parametroService;

    @Resource(name = "jobLoadFactory")
    private JobLoadFactory jobLoadFactory;

    @Resource(name = "restUtil")
    private RestUtil restUtil;

    @Resource(name = "periodoController")
    private PeriodoController periodoController;
    
    @RequestMapping(value = "proceso/ejecutar/{nombre}", method = RequestMethod.POST)
    @ResponseBody
    public void procesar(HttpServletRequest request, HttpServletResponse response, @PathVariable("nombre") String nombre) throws HttpException {
        
    	try {
 
            String pathEntrada = parametroService.obtener(Constante.KeyParametro.DIRECTORIO_LOAD).getTexto();
            String pathSalida = parametroService.obtener(Constante.KeyParametro.DIRECTORIO_OUT).getTexto();
            String parameters = IOUtils.toString(request.getReader());//URLDecoder.decode(parameters, "UTF-8")
            parameters = URLDecoder.decode(parameters, "UTF-8");
            Boolean estado = false;
            Boolean anexo8 = false;
            
            ConfigureTasklet configure = null;
            
            String peridoActual = restUtil.obtenerCodigoPeriodoActual(periodoController);
             
            if (nombre.equals(Constante.TipoCarga.CARGA_ANEXO1)){
                configure = loadParametroAnexo1(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.TipoCarga.CARGA_ANEXO8)){
            	
            	estado = descomprimirAnexo8(pathEntrada);
            	if(estado)
            		configure = loadParametroAnexo8(pathEntrada,peridoActual);
            	else{
            		anexo8 = true;
		        	response.addHeader("errorCode", "errorProcesar");
		        	response.addHeader("errorMessage","El archivo zip debe contener los 5 archivos");
            	} 	
            }else if (nombre.equals(Constante.TipoCarga.CARGA_SALDOSDIARIOS)){
                configure = loadParametroSaldosDiarios(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.TipoCarga.CARGA_OTROSDISPONIBLES)){
                configure = loadParametroOtrosRestringidos(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.TipoCarga.CARGA_CLIENTESECTORIZADO)){
            	this.descomprimirArchivoZIP(pathEntrada);
            	configure = loadParametroClienteSectorizado(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.TipoCarga.CARGA_ARCHIVOMODELO)){
                configure = loadParametroArchivoModelo(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.TipoCarga.CARGA_BALANCECOMPROBACION)){
                configure = loadParametroBalanceComprobacion(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.TipoCarga.CARGA_AGENTESECONOMICOS)){
                configure = loadParametroAgentesEconomicos(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.Proceso.PROCESO_ANEXO1)){
                configure = loadParametroProcesoAnexo1(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.Proceso.PROCESO_ANEXO8)){
                configure = loadParametroProcesoAnexo8(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.Proceso.PROCESO_SALDIARIO)){
                configure = loadParametroProcesoSaldosDiarios(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.Proceso.PROCESO_OTROSDISPO)){
                configure = loadParametroProcesoOtrosRestringidos(pathEntrada,peridoActual);
            }else if (nombre.equals(Constante.TipoProcedimiento.GENERAR_CONSOLIDADO)){
                configure = loadConfigProcedureFuente(Constante.TipoProcedimiento.PROC_GENERAR_CONSOLIDADO, parameters,"GENERAR_CONSOLIDADO_BSI","LOAD_CONSOLIDADO_BSI");
            } else if (nombre.equals(Constante.TipoProcedimiento.GENERAR_CLIENTES_NO_SECTORIZADOS)){
                configure = loadConfigProcedureFuente(Constante.TipoProcedimiento.PROC_GENERAR_CLIENTES_NO_SECTORIZADOS, parameters,"GENERAR_NO_SECTORIZADOS","LOAD_NO_SECTORIZADOS");
            }else if (nombre.equals(Constante.TipoProcedimiento.GENERAR_BSI)){
            	jobLoadFactory.createAndExecuteJob(loadConfigGenerarVersionBSI(parameters), UUID.randomUUID().toString());
                configure = loadConfigGenerarArchivoBsi(pathSalida, parameters);
            } else if (nombre.equals(Constante.TipoProcedimiento.GENERAR_SALDOS_INICIALES)){
                configure = loadConfigProcedureFuente(Constante.TipoProcedimiento.PROC_GENERAR_SALDOS_INICIALES, parameters,"GENERAR_SALDOS_INICIALES","LOAD_SALDOS_INICIALES_MES_ANTERIOR");
            } else if (nombre.equals(Constante.TipoProcedimiento.GENERAR_ARCHIVO_NO_SECTORIZADOS)){
                configure = loadConfigGenerarArchivoNoSectorizado(pathSalida, parameters);
            } else if (nombre.equals(Constante.TipoProcedimiento.GENERAR_ARCHIVO_INPUT)){
                configure = loadConfigGenerarArchivoInput(pathSalida, parameters);
            } else if (nombre.equals(Constante.Proceso.PROCESO_AGENTECONO)){
                configure = loadConfigGenerarAgentesEconomicos(pathSalida, parameters);
            } else if (nombre.equals(Constante.Proceso.PROCESO_ARCHIMODEL)){
                configure = loadConfigGenerarArchivoModelo(pathSalida, parameters);
            } else if (nombre.equals(Constante.Proceso.PROCESO_ENTRADAS)){
                configure = null;
                jobLoadFactory.createAndExecuteJobInBackground(this.loadParametroProcesoAnexo1(pathEntrada,peridoActual));
                jobLoadFactory.createAndExecuteJobInBackground(this.loadParametroProcesoAnexo8(pathEntrada,peridoActual));
                jobLoadFactory.createAndExecuteJobInBackground(this.loadParametroProcesoSaldosDiarios(pathEntrada,peridoActual));
                jobLoadFactory.createAndExecuteJobInBackground(this.loadParametroProcesoOtrosRestringidos(pathEntrada,peridoActual));
            } else {
                configure = null;
            }
            
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule("test", Version.unknownVersion());
            module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
            module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
            mapper.registerModule(module);
            
            if (configure!=null){
                jobLoadFactory.createAndExecuteJobInBackground(configure);
            }
            
            if(anexo8)
            	response.setStatus(HttpServletResponse.SC_ACCEPTED);
            else
            	response.setStatus(HttpServletResponse.SC_CREATED);
            
        } catch (Exception e) {
            throw new HttpException(e);
        }
 
    }
    
    private Boolean descomprimirAnexo8(String pathEntrada) throws SinResultadosException, IOException, UtilException{

    	String strDirectorioTmp = ArchivoUtil.completarSeparador(pathEntrada)+ "temp";
    	File fileDirectorioTmp = new File(strDirectorioTmp);
    	if (!fileDirectorioTmp.mkdir()){
    		File[] filesAntiguos = ArchivoUtil.listarArchivos(fileDirectorioTmp);
    		if(filesAntiguos != null){
    			for (File file : filesAntiguos) {
    	        	ArchivoUtil.deleteWithChildren(file.getAbsolutePath());
    			}
    		}
    	}else{
    		LOG.info("Se creo la ruta :"+strDirectorioTmp);
    	}
    	
		String strNombreArchivoCompreso = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8).getCodigo();
		ArchivoUtil.unZipFile(new File(ArchivoUtil.completarSeparador(pathEntrada) +strNombreArchivoCompreso), new File(strDirectorioTmp));
		
		int contador = 0;

		File[] filesNuevos = ArchivoUtil.listarArchivos(new File(strDirectorioTmp));
		
		List<Long> lst = new ArrayList<Long>();
	    lst.add(Constante.KeyParametro.ARCHIVO_INPUTS);
	    Map<Long, List<Parametro>> mapParametroHijo = parametroService.listarPorIDsPadres(lst);
	    
	    List<Parametro> TipoArchivos = mapParametroHijo.get(Constante.KeyParametro.ARCHIVO_INPUTS);
	    List<String> formatosAnexo8 = new ArrayList<String>();
	    
	    if(TipoArchivos !=null){
			for (Parametro p : TipoArchivos) {
				if(p !=null){
			    	for(String f : Constante.ARCHIVOS_ANEXO8){
			    		if(p.getCodigo().equals(f)){
			    			formatosAnexo8.add(p.getCodigo());
			    			break;}
			    	}
				}
			}
	    }
    
		if (filesNuevos !=null){
			for (File file : filesNuevos) {
				
				for(String f : formatosAnexo8){
					if(f !=null){
						if(f.substring(7,9).equals(file.getName().substring(0,2))){
							File parent	= file.getParentFile();
							file.renameTo(new File(parent, f));
							contador = contador + 1;
						}
					}
				}

			}
		}
		
        
        if (contador == 5){
        	return true; 
        }   	
        else
        	return false;
		
	}
    
    private Boolean descomprimirArchivoZIP(String path) throws SinResultadosException, IOException {
    	File[] files = ArchivoUtil.listarArchivos(new File(ArchivoUtil.completarSeparador(path)));
        String strArchivoDETACLIESECTO = "CLIENTES_SECTORIZADOS.TXT";
        
    	for (File file : files) {
        	if (file.getName().equals(strArchivoDETACLIESECTO)){
        		file.delete();
        	}
		} 
    	
    	String strNombreArchivoCompreso = parametroService.obtener(Constante.KeyParametro.ARCHIVO_DETALLECLIENTESECTORIZADO).getCodigo();
		ArchivoUtil.unZipFile(new File(ArchivoUtil.completarSeparador(path) +strNombreArchivoCompreso),new File(ArchivoUtil.completarSeparador(path)));

        files = ArchivoUtil.listarArchivos(new File(ArchivoUtil.completarSeparador(path)));
        for (File file : files) {
        	if (file.getName().equals(strArchivoDETACLIESECTO)){
        		return true;
        	}
		}
        return false;
	}

	public ConfigureTasklet loadParametroClienteSectorizado(String path, String periodo_yyyyMM) throws SinResultadosException, IOException {
        
        ConfigureTasklet configure = new ConfigureTasklet();
        String strNombreArchivo = parametroService.obtener(Constante.KeyParametro.ARCHIVO_DETALLECLIENTESECTORIZADO).getCodigo();
        configure.setNameConfigureTasklet(strNombreArchivo);
        
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("LOAD_FILE_CLIENSECTO");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_DETA_CLIE_SECTO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }

    public ConfigureTasklet loadParametroAnexo1(String path, String periodo_yyyyMM) throws SinResultadosException, IOException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO1).getCodigo());
        
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("LOAD_ANEXO1_TO_CONSOLIDADO");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_ANEXO1_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        lstConfigureProcedures.add(configureProcedureTasklet);
        
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        String sBlob = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO1).getBlobVal();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        configure.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlob.substring(0, (int)sBlob.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroAnexo8(String path, String periodo_yyyyMM) throws SinResultadosException, IOException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8).getCodigo());
        
        
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("LOAD_ANEXO8_TO_CONSOLIDADO");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_ANEXO8_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        
        String sBlobAnexo8_01 = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8_01).getBlobVal();
        String sBlobAnexo8_02 = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8_02).getBlobVal();
        String sBlobAnexo8_03 = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8_03).getBlobVal();
        String sBlobAnexo8_04 = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8_04).getBlobVal();
        String sBlobAnexo8_05 = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8_05).getBlobVal();
        
        String sBlobAnexo8_Completo = "["+sBlobAnexo8_01.substring(1, sBlobAnexo8_01.length()-1)+"," 
        		+ sBlobAnexo8_02.substring(1, sBlobAnexo8_02.length()-1)+","
        		+ sBlobAnexo8_03.substring(1, sBlobAnexo8_03.length()-1)+","
        		+ sBlobAnexo8_04.substring(1, sBlobAnexo8_04.length()-1)+","
        		+ sBlobAnexo8_05.substring(1, sBlobAnexo8_05.length()-1)+"]";
        
        configure.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlobAnexo8_Completo.substring(0, (int)sBlobAnexo8_Completo.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroSaldosDiarios(String path, String periodo_yyyyMM) throws SinResultadosException, IOException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(parametroService.obtener(Constante.KeyParametro.ARCHIVO_SALDOSDIARIOS).getCodigo());
        
        // Procedure
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("LOAD_SALDIARIO_TO_CONSOLIDADO");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_SALDIARIO_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        String sBlob = parametroService.obtener(Constante.KeyParametro.ARCHIVO_SALDOSDIARIOS).getBlobVal();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        configure.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlob.substring(0, (int)sBlob.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroArchivoModelo(String path,String periodo_yyyyMM ) throws SinResultadosException, IOException{
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(parametroService.obtener(Constante.KeyParametro.ARCHIVO_ARCHIVOMODELO).getCodigo());

        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);

        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("LOAD_RESUMEN_INPUTS");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_GENERAR_RESUMEN_INPUT_HOST");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(new LinkedHashMap<String, Integer>());
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        String sBlob = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ARCHIVOMODELO).getBlobVal();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        configure.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlob.substring(0, (int)sBlob.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroOtrosRestringidos(String path, String periodo_yyyyMM) throws SinResultadosException, IOException{
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(parametroService.obtener(Constante.KeyParametro.ARCHIVO_OTROSDISPONIBLESRESTRINGIDOS).getCodigo());
        
        // Procedure
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("LOAD_OTROSDISPO_TO_CONSOLIDADO");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_OTROSDISP_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        String sBlob = parametroService.obtener(Constante.KeyParametro.ARCHIVO_OTROSDISPONIBLESRESTRINGIDOS).getBlobVal();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        configure.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlob.substring(0, (int)sBlob.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroBalanceComprobacion(String path, String periodo_yyyyMM) throws SinResultadosException, IOException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(parametroService.obtener(Constante.KeyParametro.ARCHIVO_BALANCECOMPROBACION).getCodigo());
        
        String sBlob = parametroService.obtener(Constante.KeyParametro.ARCHIVO_BALANCECOMPROBACION).getBlobVal();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        configure.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlob.substring(0, (int)sBlob.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroAgentesEconomicos(String path, String periodo_yyyyMM) throws SinResultadosException, IOException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(parametroService.obtener(Constante.KeyParametro.ARCHIVO_AGENTESECONOMICOS).getCodigo());
        
        String sBlob = parametroService.obtener(Constante.KeyParametro.ARCHIVO_AGENTESECONOMICOS).getBlobVal();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("test", Version.unknownVersion());
        module.addAbstractTypeMapping(Validator.class, ObligatorioValidator.class);
        module.addAbstractTypeMapping(Validator.class, FormatoValidator.class);
        mapper.registerModule(module);
        configure.setConfigureFilesLoads((List<ConfigureFileLoadTasklet>) mapper.readValue(String.valueOf(sBlob.substring(0, (int)sBlob.length())), new TypeReference<List<ConfigureFileLoadTasklet>>() {}));
        
        return configure;
    }
    
    
    public ConfigureTasklet loadParametroProcesoAnexo1(String path, String periodo_yyyyMM) throws JsonProcessingException, SinResultadosException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet("PROCESAR_"+parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO1).getCodigo());
        
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("PROCEDURE_ANEXO1");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_ANEXO1_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroProcesoAnexo8(String path, String periodo_yyyyMM) throws JsonProcessingException, SinResultadosException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet("PROCESAR_"+parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8).getCodigo());
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("PROCEDURE_ANEXO8");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_ANEXO8_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroProcesoSaldosDiarios(String path, String periodo_yyyyMM) throws JsonProcessingException, SinResultadosException{
        
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet("PROCESAR_"+parametroService.obtener(Constante.KeyParametro.ARCHIVO_SALDOSDIARIOS).getCodigo());
       
        // Procedure
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("PROCEDURE_LOAD_SALDIARIO_TO_CONSOLIDADO");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_SALDIARIO_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }
    
    public ConfigureTasklet loadParametroProcesoOtrosRestringidos(String path, String periodo_yyyyMM) throws JsonProcessingException, SinResultadosException{
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet("PROCESAR_"+parametroService.obtener(Constante.KeyParametro.ARCHIVO_OTROSDISPONIBLESRESTRINGIDOS).getCodigo());
        // Procedure
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", periodo_yyyyMM);
        mapEntradas.put("p_job_instance_id", 0);

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_LEIDAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("PROCEDURE_OTROSDISPO_TO_CONSOLIDADO");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_LOAD_OTROSDISP_CONSOLIDADO");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }
    
    private ConfigureTasklet loadConfigProcedureFuente(String procedure, String parameters, String strNameConfigureTasklet, String strNameTasklet) {
        ConfigureTasklet configure = new ConfigureTasklet();
        configure.setNameConfigureTasklet(strNameConfigureTasklet);
        //String periodo = JsonPathUtil.read(parameters, "$.data.props.periodo");        
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("p_periodo", restUtil.obtenerCodigoPeriodoActual(periodoController));

        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_PROCESADAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet(strNameTasklet);
        configureProcedureTasklet.setStoreProcedure(procedure);
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }
    
    private ConfigureTasklet loadConfigGenerarArchivoInput(String pathSalida, String parameters) throws JsonProcessingException, SinResultadosException {
        ConfigureTasklet configure = new ConfigureTasklet();
        String periodo_yyyyMM = JsonPathUtil.read(parameters, "$.data.props.periodo");
        String fuente  = "INPUTDISTRIBUIDO";
        fuente = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_ARCHIVO_INPUTHOST).getTexto();
        Date periodo = restUtil.obtenerFecha(periodo_yyyyMM);
        configure.setNameConfigureTasklet("GENERAR"+ fuente);
        
        ConfigureFileLoadTasklet configureFileLoadTasklet = new ConfigureFileLoadTasklet();
        configureFileLoadTasklet.setTipoArchivo(JobLoadFactory.GENERAR_FORMATO_ARCHIVO_TEXTO);
        configureFileLoadTasklet.setArchivo(fuente + FechaUtil.convertirACadena(periodo, "MMyyyy") + "." + JobLoadFactory.EXTENSION_TXT);
        configureFileLoadTasklet.setDirectorio(ArchivoUtil.completarSeparador(pathSalida));
        configureFileLoadTasklet.setFilaInicio(1);
        
        List<TextField> lstTextField = new ArrayList<TextField>();
        
        TextField textField = new TextField();
        textField.setCol(1);
        textField.setBegin(0);
        textField.setEnd(20);
        textField.setType(FieldType.STRING);
        textField.setName("CUENTA_CONTABLE");
        lstTextField.add(textField);
        
        textField = new TextField();
	    textField.setCol(2);
	    textField.setBegin(20);
	    textField.setEnd(8);
	    textField.setType(FieldType.STRING);
	    textField.setName("CODIGO_CENTRAL");
	    lstTextField.add(textField);

	    textField = new TextField();
        textField.setCol(3);
        textField.setBegin(28);
        textField.setEnd(11);
        textField.setType(FieldType.STRING);
        textField.setName("DOI");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(4);
        textField.setBegin(39);
        textField.setEnd(18);
        textField.setType(FieldType.STRING);
        textField.setName("SALDO");
        textField.setPadAlign(TextField.PAD_LEFT);
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(5);
        textField.setBegin(57);
        textField.setEnd(3);
        textField.setType(FieldType.STRING);
        textField.setName("DIVISA");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(6);
        textField.setBegin(60);
        textField.setEnd(10);
        textField.setType(FieldType.STRING);
        textField.setName("FUENTE");
        lstTextField.add(textField);
	    
//        textField = new TextField();
//        textField.setCol(2);
//        textField.setBegin(20);
//        textField.setEnd(50);
//        textField.setType(FieldType.STRING);
//        textField.setName("DESCRIPCION");
//        lstTextField.add(textField);
        
//        textField = new TextField();
//        textField.setCol(3);
//        textField.setBegin(20);
//        textField.setEnd(18);
//        textField.setType(FieldType.STRING);
//        textField.setName("SALDO");
//        textField.setPadAlign(TextField.PAD_LEFT);
//        lstTextField.add(textField);
//        
//        textField = new TextField();
//        textField.setCol(4);
//        textField.setBegin(38);
//        textField.setEnd(3);
//        textField.setType(FieldType.STRING);
//        textField.setName("DIVISA");
//        lstTextField.add(textField);
//        
//        textField = new TextField();
//        textField.setCol(5);
//        textField.setBegin(41);
//        textField.setEnd(8);
//        textField.setType(FieldType.STRING);
//        textField.setName("CODIGO_CENTRAL");
//        lstTextField.add(textField);
//        
//        textField = new TextField();
//        textField.setCol(6);
//        textField.setBegin(49);
//        textField.setEnd(11);
//        textField.setType(FieldType.STRING);
//        textField.setName("DOI");
//        lstTextField.add(textField);
//        
//        textField = new TextField();
//        textField.setCol(7);
//        textField.setBegin(60);
//        textField.setEnd(10);
//        textField.setType(FieldType.STRING);
//        textField.setName("FUENTE");
//        lstTextField.add(textField);
        
        configureFileLoadTasklet.setCampos(lstTextField);

        configureFileLoadTasklet.setSentenciaInsercion(
//        		"SELECT CUENTA_CONTABLE, "
//        		+ " TO_CHAR(SALDO,'999999999999990.00') SALDO, "
//        		+ " DIVISA, CODIGO_CENTRAL, DOI, FUENTE "
//              + " FROM BALSEC.CONSOLIDADO_INPUTS_HOST "
//              + " WHERE TO_CHAR(PERIODO,'YYYYMM') = '"+ periodo_yyyyMM + "'"
//              + " ORDER BY FUENTE DESC"
        		
        		"SELECT CUENTA_CONTABLE, "
        		+ " CODIGO_CENTRAL,DOI, "
        		+ " TO_CHAR(SALDO,'999999999999990.00') SALDO,"
        		+ " DIVISA, FUENTE "
                + " FROM BALSEC.CONSOLIDADO_INPUTS_HOST "
                + " WHERE TO_CHAR(PERIODO,'YYYYMM') = '"+ periodo_yyyyMM + "'"
                + " ORDER BY FUENTE DESC"
        		
        		);
        
        List<ConfigureFileLoadTasklet> lstCnfgLoadTasklet = new ArrayList<ConfigureFileLoadTasklet>();
        lstCnfgLoadTasklet.add(configureFileLoadTasklet);
        configure.setConfigureFilesLoads(lstCnfgLoadTasklet);
        
        ObjectMapper map = new ObjectMapper();
        System.out.println(map.writeValueAsString(configure.getConfigureFilesLoads()));
        
        return configure;
    }
    

    
    private ConfigureTasklet loadConfigGenerarArchivoBsi(String pathSalida, String parameters) throws JsonProcessingException, SinResultadosException {
        ConfigureTasklet configure = new ConfigureTasklet();
        String periodo_yyyyMM = JsonPathUtil.read(parameters, "$.data.props.periodo");
        String versionBsi = JsonPathUtil.read(parameters, "$.data.props.versionBsi");
        
        configure.setNameConfigureTasklet("GENERAR_BSI");
        String nombrePrefijoArchivo = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_ARCHIVO_BSI).getTexto();
        String nombreArchivo = nombrePrefijoArchivo + periodo_yyyyMM + versionBsi;
        ConfigureFileLoadTasklet configureFileLoadTasklet = new ConfigureFileLoadTasklet();
        configureFileLoadTasklet.setTipoArchivo(JobLoadFactory.GENERAR_FORMATO_ARCHIVO_TEXTO);
        configureFileLoadTasklet.setArchivo(nombreArchivo + "." + JobLoadFactory.EXTENSION_TXT);
        configureFileLoadTasklet.setDirectorio(ArchivoUtil.completarSeparador(pathSalida));
        configureFileLoadTasklet.setFilaInicio(1);
        
        List<TextField> lstTextField = new ArrayList<TextField>();
        
        TextField textField = new TextField();
        textField.setCol(1);
        textField.setBegin(0);
        textField.setEnd(6);
        textField.setType(FieldType.STRING);
        textField.setName("PERIODO");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(2);
        textField.setBegin(6);
        textField.setEnd(3);
        textField.setType(FieldType.STRING);
        textField.setName("ENTIDAD_FINANCIERA");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(3);
        textField.setBegin(9);
        textField.setEnd(20);
        textField.setType(FieldType.STRING);
        textField.setName("CUENTA_CONTABLE");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(4);
        textField.setBegin(29);
        textField.setEnd(12);
        textField.setType(FieldType.STRING);
        textField.setName("CODIGO_SECTOR");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(5);
        textField.setBegin(41);
        textField.setEnd(18);
        textField.setType(FieldType.STRING);
        textField.setName("SALDO_INI");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(6);
        textField.setBegin(59);
        textField.setEnd(18);
        textField.setType(FieldType.STRING);
        textField.setName("SALDO_FIN");
        textField.setPadAlign(TextField.PAD_LEFT);
        lstTextField.add(textField);
        
        configureFileLoadTasklet.setCampos(lstTextField);
        String selectUnion = "SELECT SUBSTR('"+nombreArchivo+"',1,6) PERIODO, "+
						       "SUBSTR('"+nombreArchivo+"',7,3) ENTIDAD_FINANCIERA, "+
						       "SUBSTR('"+nombreArchivo+"',10,20) CUENTA_CONTABLE, "+
						       "'' CODIGO_SECTOR, "+
						       "NULL SALDO_INI , "+
						       "NULL SALDO_FIN "+
						       "from SYS.DUAL " + 
						       "UNION ALL ";
        configureFileLoadTasklet.setSentenciaInsercion(selectUnion
        		/*+ " SELECT * FROM ("
        		+ " SELECT TO_CHAR(PERIODO,'YYYYMM') PERIODO, '006' ENTIDAD_FINANCIERA, CUENTA_CONTABLE, "
                + "CODIGO_SECTOR, BALSEC.PKG_UTIL.FN_NUMERICO_FORMATO_CADENA(SALDO_INI,18) SALDO_INI,  BALSEC.PKG_UTIL.FN_NUMERICO_FORMATO_CADENA(SALDO_FIN,18) SALDO_FIN "
                + " FROM BALSEC.CONSOLIDADO_BSI "
                + " WHERE TO_CHAR(PERIODO,'YYYYMM') = '"+ periodo_yyyyMM + "'"
                + " AND VERSION_BSI = '" + versionBsi+"' "
                + "  ORDER BY substr(CUENTA_CONTABLE,1,2) || '0' || "
                + "  rpad(substr(CUENTA_CONTABLE,4,LENGTH(CUENTA_CONTABLE)),15,'0') ASC,"
                + "  substr(CUENTA_CONTABLE,3,1) asc"
                + " )");*/
        		+"SELECT * FROM ( "
				+"SELECT * FROM ( "
				+"SELECT TO_CHAR(PERIODO,'YYYYMM') PERIODO, '006' ENTIDAD_FINANCIERA, RPAD(CUENTA_CONTABLE,20,'0') CUENTA_CONTABLE, "
				+"CODIGO_SECTOR, BALSEC.PKG_UTIL.FN_NUMERICO_FORMATO_CADENA(SALDO_INI,18) SALDO_INI,  BALSEC.PKG_UTIL.FN_NUMERICO_FORMATO_CADENA(SALDO_FIN,18) SALDO_FIN "
				+"FROM BALSEC.CONSOLIDADO_BSI "
				+"WHERE TO_CHAR(PERIODO,'YYYYMM') = '"+ periodo_yyyyMM + "' " 
				+"AND VERSION_BSI = '" + versionBsi+"' "
				+"UNION ALL "
				+"SELECT TO_CHAR(PERIODO,'YYYYMM') PERIODO, '006' ENTIDAD_FINANCIERA, RPAD(CUENTA_CONTABLE,20,'0') CUENTA_CONTABLE, "
				+"'XXXXXXXXXXXX' CODIGO_SECTOR, "
				+"BALSEC.PKG_UTIL.FN_NUMERICO_FORMATO_CADENA(SUM(SALDO_INI),18) , "
				+"BALSEC.PKG_UTIL.FN_NUMERICO_FORMATO_CADENA(SUM(SALDO_FIN),18) "
				+"FROM BALSEC.CONSOLIDADO_BSI "
				+"WHERE TO_CHAR(PERIODO,'YYYYMM') = '"+ periodo_yyyyMM + "' "
				+"AND VERSION_BSI = '" + versionBsi+"' "
				+"AND OPCION = '1' "
				+"GROUP BY CUENTA_CONTABLE,TO_CHAR(PERIODO,'YYYYMM') "
				+") "
				+"ORDER BY substr(CUENTA_CONTABLE,1,2) || '0' || "
				+"rpad(substr(CUENTA_CONTABLE,4,LENGTH(CUENTA_CONTABLE)),15,'0') ASC, "
				+"substr(CUENTA_CONTABLE,3,1) asc, codigo_sector desc "
				+ ")");

                
        
        List<ConfigureFileLoadTasklet> lstCnfgLoadTasklet = new ArrayList<ConfigureFileLoadTasklet>();
        lstCnfgLoadTasklet.add(configureFileLoadTasklet);
        configure.setConfigureFilesLoads(lstCnfgLoadTasklet);
        
        ObjectMapper map = new ObjectMapper();
        System.out.println(map.writeValueAsString(configure.getConfigureFilesLoads()));
        
        return configure;
    }
    
    private ConfigureTasklet loadConfigGenerarVersionBSI(String parameters) throws JsonProcessingException {
        ConfigureTasklet configure = new ConfigureTasklet();
        String periodo_yyyyMM = JsonPathUtil.read(parameters, "$.data.props.periodo");
        String versionBsi = JsonPathUtil.read(parameters, "$.data.props.versionBsi");
        configure.setNameConfigureTasklet("GENERAR_VERSION_BSI");
        
        Map<String,Object> mapEntradas = new LinkedHashMap<String, Object>();
        mapEntradas.put("P_PERIODO", periodo_yyyyMM);
        mapEntradas.put("P_VERSIONBSI", versionBsi);
        
        Map<String, Integer> mapSalidas = new LinkedHashMap<String, Integer>();
        mapSalidas.put("P_FILAS_PROCESADAS", Types.INTEGER);
        
        ConfigureProcedureTasklet configureProcedureTasklet = new ConfigureProcedureTasklet();
        configureProcedureTasklet.setNameTasklet("PR_GENERAR_BSI");
        configureProcedureTasklet.setStoreProcedure("BALSEC.PKG_CARGA_INPUTS.PR_GENERAR_BSI");
        configureProcedureTasklet.setEntradas(mapEntradas);
        configureProcedureTasklet.setSalidas(mapSalidas);
        
        List<ConfigureProcedureTasklet> lstConfigureProcedures = new ArrayList<ConfigureProcedureTasklet>();
        lstConfigureProcedures.add(configureProcedureTasklet);
        configure.setConfigureProcedures(lstConfigureProcedures);
        
        return configure;
    }
        
    private ConfigureTasklet loadConfigGenerarArchivoNoSectorizado(String pathSalida, String parameters) throws JsonProcessingException, SinResultadosException {
        ConfigureTasklet configure = new ConfigureTasklet();
        String periodo_yyyyMM = JsonPathUtil.read(parameters, "$.data.props.periodo");
        Date dtPeriodo = restUtil.obtenerFecha(periodo_yyyyMM);
        String fuente  = "CLIENTES_NO_SECTORIZADOS"; 
        fuente = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_CLIENTES_NO_SECTORIZADO).getTexto();
        configure.setNameConfigureTasklet("GENERAR"+ fuente);
        
        ConfigureFileLoadTasklet configureFileLoadTasklet = new ConfigureFileLoadTasklet();
        configureFileLoadTasklet.setTipoArchivo(JobLoadFactory.GENERAR_FORMATO_ARCHIVO_TEXTO);
        configureFileLoadTasklet.setArchivo(fuente +  "_" + FechaUtil.convertirACadena(dtPeriodo, "MMyyyy") + "." + JobLoadFactory.EXTENSION_TXT);
        //configureFileLoadTasklet.setArchivo(fuente + "." + JobLoadFactory.EXTENSION_TXT);
        configureFileLoadTasklet.setDirectorio(ArchivoUtil.completarSeparador(pathSalida));
        configureFileLoadTasklet.setFilaInicio(1);
        
        List<TextField> lstTextField = new ArrayList<TextField>();
        
        TextField textField = new TextField();
        textField.setCol(1);
        textField.setBegin(0);
        textField.setEnd(11);
        textField.setType(FieldType.STRING);
        textField.setName("DOCUMENTO");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(2);
        textField.setBegin(11);
        textField.setEnd(80);
        textField.setType(FieldType.STRING);
        textField.setName("NOMBRES");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(3);
        textField.setBegin(91);
        textField.setEnd(11);
        textField.setType(FieldType.STRING);
        textField.setName("CIIU");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(4);
        textField.setBegin(102);
        textField.setEnd(3);
        textField.setType(FieldType.STRING);
        textField.setName("PECNARES");
        lstTextField.add(textField);
        
        configureFileLoadTasklet.setCampos(lstTextField);

        configureFileLoadTasklet.setSentenciaInsercion("SELECT DOCUMENTO, NOMBRES, CIIU, PECNARES "
                + " FROM BALSEC.CLIENTE_NO_SECTORIZADO "
                + " WHERE TO_CHAR(PERIODO,'YYYYMM') = '"+ periodo_yyyyMM + "'" 
                + " AND CODDOC = 'R'" );
        
        List<ConfigureFileLoadTasklet> lstCnfgLoadTasklet = new ArrayList<ConfigureFileLoadTasklet>();
        lstCnfgLoadTasklet.add(configureFileLoadTasklet);
        configure.setConfigureFilesLoads(lstCnfgLoadTasklet);
        
        ObjectMapper map = new ObjectMapper();
        System.out.println(map.writeValueAsString(configure.getConfigureFilesLoads()));
        
        return configure;
    }
    
    private ConfigureTasklet loadConfigGenerarAgentesEconomicos(String pathSalida, String parameters) throws JsonProcessingException, SinResultadosException {
        ConfigureTasklet configure = new ConfigureTasklet();
        String periodoyyyyMM = JsonPathUtil.read(parameters, "$.data.props.periodo");
        Date dtPeriodo = FechaUtil.convertirAFecha(periodoyyyyMM+"01",FechaUtil.YYYYMMDD);
        String NombreArchivo  = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_AGENTES_ECONOMICOS).getTexto();
        String fuente = parametroService.obtener(Constante.KeyParametro.ARCHIVO_AGENTESECONOMICOS).getTexto();
        configure.setNameConfigureTasklet("GENERAR_"+ fuente);
        
        ConfigureFileLoadTasklet configureFileLoadTasklet = new ConfigureFileLoadTasklet();
        configureFileLoadTasklet.setTipoArchivo(JobLoadFactory.GENERAR_FORMATO_ARCHIVO_TEXTO);
        //configureFileLoadTasklet.setArchivo(fuente +  "_" + FechaUtil.convertirACadena(dtPeriodo, "MMyyyy") + "." + JobLoadFactory.EXTENSION_TXT);
        configureFileLoadTasklet.setArchivo(NombreArchivo + "." + JobLoadFactory.EXTENSION_TXT);
        configureFileLoadTasklet.setDirectorio(ArchivoUtil.completarSeparador(pathSalida));
        configureFileLoadTasklet.setFilaInicio(1);
        
        List<TextField> lstTextField = new ArrayList<TextField>();
        
        TextField textField = new TextField();
        textField.setCol(1);
        textField.setBegin(0);
        textField.setEnd(12);
        textField.setType(FieldType.STRING);
        textField.setName("SECTOR");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(2);
        textField.setBegin(12);
        textField.setEnd(11);
        textField.setType(FieldType.STRING);
        textField.setName("DOCUMENTO");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(3);
        textField.setBegin(23);
        textField.setEnd(150);
        textField.setType(FieldType.STRING);
        textField.setName("CLIENTE");
        lstTextField.add(textField);
        
        configureFileLoadTasklet.setCampos(lstTextField);

        configureFileLoadTasklet.setSentenciaInsercion("SELECT SECTOR, DOCUMENTO, CLIENTE "
                + " FROM BALSEC.AGENTES_ECONOMICOS "
                + " WHERE TO_CHAR(PERIODO,'yyyyMM') = '"+ FechaUtil.convertirACadena(dtPeriodo,"yyyyMM") + "'"
                		+ " AND ESTADO = 'A'" );
        
        List<ConfigureFileLoadTasklet> lstCnfgLoadTasklet = new ArrayList<ConfigureFileLoadTasklet>();
        lstCnfgLoadTasklet.add(configureFileLoadTasklet);
        configure.setConfigureFilesLoads(lstCnfgLoadTasklet);
        
        ObjectMapper map = new ObjectMapper();
        System.out.println(map.writeValueAsString(configure.getConfigureFilesLoads()));
        
        return configure;
    }
    
    private ConfigureTasklet loadConfigGenerarArchivoModelo(String pathSalida, String parameters) throws JsonProcessingException, SinResultadosException {
        ConfigureTasklet configure = new ConfigureTasklet();
        String periodoyyyyMM = JsonPathUtil.read(parameters, "$.data.props.periodo");
        Date dtPeriodo = FechaUtil.convertirAFecha(periodoyyyyMM+"01",FechaUtil.YYYYMMDD);
        String NombreArchivo  = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_ARCHIVO_MODELO).getTexto();
        String fuente = parametroService.obtener(Constante.KeyParametro.ARCHIVO_ARCHIVOMODELO).getTexto();
        configure.setNameConfigureTasklet("GENERAR_"+ fuente);
        
        ConfigureFileLoadTasklet configureFileLoadTasklet = new ConfigureFileLoadTasklet();
        configureFileLoadTasklet.setTipoArchivo(JobLoadFactory.GENERAR_FORMATO_ARCHIVO_TEXTO);
        configureFileLoadTasklet.setArchivo(NombreArchivo + "." + JobLoadFactory.EXTENSION_TXT);
        configureFileLoadTasklet.setDirectorio(ArchivoUtil.completarSeparador(pathSalida));
        configureFileLoadTasklet.setFilaInicio(1);
        
        List<TextField> lstTextField = new ArrayList<TextField>();
        
        TextField textField = new TextField();
        textField.setCol(1);
        textField.setBegin(0);
        textField.setEnd(20);
        textField.setType(FieldType.STRING);
        textField.setName("CUENTA_CONTABLE");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(2);
        textField.setBegin(20);
        textField.setEnd(1);
        textField.setType(FieldType.STRING);
        textField.setName("OPCION");
        lstTextField.add(textField);
        
        textField = new TextField();
        textField.setCol(3);
        textField.setBegin(21);
        textField.setEnd(150);
        textField.setType(FieldType.STRING);
        textField.setName("DESCRIPCION");
        lstTextField.add(textField);
        
//        textField = new TextField();
//        textField.setCol(2);
//        textField.setBegin(20);
//        textField.setEnd(150);
//        textField.setType(FieldType.STRING);
//        textField.setName("DESCRIPCION");
//        lstTextField.add(textField);
        
//        textField = new TextField();
//        textField.setCol(3);
//        textField.setBegin(170);
//        textField.setEnd(1);
//        textField.setType(FieldType.STRING);
//        textField.setName("OPCION");
//        lstTextField.add(textField);
        
        configureFileLoadTasklet.setCampos(lstTextField);

        configureFileLoadTasklet.setSentenciaInsercion("SELECT RPAD(CUENTA_CONTABLE,20,'0') CUENTA_CONTABLE, DESCRIPCION, OPCION "
                + " FROM BALSEC.ARCHIVO_MODELO "
                + " WHERE TO_CHAR(PERIODO,'yyyyMM') = '"+ FechaUtil.convertirACadena(dtPeriodo,"yyyyMM") + "'" );
        
        List<ConfigureFileLoadTasklet> lstCnfgLoadTasklet = new ArrayList<ConfigureFileLoadTasklet>();
        lstCnfgLoadTasklet.add(configureFileLoadTasklet);
        configure.setConfigureFilesLoads(lstCnfgLoadTasklet);
        
        ObjectMapper map = new ObjectMapper();
        System.out.println(map.writeValueAsString(configure.getConfigureFilesLoads()));
        
        return configure;
    }
    
}
