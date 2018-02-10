package pe.edu.upn.encuesta.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.encuesta.Constante;
import pe.edu.upn.encuesta.dao.IModuloDAO;
import pe.edu.upn.encuesta.entidad.Modulo;
import pe.edu.upn.encuesta.service.IModuloService;
import com.indra.core.exception.ValidadorException;
import com.indra.core.service.impl.HibernateScrudService2;

@Service("moduloService")
public class ModuloService extends HibernateScrudService2<Modulo, IModuloDAO> implements IModuloService {

    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void inicializando() {
        getGestor()
            .registrarPropiedad("nombre", true, Constante.MensajeValidacion.REQUERIDO.format(new String[]{"m\u00f3dulo"}))
            .addLongitud(50L, 1L, "El cod UO debe tener como m\u00E1ximo 20 caracteres");
        
        getGestor()
            .registrarPropiedad("descripcion", true, Constante.MensajeValidacion.REQUERIDO.format(new String[]{"descripci\u00f3n"}))
            .addLongitud(200L, 1L, "La descripci\u00f3n debe tener como m\u00E1ximo 500 caracteres");
    }
    

    @Autowired
    public void setModuloDAO(@Qualifier("moduloDAO") IModuloDAO moduloDAO) {
        super.setEntidadDAO(moduloDAO);
    }

    @Override
    @Transactional
    public boolean cambiarEstado(List<Modulo> listaModuloIds, String estado) throws ValidadorException {
        List<Long> listaIds = new ArrayList<Long>();
        for (Modulo p : listaModuloIds) {
            listaIds.add(p.getId());
        }
        return getHibernateDAO().cambiarEstado(listaIds, estado);
    }

}
