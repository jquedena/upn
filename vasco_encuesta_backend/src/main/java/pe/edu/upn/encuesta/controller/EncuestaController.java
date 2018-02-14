package pe.edu.upn.encuesta.controller;

import com.indra.web.controller.ScrudController2;
import com.indra.web.exception.HttpException;
import com.indra.web.model.RequestModel;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upn.encuesta.entidad.Encuesta;

@Controller("encuestaController")
@Scope("prototype")
@RequestMapping("encuesta")
public class EncuestaController extends ScrudController2<Encuesta> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(EncuestaController.class);

    @Override
    protected RequestModel<Encuesta> leerEntradaJSON(HttpServletRequest request) throws HttpException {
        return null;
    }
}
