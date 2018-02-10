package com.bbva.seguridad.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.Constante;
import pe.edu.upn.encuesta.entidad.Parametro;
import pe.edu.upn.encuesta.service.IParametroService;
import com.indra.core.service.Log4jService;

@Service("log4jService")
public class Log4jServiceImpl implements Log4jService, InitializingBean {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(Log4jService.class);
    private String contextName;
    private Map<Long, Parametro> params;

    @Resource(name = "parametroService")
    private IParametroService parametroService;

    @PostConstruct
    @Override
    @Transactional(readOnly = true)
    public void init() {
        List<Long> ids = new ArrayList<Long>();
        ids.add(Constante.KeyParametro.ROOT_CATEGORY);
        ids.add(Constante.KeyParametro.FILE);
        ids.add(Constante.KeyParametro.MAX_FILE_SIZE);
        ids.add(Constante.KeyParametro.MAX_BACKUP_INDEX);
        params = parametroService.listarPorIDs(ids);
    }

    @Override
    public String obtener(String key) {
        String value = "";

        try {
            if (Log4jService.ROOT_CATEGORY.equalsIgnoreCase(key)) {
                value = params.get(Constante.KeyParametro.ROOT_CATEGORY).getTexto();
            } else if (Log4jService.FILE.equalsIgnoreCase(key)) {
                value = params.get(Constante.KeyParametro.FILE).getTexto();
            } else if (Log4jService.MAX_BACKUP_INDEX.equalsIgnoreCase(key)) {
                value = params.get(Constante.KeyParametro.MAX_BACKUP_INDEX).getTexto();
            } else if (Log4jService.MAX_FILE_SIZE.equalsIgnoreCase(key)) {
                value = params.get(Constante.KeyParametro.MAX_FILE_SIZE).getTexto();
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
}
