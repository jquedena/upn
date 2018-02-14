package pe.edu.upn.encuesta.controller;

import com.indra.web.controller.ScrudController2;
import com.indra.web.exception.HttpException;
import com.indra.web.model.RequestModel;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upn.encuesta.entidad.Persona;

@Controller("personaController")
@Scope("prototype")
@RequestMapping("persona")
public class PersonaController extends ScrudController2<Persona> {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(PersonaController.class);

    @Override
    protected RequestModel<Persona> leerEntradaJSON(HttpServletRequest request) throws HttpException {
        return null;
    }
}
