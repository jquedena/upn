/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.dao.impl;

import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Condicion;
import com.indra.core.hibernate.Consulta;
import com.indra.core.hibernate.CriteriaConsulta;
import com.indra.core.hibernate.Objeto;
import org.springframework.stereotype.Repository;
import pe.edu.upn.encuesta.entidad.Pregunta;

/**
 *
 * @author JQUEDENA
 */
@Repository("preguntaDAO")
public class PreguntaDAO extends HibernateEntidadDAO2<Pregunta> {

    @Override
    protected Consulta<Pregunta> crearConsultaPaginado(Pregunta item) {
        Objeto pregunta = new Objeto(Pregunta.class.getCanonicalName(), item);
        Objeto encuesta = new Objeto(Pregunta.class.getCanonicalName(), pregunta.getNombreEntidad(), item.getEncuesta());
        CriteriaConsulta<Pregunta> consulta = new CriteriaConsulta<Pregunta>(getSession());
        return consulta.agregarEntidad(pregunta)
                .agregarCondicion(Condicion.igualA(encuesta, "id"))
                .ordenarAsc(pregunta, "id");
    }

    @Override
    public Pregunta obtener(long id) {
        return super.obtener(id, Pregunta.class);
    }
    
}
