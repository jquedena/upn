package pe.edu.upn.encuesta.dao.transformer;

import java.util.List;

import org.hibernate.transform.ResultTransformer;

/**
 * Solo se queda con el primer elemento del arreglo de entidades
 * producido por hibernate
 * @author JUAN
 *
 */
public class PrimerElementoTransformer implements ResultTransformer {

    private static final long serialVersionUID = 1L;

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        return tuple[0];
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public List transformList(List collection) {
        return collection;
    }
}
