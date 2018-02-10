package pe.edu.upn.encuesta.dao;

import java.util.List;

import pe.edu.upn.encuesta.entidad.Parametro;
import com.indra.core.dao.EntidadDAO2;

/**
 * 
 * @author JUAN
 *
 */
public interface IParametroDAO extends EntidadDAO2<Parametro> {

    /**
     * 
     * @param ids
     * @return
     */
    List<Parametro> listarPorIDs(List<Long> ids);
    
    /**
     * 
     * @param ids
     * @return
     */
    List<Parametro> listarPorIDsPadres(List<Long> ids);
    
    /**
     * 
     * @param idTipo
     * @param idPadre
     * @param nombre
     * @param idEstado
     * @return
     */
    List<Parametro> listar(long idTipo, List<Long> idPadre, String nombre, String idEstado);
    
    /**
     * Lista de p\u00E1rametros padres activos
     * @return Lista de p\u00E1rametros
     */
    List<Parametro> listarPadres();

    /**
     * Obtiene el p\u00E1rametro a trav\u00E9s de su ID
     * @param idParametro
     * @return Un p\u00E1rametros, nulo si no existe
     */
    Parametro obtener(long idParametro);

    /**
     * Verifica que el c\u00F3digo no este registrado
     * @param codigo
     * @param idTipo
     * @param idParametro
     * @param idPadre
     * @return verdadero si existe
     */
    boolean codigoExiste(String codigo, long idTipo, long idParametro, long idPadre);

    /**
     * Verifica que el nombre no este registrado
     * @param nombre
     * @param idTipo
     * @param idParametro
     * @param idPadre
     * @return verdadero si existe
     */
    boolean nombreExiste(String nombre, long idTipo, long idParametro, long idPadre);

    /**
     * Obtiene el p\u00E1rametro a partir de PARAMETRO_ID y CODIGO
     * @param idPadre
     * @param codigo
     * @return Un p\u00E1rametros, nulo si no existe
     */
    Parametro obtenerParametroPorCodigo(Long idPadre, String codigo);
}
