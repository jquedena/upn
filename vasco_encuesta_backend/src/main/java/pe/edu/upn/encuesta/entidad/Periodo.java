package pe.edu.upn.encuesta.entidad;

import com.indra.core.domain.Entidad;

public class Periodo extends Entidad{
	
	private static final long serialVersionUID = 1L;
	private String descripcion;
    
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
   
    
}
