/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upn.encuesta.service.impl;

import com.indra.core.dao.EntidadDAO2;
import com.indra.core.service.impl.HibernateScrudService2;
import org.springframework.stereotype.Service;
import pe.edu.upn.encuesta.entidad.Encuesta;
import pe.edu.upn.encuesta.service.EncuestaService;

@Service("encuestaService")
public class DbEncuestaService extends HibernateScrudService2<Encuesta, EntidadDAO2<Encuesta>> implements EncuestaService {

//    @Override
//    public Paginator<Encuesta> buscar(Encuesta item) throws SinResultadosException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Paginator<Encuesta> buscarPaginado(Encuesta item, Integer nroFilas, Integer pagina) throws SinResultadosException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Paginator<Encuesta> buscarPaginado(Encuesta item, IUsuario<?> usuario, Integer nroFilas, Integer pagina) throws SinResultadosException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Encuesta obtener(Long item) throws SinResultadosException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Encuesta insertar(Encuesta o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Encuesta actualizar(Encuesta item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<Encuesta> actualizar(List<Encuesta> item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void eliminar(Encuesta item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void eliminar(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void eliminar(List<Encuesta> item) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
}
