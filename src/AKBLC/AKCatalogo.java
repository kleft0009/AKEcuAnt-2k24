package AKBLC;

import java.util.HashMap;
import java.util.Map;

public class AKCatalogo {

    protected AKCatalogoDAO oCatalogoDAO = new AKCatalogoDAO();

    public Map<Integer, String> getAllHormigaTipo() throws Exception {
        return getMap(1);
    }

    public Map<Integer, String> getAllHormigaTipoSexo() throws Exception {
        return getMap(2);
    }

    public Map<Integer, String> getAllHormigaTipoEstado() throws Exception {
        return getMap(3);
    }

    private Map<Integer, String> getMap(Integer IdCatalogoPadre) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        for (AKCatalogoDTO pt : oCatalogoDAO.readByPadre(IdCatalogoPadre)) {
            map.put(pt.getIdCatalogoPadre(), pt.getNombre());  // Cambiar IdCatalogo por IdCatalogoPadre
        }
        return map;
    }
}
