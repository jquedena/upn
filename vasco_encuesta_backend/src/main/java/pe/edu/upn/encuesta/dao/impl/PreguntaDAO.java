/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.dao.impl;

import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Consulta;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pregunta obtener(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
