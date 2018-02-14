package pe.edu.upn.encuesta.seguridad.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.indra.web.controller.ErrorHandlerController;
import com.indra.web.enums.TipoResultado;
import com.indra.web.exception.HttpException;
import com.indra.web.model.Model;
import com.indra.web.model.RequestModel;

/**
 * Clase inicial
 * @author JUAN
 *
 */
@Controller("accesoController")
@Scope("prototype")
public class AccesoController extends ErrorHandlerController<Object> {

    // TODO: Revisar
    private static final long serialVersionUID = 1L;

    /**
     * Mensaje de cierre de sesion
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/signOut")
    @ResponseBody
    public Model<Object> signOut(HttpServletRequest request, HttpServletResponse response) {
        Model<Object> model = new Model<Object>();
        model.setTipoResultado(TipoResultado.ERROR);
        model.setMensaje("Su sesi\u00F3n ha terminado");
        configureResponse(response, request);
        adicionarError(response, "signOut", model.getMensaje(), HttpServletResponse.SC_UNAUTHORIZED);
        return model;
    }

    /**
     * Sesion expirada
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/expire")
    @ResponseBody
    public Model<Object> expire(HttpServletRequest request, HttpServletResponse response) {
        Model<Object> model = new Model<Object>();
        model.setTipoResultado(TipoResultado.ERROR);
        model.setMensaje("Su sesi\u00F3n ha caducado");
        configureResponse(response, request);
        adicionarError(response, "expire", model.getMensaje(), HttpServletResponse.SC_UNAUTHORIZED);
        return model;
    }

    /**
     * No autorizado
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/notAuthorized")
    @ResponseBody
    public Model<Object> notAuthorized(HttpServletRequest request, HttpServletResponse response) {
        Model<Object> model = new Model<Object>();
        model.setTipoResultado(TipoResultado.ERROR);
        model.setMensaje("Usted no cuenta con un rol dentro del sistema");
        configureResponse(response, request);
        adicionarError(response, "notAuthorized", model.getMensaje(), HttpServletResponse.SC_UNAUTHORIZED);
        return model;
    }

    /**
     * Error
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/error")
    @ResponseBody
    public Model<Object> error(HttpServletRequest request, HttpServletResponse response) {
        Model<Object> model = new Model<Object>();
        model.setTipoResultado(TipoResultado.ERROR);
        model.setMensaje(request.getAttribute("mensaje").toString());
        configureResponse(response, request);
        adicionarError(response, "error", model.getMensaje(), HttpServletResponse.SC_UNAUTHORIZED);
        return model;
    }
    
    /**
     * Obtiene el menu de opciones del usuario
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/menu")
    public void menu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        configureResponse(response, request);
//        String menu = MenuUtil.dibujarMenu(((UsuarioAutentificado) usuario).getListaOpciones());
        ServletOutputStream ou = response.getOutputStream();
//        ou.write(menu.getBytes());
        adicionarError(response, "usuarioAutentificado", imprimir(usuario), HttpServletResponse.SC_OK);
    }

    @Override
    protected RequestModel<Object> leerEntradaJSON(HttpServletRequest request) throws HttpException {
        throw new UnsupportedOperationException();
    }
}
