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
import pe.edu.upn.encuesta.entidad.Encuesta;

/**
 *
 * @author JQUEDENA
 */
@Repository("encuestaDAO")
public class EncuestaDAO extends HibernateEntidadDAO2<Encuesta> {

    @Override
    protected Consulta<Encuesta> crearConsultaPaginado(Encuesta item) {
        Objeto encuesta = new Objeto(Encuesta.class.getCanonicalName(), item);
        CriteriaConsulta<Encuesta> consulta = new CriteriaConsulta<Encuesta>(getSession());
        return consulta.agregarEntidad(encuesta)
                .agregarCondicion(Condicion.igualA(encuesta, "nombre"))
                .agregarCondicion(Condicion.mayorIgualA(encuesta, "fechaInicio"))
                .agregarCondicion(Condicion.menorIgualA(encuesta, "fechaFin"))
                .ordenarDesc(encuesta, "fechaInicio");
    }

    @Override
    public Encuesta obtener(long id) {
        return super.obtener(id, Encuesta.class);
    }
    
}
