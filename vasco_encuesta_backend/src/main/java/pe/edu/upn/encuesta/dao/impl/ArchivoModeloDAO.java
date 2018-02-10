/**
 * 
 */
package pe.edu.upn.encuesta.dao.impl;
import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.IArchivoModeloDAO;
import pe.edu.upn.encuesta.entidad.ArchivoModelo;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * @author JUAN
 *
 */

@Repository("archivoModeloDAO")
public class ArchivoModeloDAO extends HibernateEntidadDAO2<ArchivoModelo> implements IArchivoModeloDAO {

	private static final long serialVersionUID = 1L;

	@Override
    public ArchivoModelo obtener(long id) {
        return obtener(id, ArchivoModelo.class);
    }
	
	@Override
	protected Consulta<ArchivoModelo> crearConsultaPaginado(ArchivoModelo item) {
		Objeto oAgente = new Objeto(ArchivoModelo.class.getCanonicalName(), item);
        CriteriaConsulta<ArchivoModelo> consulta = new CriteriaConsulta<ArchivoModelo>(getSession());
        return consulta.agregarEntidad(oAgente)
	            .agregarCondicion(Condicion.igualA(oAgente, "id"))
	            .agregarCondicion(Condicion.igualA(oAgente, "periodo"))
	            .agregarCondicion(Condicion.igualA(oAgente, "cuentaContable"))
	            .agregarCondicion(Condicion.contiene(oAgente, "descripcion")) 
	            .agregarCondicion(Condicion.igualA(oAgente, "opcion"))
	            .ordenarAsc(oAgente, "cuentaContable");
	}

}
