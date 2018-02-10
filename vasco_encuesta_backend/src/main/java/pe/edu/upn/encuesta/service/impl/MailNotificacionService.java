package pe.edu.upn.encuesta.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import pe.edu.upn.encuesta.Constante;
import pe.edu.upn.encuesta.entidad.Parametro;
import pe.edu.upn.encuesta.service.IParametroService;
import com.indra.mail.MailException;
import com.indra.mail.Message;
import com.indra.mail.filter.FilterChainMail;
import com.indra.mail.manager.MailManager;

/**
 * Notificacion por correo cuando un archivo no carga
 * @author JUAN
 *
 */
@Service("mailNotificacionService")
public class MailNotificacionService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(MailNotificacionService.class);
    private MailManager mailManager;

    @Resource(name = "parametroService")
    private IParametroService parametroService;
    
    /**
     * Constructor
     */
    public MailNotificacionService() {
        mailManager = new MailManager();
        mailManager.setFilterChainMail(new FilterChainMail());
    }

    /**
     * Notifica un mensaje
     * @param mensaje
     * @return
     */
    public boolean notificar(Message mensaje) {
        return notificar(Collections.singletonList(mensaje));
    }
    
    /**
     * Notifica una lista de mensajes
     * @param mensajes
     * @return
     */
    public boolean notificar(List<Message> mensajes) {
        boolean result = true;
        List<Long> ids = new ArrayList<Long>();
        Map<Long, Parametro> params;

        try {
            ids.add(Constante.KeyParametro.HOST_MAIL);
            ids.add(Constante.KeyParametro.PORT_MAIL);
            ids.add(Constante.KeyParametro.USER_MAIL);
            ids.add(Constante.KeyParametro.PASSWORD_MAIL);
            ids.add(Constante.KeyParametro.LIST_SEND);
            params = parametroService.listarPorIDs(ids);
            
            for(Message m : mensajes) {
                sendMessage(m, params);
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener los parametros de configuraci\u00F3n", e);
            result = false;
        }

        return result;
    }
    
    private boolean sendMessage(Message m, Map<Long, Parametro> params) {
        boolean result = true;
        try {
            m.getHeader().setHost(params.get(Constante.KeyParametro.HOST_MAIL).getTexto());
            m.getHeader().setPort(params.get(Constante.KeyParametro.PORT_MAIL).getTexto());
            m.getHeader().setUserFrom("BALSEC - Balance Sectorial Institucional");
            m.getHeader().setPasswordFrom(params.get(Constante.KeyParametro.PASSWORD_MAIL).getTexto());
            m.getHeader().setEmailFrom(params.get(Constante.KeyParametro.USER_MAIL).getTexto());
            m.getHeader().setListTO(params.get(Constante.KeyParametro.LIST_SEND).getTexto());
            
            mailManager.getFilterChainMail().send(m);
        } catch (MailException e) {
            LOGGER.error("No se pudo enviar", e);
            result = false;
        } catch (Exception e) {
            LOGGER.error("Error no controlado", e);
            result = false;
        }
        
        return result;
    }
}
