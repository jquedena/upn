package pe.edu.upn.encuesta.seguridad.service;


import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.indra.core.exception.AutentificacionException;
import com.indra.core.exception.NoAutorizadoException;
import com.indra.core.service.FilterService;
import pe.edu.upn.encuesta.seguridad.bean.UsuarioAutentificado;

/**
 * 
 * @author JUAN
 *
 */
@Service("filterService")
public class FilterServiceImpl implements FilterService {

    // TODO: Revisar
    private static final Logger LOG = Logger.getLogger(FilterServiceImpl.class);
    
    /**
     * Precarga los roles IDM
     */
    @PostConstruct
    public void init() {
//        cache = new LinkedHashMap<String, Parametro>();
//        Parametro item = new Parametro();
//        item.setParametro(new Parametro());
//        item.getParametro().setId(Constante.KeyParametro.ROLES_IDM);
//        List<Parametro> parametros;
//        try {
//            parametros = parametroService.buscar(item).getListaEntidades();
//            for(Parametro param : parametros) {
//                cache.put(param.getCodigo(), param);
//            }
//        } catch (SinResultadosException e) {
//            LOG.error("init", e);
//        }
    }
    
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public UsuarioAutentificado loadUser(String codigoUsuario) throws NoAutorizadoException, AutentificacionException {
        return loadUser(codigoUsuario, "");
    }
    
    /**
     *  Autentificacion de usuario
     *  @param codigoUsuario
     *  @param contexto
     *  @return
     *  @throws NoAutorizadoException
     *  @throws AutentificacionException
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public UsuarioAutentificado loadUser(String codigoUsuario, String contexto) throws NoAutorizadoException, AutentificacionException {
        UsuarioAutentificado usuarioActual = new UsuarioAutentificado();

        usuarioActual.setCodigoRegistro("-------");
        usuarioActual.setNombreCompleto("Anonimo");
        usuarioActual.setCodigoCargo("---");

//        try {
//            Parametro param = parametroService.obtener(Constante.KeyParametro.ENDPOINT_WSLDAP3);
//            LOG.info(param.getTexto());
//            usuario = ldapClientWS.obtenerUsuario(codigoUsuario);
//        } catch (Exception e) {
//            LOG.info("Verificar el par\u00E1metro del endpoint");
//            usuario = new Usuario();
//            usuario.setPuesto(new Puesto());
//            usuario.setNombres(codigoUsuario);
//            usuario.setApellidos("Sin Servicio");
//            throw new AutentificacionException("No se pudo acceder al servicio web ws-ldap3", e);
//        }
//        
//        if (usuario != null) {
//            usuarioActual.obtenerDeUsuario(usuario);
//            obtainAccess(usuario, usuarioActual);
//            obtainAccessForDummies(codigoUsuario, usuarioActual);
//            
//            if(usuarioActual.getRoles().isEmpty()) {
//                LOG.error("No existen roles asignados al usuario [" + codigoUsuario + "]");
//                throw new NoAutorizadoException();
//            }
//            
//            usuarioActual.setRolActual(usuarioActual.getRoles().get(0));
//            
//            // Asignado las opciones de menu
//            usuarioActual.setListaOpciones(rolOpcionService.obtenerOpcionesPorRoles(usuarioActual.getRoles()));
//            UsuarioAutentificado usuarioAutentificado = WebServletContextListener.getBean("usuarioAutentificado");
//            BeanUtils.copyProperties(usuarioActual, usuarioAutentificado);
//        } else {
//            LOG.error("No existe el usuario [" + codigoUsuario + "]");
//            throw new NoAutorizadoException();
//        }
        return usuarioActual;
    }

}
