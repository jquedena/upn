/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pe.edu.upn.encuesta.domain.TipoControl;

/**
 *
 * @author JQUEDENA
 */
@Stateless
public class TipoControlFacade extends AbstractFacade<TipoControl> {

    @PersistenceContext(unitName = "Encuesta_v4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoControlFacade() {
        super(TipoControl.class);
    }
    
}
