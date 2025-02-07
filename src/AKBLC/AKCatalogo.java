package AKBLC;

import AKDAC.AKDAO.AKCatalogoDAO;
import AKDAC.AKDTO.AKCatalogoDTO;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que gestiona la obtención de diferentes tipos de catálogos relacionados
 * con hormigas.
 */
public class AKCatalogo {

    /**
     * Objeto DAO para la interacción con la base de datos de catálogos.
     */
    protected AKCatalogoDAO oCatalogoDAO = new AKCatalogoDAO();

    /**
     * Obtiene un mapa de todos los tipos de hormigas disponibles.
     *
     * @return Un {@code Map} donde la clave es el ID del tipo de hormiga y el
     * valor es su nombre.
     * @throws Exception Si ocurre un error en la consulta a la base de datos.
     */
    public Map<Integer, String> AKGetAllHormigaTipo() throws Exception {
        return AKGetMap(1);
    }

    /**
     * Obtiene un mapa con los tipos de hormigas categorizados por sexo.
     *
     * @return Un {@code Map} con los identificadores y nombres de los tipos de
     * hormigas por sexo.
     * @throws Exception Si ocurre un error en la consulta a la base de datos.
     */
    public Map<Integer, String> AKGetAllHormigaTipoSexo() throws Exception {
        return AKGetMap(2);
    }

    /**
     * Obtiene un mapa con los tipos de hormigas categorizados por estado.
     *
     * @return Un {@code Map} con los identificadores y nombres de los tipos de
     * hormigas por estado.
     * @throws Exception Si ocurre un error en la consulta a la base de datos.
     */
    public Map<Integer, String> AKGetAllHormigaTipoEstado() throws Exception {
        return AKGetMap(3);
    }

    /**
     * Obtiene un mapa con los tipos de hormigas categorizados por genética.
     *
     * @return Un {@code Map} con los identificadores y nombres de los tipos de
     * hormigas por genética.
     * @throws Exception Si ocurre un error en la consulta a la base de datos.
     */
    public Map<Integer, String> AKGetAllHormigaTipoGeno() throws Exception {
        return AKGetMap(4);
    }

    /**
     * Obtiene un mapa con los tipos de hormigas categorizados por ingesta
     * nativa.
     *
     * @return Un {@code Map} con los identificadores y nombres de los tipos de
     * hormigas por ingesta nativa.
     * @throws Exception Si ocurre un error en la consulta a la base de datos.
     */
    public Map<Integer, String> AKGetAllHormigaTipoIngesta() throws Exception {
        return AKGetMap(5);
    }

    /**
     * Método privado para obtener un mapa de catálogos basado en un ID de
     * categoría padre.
     *
     * @param IdCatalogoPadre ID del catálogo padre del cual obtener los datos.
     * @return Un {@code Map} con los identificadores y nombres de los elementos
     * del catálogo.
     * @throws Exception Si ocurre un error en la consulta a la base de datos.
     */
    private Map<Integer, String> AKGetMap(Integer IdCatalogoPadre) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        for (AKCatalogoDTO pt : oCatalogoDAO.readByPadre(IdCatalogoPadre)) {
            map.put(pt.getIdCatalogo(), pt.getNombre());
        }
        return map;
    }
}
