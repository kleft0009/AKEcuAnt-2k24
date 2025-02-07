package AKDAC.AKDAO;

import AKDAC.AKDTO.AKCatalogoDTO;
import AKDAC.AKDataHelperSQLite;
import AKDAC.AKIDAO;
import AKInfra.AKAppException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AKCatalogoDAO extends AKDataHelperSQLite implements AKIDAO<AKCatalogoDTO> {

    private static final String SELECT_QUERY = "SELECT "
            + "IdCatalogo, "
            + "IdCatalogoPadre, "
            + "Nombre, "
            + "Detalle, "
            + "Estado, "
            + "FechaCrea, "
            + "FechaModifica "
            + " FROM AKCatalogo WHERE Estado = 'A";

    @Override
    public AKCatalogoDTO newDTO(ResultSet rs) {
        try {
            return new AKCatalogoDTO(
                    rs.getInt("idCatalogo"),
                    rs.getInt("idCatalogoPadre"),
                    rs.getString("Nombre"),
                    rs.getString("Detalle"),
                    rs.getString("Estado"),
                    rs.getString("FechaCrea"),
                    rs.getString("FechaModifica")
            );
        } catch (SQLException e) {
            new AKAppException(e, getClass().getName(), "NewDTO()");
        }
        return null;
    }

    @Override
    public AKCatalogoDTO readBy(Integer id) throws Exception {
        String query = SELECT_QUERY + " AND IdCatalogo = ?";
        return executeReadBy(query, rs -> newDTO(rs), id);
    }

    public List<AKCatalogoDTO> readByPadre(Integer idPadre) throws Exception {
        String query = "SELECT   h.IdCatalogo, "
                + "h.IdCatalogoPadre, "
                + "h.Nombre, "
                + "h.Detalle, "
                + "h.Estado, "
                + "h.FechaCrea, "
                + "h.FechaModifica "
                + " FROM Catalogo p "
                + " JOIN Catalogo h ON h.IdCatalogoPadre = p.IdCatalogo "
                + " WHERE h.Estado = 'A' AND p.IdCatalogo = ?";

        return executeReadByPadre(query, rs -> newDTO(rs), idPadre);
    }

    @Override
    public List<AKCatalogoDTO> readAll() throws Exception {
        String query = SELECT_QUERY;
        return executeReadAll(query, rs -> newDTO(rs));
    }

    @Override
    public boolean create(AKCatalogoDTO dto) throws Exception {
        String query = "INSERT INTO Catalogo ("
                + "IdCatalogoPadre, "
                + "Nombre,"
                + "Detalle,"
                + "Estado,"
                + "FechaCrea"
                + ") VALUES (?,?,?,?,?)";
        return execute(query, dto.getIdCatalogoPadre(),
                dto.getNombre(),
                dto.getDetalle(),
                dto.getEstado(),
                dto.getFechaCrea());

    }

    @Override
    public boolean update(AKCatalogoDTO dto) throws Exception {
        String query = "UPDATE AKCatalogo SET "
                + "IdCatalogoPadre = ?, "
                + "Nombre = ?, "
                + "Detalle = ?, "
                + "FechaModifica = ? "
                + "WHERE IdCatalogo = ?";
        return execute(query, dto.getIdCatalogoPadre(),
                dto.getNombre(),
                dto.getDetalle(),
                getDateTimeNow(),
                dto.getIdCatalogo());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE AKCatalogo SET Estado = ?, FechaModifica = ? WHERE IdCatalogo = ?";
        return execute(query, "X", getDateTimeNow(), id);
    }

    @Override
    public Integer getMaxRow() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'getMaxRow'");
    }

}
