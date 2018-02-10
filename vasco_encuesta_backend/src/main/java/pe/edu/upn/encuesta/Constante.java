package pe.edu.upn.encuesta;

import java.text.MessageFormat;

/**
 * 
 * @author JUAN
 *
 */
public class Constante {

    private Constante() {
    }

    /**
     * Estados del proceso batch
     * @author JUAN
     *
     */
    public static class BatchEstado {
        public static final String STRING_VALUE_COMPLETED = "COMPLETED";
        public static final String STRING_VALUE_STARTED = "STARTED";
        public static final String STRING_VALUE_FAILED = "FAILED";
        public static final String STRING_VALUE_UNKNOWN = "UNKNOWN";
        public static final String STRING_VALUE_EXECUTING = "EXECUTING";
        public static final String STRING_VALUE_PARTITIAL = "PARTITIAL";
        public static final String STRING_VALUE_STARTING = "STARTING";
        public static final String STRING_VALUE_ABANDONED = "ABANDONED";
        public static final String LABEL_PROCESO_COMPLETED = "COMPLETADO";
        public static final String LABEL_PROCESO_STARTED = "INICIADO";
        public static final String LABEL_PROCESO_FAILED = "FALLADO";
        public static final String LABEL_PROCESO_UNKNOWN = "DESCONOCIDO";
        public static final String LABEL_PROCESO_EXECUTING = "EN EJECUCI\u00D3N";
        public static final String LABEL_PROCESO_PARTITIAL = "PARCIAL";
        public static final String LABEL_PROCESO_ABANDONED = "ABANDONADO";
    }
    
    /**
     * Mensajes de Validacion
     * @author JUAN
     *
     */
    public static class MensajeValidacion {
        public static final MessageFormat REQUERIDO = new MessageFormat("El campo {0} es obligatorio");
        public static final MessageFormat MAXIMO_CARACTERES = new MessageFormat("{0} debe tener como m\u00E1ximo {1} caracteres");
    }
    
    /**
     * Parametros del sistema
     * @author JUAN
     *
     */
    public static class KeyParametro {
    
        // Tipos de parametros
        public static final Long PB_TIPOS_PARAMETRO = 0L;
        public static final Long PB_TIPO_PADRE = 1L;
        public static final Long PB_TIPO_HIJO = 2L;
        
        // Configuracion de log4j
        public static final Long ROOT_CATEGORY = 5L;
        public static final Long FILE = 6L;
        public static final Long MAX_FILE_SIZE = 7L;
        public static final Long MAX_BACKUP_INDEX = 8L;
        
        // Configuracion de servidor de correo
        public static final Long HOST_MAIL = 10L;
        public static final Long PORT_MAIL = 11L;
        public static final Long USER_MAIL = 12L;
        public static final Long LIST_SEND = 13L;
        public static final Long PASSWORD_MAIL = 14L;
        public static final Long MODE_DUMMY = 15L;
        public static final Long MAIL_DUMMY = 16L;
    
        // Configuracion para acceso al servicio IDM
        public static final Long ENDPOINT_WSLDAP3 = 17L;
        public static final Long ROLES_IDM = 20L;
        
        // Directorios
        public static final Long DIRECTORIO_LOAD = 18L;
        public static final Long DIRECTORIO_OUT = 19L;
        
        public static final Long ARCHIVO_INPUTS = 222L;
        public static final Long ARCHIVO_ANEXO8 = 223L;
        public static final Long ARCHIVO_ANEXO8_01 = 332L;
        public static final Long ARCHIVO_ANEXO8_02 = 333L;
        public static final Long ARCHIVO_ANEXO8_03 = 334L;
        public static final Long ARCHIVO_ANEXO8_04 = 335L;
        public static final Long ARCHIVO_ANEXO8_05 = 336L;
        public static final Long ARCHIVO_ANEXO1 = 224L;
        public static final Long ARCHIVO_SALDOSDIARIOS = 225L;
        public static final Long ARCHIVO_OTROSDISPONIBLESRESTRINGIDOS = 226L;
        public static final Long ARCHIVO_DETALLECLIENTESECTORIZADO = 227L;
        public static final Long ARCHIVO_BALANCECOMPROBACION = 228L;
        public static final Long ARCHIVO_ARCHIVOMODELO = 229L;
        public static final Long ARCHIVO_AGENTESECONOMICOS = 230L;
        public static final Long TAREAS_PROGRAMADAS = 362L;
        public static final String CODIGO_CLIENTE_NO_SECTORIZADO = "CLIENNOSEC";
        public static final String PARAMETRO_INPUT_HOST = "PARAMETROINPUT";
        //public static final String AGREGAR_AGENTE_ECONO = "AGREGARAGENTE";
        
        //Configuracion para la carga de periodos
        public static final Long RANGO_PERIODOS = 231L;
        public static final String PERIODO_DIA = "DIA";
        public static final String PERIODO_MES = "MES";
        public static final String PERIODO_ANIO = "A\u00D1O";
        public static final String FORMATO_PERIODO_DIA = "dd MMMM yyyy";
        public static final String FORMATO_PERIODO_MES = "MMM'-'yyyy";
        public static final String FORMATO_PERIODO_ANIO = "yyyy";
        
        //Configuracion para la accion a realizar
        public static final String ACCION_REGISTRAR = "registrar";
        public static final String ACCION_ACTUALIZAR = "actualizar";
        public static final String ACCION_ELIMINAR = "eliminar";
        public static final String AGREGAR_AGENTE_ECONO = "agregaragente";
        
        

        public static final Long ARCHIVOSALIDA_GENERAR_ARCHIVO_INPUTHOST = 311L;
        public static final Long ARCHIVOSALIDA_GENERAR_ARCHIVO_BSI = 310L;
        public static final Long ARCHIVOSALIDA_GENERAR_CLIENTES_NO_SECTORIZADO = 309L;
        public static final Long ARCHIVOSALIDA_GENERAR_AGENTES_ECONOMICOS = 308L;
        public static final Long ARCHIVOSALIDA_GENERAR_ARCHIVO_MODELO = 307L;
		
        
    }
    
    /**
     * Cargas
     * @author JUAN
     *
     */
    public static class TipoCarga {
        public static final String CARGA_ANEXO1 = "CARGA_ANEXO1";
        public static final String CARGA_ANEXO8 = "CARGA_ANEXO8";
        public static final String CARGA_SALDOSDIARIOS = "CARGA_SALDIARIO";
        public static final String CARGA_OTROSDISPONIBLES = "CARGA_OTROSDISPO";
        public static final String CARGA_CLIENTESECTORIZADO = "CARGA_CLIENSECTO";
        public static final String CARGA_ARCHIVOMODELO = "CARGA_ARCHIMODEL";
        public static final String CARGA_BALANCECOMPROBACION = "CARGA_BALCOMP";
        public static final String CARGA_AGENTESECONOMICOS = "CARGA_AGENTECONO";
    }
    
    /**
     * Procesos
     * @author JUAN
     *
     */
    public static class Proceso {
        public static final String PROCESO_ANEXO1 = "PROCESO_ANEXO1";
        public static final String PROCESO_ANEXO8 = "PROCESO_ANEXO8";
        public static final String PROCESO_SALDIARIO = "PROCESO_SALDIARIO";
        public static final String PROCESO_OTROSDISPO = "PROCESO_OTROSDISPO";
        public static final String PROCESO_ENTRADAS = "PROCESO_ENTRADAS";
        public static final String PROCESO_ARCHIMODEL = "PROCESO_ARCHIMODEL";
        public static final String PROCESO_AGENTECONO = "PROCESO_AGENTECONO";
        public static final String PROCESO_CONSOLIDADO_BSI = "GENERAR_CONSOLIDADO_BSI";
    }
    
    /**
     * Procesos de salida
     * @author JUAN
     *
     */
    public static class TipoProcedimiento {
        public static final String GENERAR_CONSOLIDADO = "generarConsolidado";
        public static final String GENERAR_CLIENTES_NO_SECTORIZADOS = "generarClientesNoSectorizados";
        public static final String GENERAR_ARCHIVO_NO_SECTORIZADOS = "generarArchivoNoSectorizados";
        public static final String GENERAR_BSI = "generarBSI";
        public static final String GENERAR_SALDOS_INICIALES = "generarSaldosIniciales";
        public static final String GENERAR_ARCHIVO_INPUT = "generarInput";
        // Procedimientos almacenados
        public static final String PROC_GENERAR_CONSOLIDADO = "BALSEC.PKG_CARGA_INPUTS.PR_GENERAR_CONSOLIDADO";
        public static final String PROC_GENERAR_CLIENTES_NO_SECTORIZADOS = "BALSEC.PKG_CARGA_INPUTS.PR_GENERAR_CLIENTES_NO_SECTOR";
        public static final String PROC_GENERAR_BSI = "BALSEC.PKG_CARGA_INPUTS.PR_GENERAR_BSI";
        public static final String PROC_GENERAR_SALDOS_INICIALES = "BALSEC.PKG_CARGA_INPUTS.PR_GENERAR_SALDOS_INICIALES";
    }
    
    public static final String ESTADO_ACTIVO = "A";
    public static final String ESTADO_INACTIVO = "I";
    
    public static final String[] ARCHIVOS_ANEXO8 = {
    	"ANEXO8_01",
    	"ANEXO8_02",
    	"ANEXO8_03",
    	"ANEXO8_04",
    	"ANEXO8_07"
    };
}
