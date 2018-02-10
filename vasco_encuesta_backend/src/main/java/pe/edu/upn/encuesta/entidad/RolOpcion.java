package pe.edu.upn.encuesta.entidad;

import com.indra.core.domain.Entidad;

public class RolOpcion extends Entidad {

    private static final long serialVersionUID = 8033730430930730407L;
    private String rolIdm;
    private Modulo modulo;
    private String invisible;
    private String inactivo;

    public String getRolIdm() {
        return rolIdm;
    }

    public void setRolIdm(String rolIdm) {
        this.rolIdm = rolIdm;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getInvisible() {
        return invisible;
    }

    public void setInvisible(String invisible) {
        this.invisible = invisible;
    }

    public String getInactivo() {
        return inactivo;
    }

    public void setInactivo(String inactivo) {
        this.inactivo = inactivo;
    }

}
