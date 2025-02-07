package AKBLC;

import AKDAC.AKDAO.AKCatalogoDAO;
import AKDAC.AKDTO.AKCatalogoDTO;
import java.util.HashMap;
import java.util.Map;

public class AKCatalogo {

    protected AKCatalogoDAO oCatalogoDAO = new AKCatalogoDAO();

    public Map<Integer, String> AKGetAllHormigaTipo() throws Exception {
        return AKGetMap(1);
    }

    public Map<Integer, String> AKGetAllHormigaTipoSexo() throws Exception {
        return AKGetMap(2);
    }

    public Map<Integer, String> AKGetAllHormigaTipoEstado() throws Exception {
        return AKGetMap(3);
    }

    public Map<Integer, String> AKGetAllHormigaTipoGeno() throws Exception {
        return AKGetMap(4);
    }

    public Map<Integer, String> AKGetAllHormigaTipoIngesta() throws Exception {
        return AKGetMap(5);
    }

    private Map<Integer, String> AKGetMap(Integer IdCatalogoPadre) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        for (AKCatalogoDTO pt : oCatalogoDAO.readByPadre(IdCatalogoPadre)) {
            map.put(pt.getIdCatalogo(), pt.getNombre());
        }
        return map;
    }

}
