/**
 * 
 */
package pe.edu.upn.encuesta.dao.impl;

import org.springframework.stereotype.Repository;

import pe.edu.upn.encuesta.dao.ILogTempDAO;
import pe.edu.upn.encuesta.entidad.LogTemp;
import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;

/**
 * @author JUAN
 *
 */

@Repository("logTempDAO")
public class LogTempDAO extends HibernateEntidadDAO2<LogTemp> implements ILogTempDAO {
	
	private static final long serialVersionUID = 1L;

	@Override
    public LogTemp obtener(long id) {
        return obtener(id, LogTemp.class);
    }
	
	@Override
	protected Consulta<LogTemp> crearConsultaPaginado(LogTemp item) {
		Objeto oLogTemp = new Objeto(LogTemp.class.getCanonicalName(), item);
        CriteriaConsulta<LogTemp> consulta = new CriteriaConsulta<LogTemp>(getSession());
        return consulta.agregarEntidad(oLogTemp)
	            .agregarCondicion(Condicion.igualA(oLogTemp, "id"))
	            .agregarCondicion(Condicion.igualA(oLogTemp, "tipoArchivo"))
	            .agregarCondicion(Condicion.igualA(oLogTemp, "descripcionError"))
	            .agregarCondicion(Condicion.igualA(oLogTemp, "tipoError"))
	            .ordenarAsc(oLogTemp, "id");
	}

	
}
