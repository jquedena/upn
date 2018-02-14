package pe.edu.upn.encuesta.seguridad.service;

import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import com.indra.core.service.Log4jService;

@Service("log4jService")
public class Log4jServiceImpl implements Log4jService, InitializingBean {

    // TODO: Revisar
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Log4jService.class);
    private String contextName;

    @Override
    public String obtener(String key) {
        String value = "";

        try {
            if (Log4jService.ROOT_CATEGORY.equalsIgnoreCase(key)) {
//                value = params.get(Constante.KeyParametro.ROOT_CATEGORY).getTexto();
            } else if (Log4jService.FILE.equalsIgnoreCase(key)) {
//                value = params.get(Constante.KeyParametro.FILE).getTexto();
            } else if (Log4jService.MAX_BACKUP_INDEX.equalsIgnoreCase(key)) {
//                value = params.get(Constante.KeyParametro.MAX_BACKUP_INDEX).getTexto();
            } else if (Log4jService.MAX_FILE_SIZE.equalsIgnoreCase(key)) {
//                value = params.get(Constante.KeyParametro.MAX_FILE_SIZE).getTexto();
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener el valor de: [" + key + "]", e);
        }

        return value;
    }

    @Override
    public void test(PrintWriter out) {
        out.write("No implementado...");
    }

    @Override
    public String getContextName() {
        return contextName;
    }

    @Override
    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("afterPropertiesSet");
    }

    @Override
    public void init() {
        LOGGER.info("init");
    }
}
