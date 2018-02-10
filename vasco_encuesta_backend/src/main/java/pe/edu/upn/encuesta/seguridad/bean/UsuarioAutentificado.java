package com.bbva.seguridad.bean;

import java.util.List;

import pe.edu.upn.encuesta.entidad.RolOpcion;
import com.indra.core.seguridad.RolSistema;
import com.indra.core.seguridad.UsuarioSistema;

/**
 * Representa al usuario autentificado
 * @author JUAN
 *
 */
public class UsuarioAutentificado extends UsuarioSistema<RolSistema> {

    private static final long serialVersionUID = 1L;
    private List<RolOpcion> listaOpciones;
    
    public List<RolOpcion> getListaOpciones() {
        return listaOpciones;
    }

    public void setListaOpciones(List<RolOpcion> listaOpciones) {
        this.listaOpciones = listaOpciones;
    }

    /**
     * Convierte un objeto Usuario a UsuarioAutentificado
     * @return
     */
    public UsuarioAutentificado obtenerDeUsuario() {
//        this.setCodigoRegistro(usuario.getUsuario());
//        this.setNombreCompleto(usuario.getNombres() + " " + usuario.getApellidos());
//        this.setCodigoCargo(usuario.getPuesto().getNombreCargoFuncionalLocal());
//        this.setCodigoOficina(usuario.getCodigoCentro());
//        this.setNombreOficina(usuario.getDescripcionCentro());
//        this.setNombreArea(usuario.getDescripcionUnidadOrganizativa9());
//        this.setCorreoElectronico(usuario.getEMail());
//        this.setAnexo(usuario.getTelefono2());
        
        return this;
    }
}
