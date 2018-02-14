/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.dao.impl;

import com.indra.core.dao.impl.HibernateEntidadDAO2;
import com.indra.core.hibernate.Consulta;
import org.springframework.stereotype.Repository;
import pe.edu.upn.encuesta.entidad.Inquilino;

/**
 *
 * @author JQUEDENA
 */
@Repository("inquilinoDAO")
public class InquilinoDAO extends HibernateEntidadDAO2<Inquilino> {

    @Override
    protected Consulta<Inquilino> crearConsultaPaginado(Inquilino item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Inquilino obtener(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
