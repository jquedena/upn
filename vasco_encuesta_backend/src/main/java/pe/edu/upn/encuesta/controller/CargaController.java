package pe.edu.upn.encuesta.controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import pe.edu.upn.encuesta.Constante;
import pe.edu.upn.encuesta.entidad.ConsolidadoBsi;
import com.bbva.balsec.facade.IFuenteFacade;
import pe.edu.upn.encuesta.service.IConsolidadoBsiService;
import pe.edu.upn.encuesta.service.IParametroService;
import com.indra.constant.FormatoArchivo;
import com.indra.core.domain.Entidad;
import com.indra.core.exception.SinResultadosException;
import com.indra.exception.UtilException;
import com.indra.ireport.Column;
import com.indra.ireport.FactoryGeneratorReport;
import com.indra.ireport.GeneratorReport;
import com.indra.ireport.enums.PageType;
import com.indra.ireport.enums.TextAlign;
import com.indra.ireport.enums.TypeField;
import com.indra.util.ArchivoUtil;
import com.indra.util.FechaUtil;
import com.indra.util.JsonPathUtil;
import com.indra.util.db.Paginator;
import com.indra.web.controller.AbstractController;
import com.indra.web.exception.HttpException;
import com.jayway.jsonpath.TypeRef;

/**
 * Clase para la carga de archivos
 * @author JUAN
 *
 */
@Controller("cargaController")
@Scope("prototype")
public class CargaController extends AbstractController {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(CargaController.class);
    
    @Resource(name = "parametroService")
    private IParametroService parametroService;

    @Resource(name = "fuenteFacade")
    private IFuenteFacade fuenteFacade;
    
    @Resource(name = "consolidadoBsiService")
	private IConsolidadoBsiService consolidadoBsiService;
    
    /**
     * Copia un archivo al file server
     * @param request
     * @param response
     * @param nombreArchivo
     * @return
     * @throws SinResultadosException 
     * @throws IOException 
     * @throws UtilException 
     */
    @RequestMapping(value = "subir/{nombreArchivo}", method = RequestMethod.POST)
    @ResponseBody
    public void subir(MultipartHttpServletRequest request, HttpServletResponse response, @PathVariable("nombreArchivo") String nombreArchivo) throws HttpException {
        try {
            String path = parametroService.obtener(Constante.KeyParametro.DIRECTORIO_LOAD).getTexto();
            Iterator<String> itr = request.getFileNames();
            MultipartFile mpf = request.getFile(itr.next());
            ArchivoUtil.escribirArchivo(ArchivoUtil.completarSeparador(path) + nombreArchivo, mpf.getBytes());
            LOG.info(mpf.getSize() + " bytes size!");
            LOG.info(mpf.getOriginalFilename() + " uploaded!");
            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            throw new HttpException(e);
        }
    }

    /**
     * 
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "descargar", method = RequestMethod.POST)
    public void download(HttpServletRequest request, HttpServletResponse response) throws UtilException {
        try {
            String nombreArchivo = request.getParameter("nombreArchivo");
            String rutaArchivo = request.getParameter("rutaArchivo");
            
            generarArchivo(response, new FileInputStream(new File(rutaArchivo)), FormatoArchivo.BIN, nombreArchivo);
        } catch (Exception ex) {
            generarArchivoError(ex, response);
        }
    }
    
    @RequestMapping(value = "descargarMonitor", method = RequestMethod.POST)
    public void downloadMonit(HttpServletRequest request, HttpServletResponse response) throws UtilException {
        try {
            String nombreArchivo = request.getParameter("nombreArchivoMonitor");
            String rutaArchivo = request.getParameter("rutaArchivoMonitor");
            
            generarArchivo(response, new FileInputStream(new File(rutaArchivo)), FormatoArchivo.BIN, nombreArchivo);
        } catch (Exception ex) {
            generarArchivoError(ex, response);
        }
    }
    
    @RequestMapping(value = "descargarClienNoSec", method = RequestMethod.POST)
    public void downloadClienNoSec(HttpServletRequest request, HttpServletResponse response) throws UtilException {
        try {
        	
        	String pathSalida = ArchivoUtil.completarSeparador(parametroService.obtener(Constante.KeyParametro.DIRECTORIO_OUT).getTexto());
        	String fuente = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_CLIENTES_NO_SECTORIZADO).getTexto();
        	
            //String nombreArchivo = request.getParameter("nombreArchivoClienNoSec");
            String fecha = request.getParameter("fechaArchivoClienNoSec");
            
            String nombreArchivo = fuente+"_"+fecha;
            String rutaArchivo = pathSalida+fuente+"_"+fecha;
                        
            generarArchivo(response, new FileInputStream(new File(rutaArchivo)), FormatoArchivo.BIN, nombreArchivo);
            
            
        } catch (Exception ex) {
            generarArchivoError(ex, response);
        }
    }

    @RequestMapping(value = "descargarClienNoSecLog", method = RequestMethod.POST)
    public void downloadClienNoSecLog(HttpServletRequest request, HttpServletResponse response) throws UtilException {
        try {
        	
        	String pathSalida = ArchivoUtil.completarSeparador(parametroService.obtener(Constante.KeyParametro.DIRECTORIO_OUT).getTexto());
        	String fuente = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_CLIENTES_NO_SECTORIZADO).getTexto();
            
            //String nombreArchivolog = request.getParameter("nombreArchivoClienNoSecLog");
            String fecha = request.getParameter("fechaArchivoClienNoSecLog");
            
            String nombreArchivolog = "GENERAR"+fuente+"_"+fecha;
            String rutaArchivolog = pathSalida+"GENERAR"+fuente+"_"+fecha;
            
            //String rutaArchivolog = request.getParameter("rutaArchivoClienNoSecLog");
            
            generarArchivo(response, new FileInputStream(new File(rutaArchivolog)), FormatoArchivo.BIN, nombreArchivolog);
            
        } catch (Exception ex) {
            generarArchivoError(ex, response);
        }
    }
    
    @RequestMapping(value = "descargarBsi", method = RequestMethod.POST)
    public void downloadBsi(HttpServletRequest request, HttpServletResponse response) throws UtilException {
        try {
        	
        	String pathSalida = ArchivoUtil.completarSeparador(parametroService.obtener(Constante.KeyParametro.DIRECTORIO_OUT).getTexto());
        	String nombrePrefijoArchivo = parametroService.obtener(Constante.KeyParametro.ARCHIVOSALIDA_GENERAR_ARCHIVO_BSI).getTexto();
        	
            //String nombreArchivo = request.getParameter("nombreArchivoClienNoSec");
            String fecha = request.getParameter("fechaBsi");
            
            String nombreArchivo = nombrePrefijoArchivo+fecha;
            String rutaArchivo = pathSalida+nombrePrefijoArchivo+fecha;
                        
            generarArchivo(response, new FileInputStream(new File(rutaArchivo)), FormatoArchivo.BIN, nombreArchivo);
            
            
        } catch (Exception ex) {
            generarArchivoError(ex, response);
        }
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws UtilException 
     * @throws SinResultadosException 
     * @throws IOException 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "descargar/entrada", method = RequestMethod.POST)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws UtilException, SinResultadosException {
        String parameters = request.getParameter("params");
        String nombreArchivo = JsonPathUtil.read(parameters, "$.data.props.nombreArchivo");
        String title = JsonPathUtil.read(parameters, "$.data.props.titulo");
        String fuente = JsonPathUtil.read(parameters, "$.data.props.fuente");
        String periodo = JsonPathUtil.read(parameters, "$.data.props.periodo");
        
        
        List<Column> columns = new ArrayList<Column>();
        GeneratorReport vmGeneratorReport = null;
        Paginator<? extends Entidad> paginator = null;
        
        if (fuente.equals("CONSOLIDADO")){
        	columns.add(new Column("Cuenta", "cuentaContable", 100, TextAlign.LEFT_MIDDLE, TypeField.String));
            columns.add(new Column("Sector", "codigoSector", 100, TextAlign.LEFT_MIDDLE, TypeField.String));
            columns.add(new Column("Saldo Fin BC", "saldoFinBc", 120, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("Saldo Inicial", "saldoIni", 120, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("Saldo Final", "saldoFin", 120, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("Opci\u00F3n", "opcion", 80, TextAlign.LEFT_MIDDLE, TypeField.String));
            
            ConsolidadoBsi consolidadoBsi = JsonPathUtil.read(parameters, "$.data.entidad", new TypeRef<ConsolidadoBsi>() {});
			consolidadoBsi.setPeriodo(FechaUtil.convertirAFecha(periodo,"yyyyMM"));
			
			paginator = consolidadoBsiService.buscarPaginado(consolidadoBsi, null, null);
        } else if (fuente.equals("CUADRE")){
        	columns.add(new Column("Cuenta", "cuentaContable", 100, TextAlign.LEFT_MIDDLE, TypeField.String));
            columns.add(new Column("Saldo Inicial", "saldoIni", 120, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("Saldo Final", "saldoFin", 120, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("Saldo Fin BC", "saldoFinBc", 120, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("Diferencia", "diferencia", 120, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("Opci\u00F3n", "opcion", 80, TextAlign.LEFT_MIDDLE, TypeField.String));
            
            ConsolidadoBsi consolidadoBsi = JsonPathUtil.read(parameters, "$.data.entidad", new TypeRef<ConsolidadoBsi>() {});
			consolidadoBsi.setPeriodo(FechaUtil.convertirAFecha(periodo,"yyyyMM"));
        	
        	paginator = consolidadoBsiService.consultarCuadre(consolidadoBsi, null, null);
        	
        } else if (fuente.equals(parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO1).getCodigo())||
        		fuente.equals(parametroService.obtener(Constante.KeyParametro.ARCHIVO_ANEXO8).getCodigo()) ||
        		fuente.equals(parametroService.obtener(Constante.KeyParametro.ARCHIVO_SALDOSDIARIOS).getCodigo()) ||
        		fuente.equals(parametroService.obtener(Constante.KeyParametro.ARCHIVO_OTROSDISPONIBLESRESTRINGIDOS).getCodigo())){
        	
        	columns.add(new Column("Cuenta Contable", "cuentaContable", 100, TextAlign.LEFT_MIDDLE, TypeField.String));
            columns.add(new Column("Descripci\u00F3n", "descripcion", 250, TextAlign.LEFT_MIDDLE, TypeField.String));
            columns.add(new Column("Saldo", "saldo", 100, TextAlign.LEFT_MIDDLE, TypeField.BigDecimal));
            columns.add(new Column("C\u00F3odigo Central", "codigoCentral", 120, TextAlign.LEFT_MIDDLE, TypeField.String));
            columns.add(new Column("DOI", "doi", 90, TextAlign.LEFT_MIDDLE, TypeField.String));
            columns.add(new Column("Fuente", "fuente", 100, TextAlign.LEFT_MIDDLE, TypeField.String));
            
            paginator = (Paginator<Entidad>) fuenteFacade.listar(fuente, periodo, parameters);
            
        }
        
        vmGeneratorReport = FactoryGeneratorReport.create(FactoryGeneratorReport.XLSX);
        vmGeneratorReport.setFileName(nombreArchivo)
            .setFileTemplate(getClass().getResource("/template.jrxml.vm").getFile())
            .setPageType(PageType.FREE)
            .setTitle(title)
            .setSubTitle("")
            .addAllColumns(columns);
        
        /*try {*/
            
            vmGeneratorReport.setListObject(paginator.getListaEntidades());
        /*} catch(SinResultadosException e) {
            LOG.debug("", e);
            vmGeneratorReport.setListObject(new ArrayList<Object>());
        }*/
        
        descargar(vmGeneratorReport, request, response);
    }
}
