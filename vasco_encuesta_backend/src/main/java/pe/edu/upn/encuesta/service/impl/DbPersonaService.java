/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.service.impl;

import com.indra.core.dao.EntidadDAO2;
import com.indra.core.service.impl.HibernateScrudService2;
import org.springframework.stereotype.Service;
import pe.edu.upn.encuesta.entidad.Persona;
import pe.edu.upn.encuesta.service.PersonaService;

@Service
public class DbPersonaService extends HibernateScrudService2<Persona, EntidadDAO2<Persona>> implements PersonaService {
    
}
