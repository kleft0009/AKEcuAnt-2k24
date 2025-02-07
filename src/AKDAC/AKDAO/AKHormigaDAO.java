package AKDAC.AKDAO;

import AKDAC.AKDTO.AKHormigaDTO;
import AKDAC.AKDataHelperSQLite;
import AKDAC.AKIDAO;
import AKInfra.AKAppException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AKHormigaDAO extends AKDataHelperSQLite implements AKIDAO<AKHormigaDTO> {

    private static final String SELECT_QUERY = "SELECT "
            + "IdHormiga, "
            + "IdCatalogoTipo, "
            + "IdCatalogoSexo, "
            + "IdCatalogoEstado, "
            + "IdCatalogoIngestaNativa, "
            + "IdCatalogoGenoAllimento, "
            + "Nombre, "
            + "Estado, "
            + "FechaCrea, "
            + "FechaModifica "
            + " FROM Hormiga WHERE Estado = 'A'";

    @Override
    public AKHormigaDTO newDTO(ResultSet rs) {
        try {
            return new AKHormigaDTO(
                    rs.getInt("IdHormiga"),
                    rs.getInt("IdCatalogoTipo"),
                    rs.getInt("IdCatalogoSexo"),
                    rs.getInt("IdCatalogoEstado"),
                    rs.getInt("IdCatalogoIngestaNativa"),
                    rs.getInt("IdCatalogoGenoAllimento"),
                    rs.getString("Nombre"),
                    rs.getString("Estado"),
                    rs.getString("FechaCrea"),
                    rs.getString("FechaModifica")
            );
        } catch (SQLException e) {
            new AKAppException(e, getClass().getName(), "newDTO()");
        }
        return null;
    }

    @Override
    public AKHormigaDTO readBy(Integer id) throws Exception {
        String query = SELECT_QUERY + " AND IdHormiga = ?";
        return executeReadBy(query, rs -> newDTO(rs), id);
    }

    public List<AKHormigaDTO> readByPadre(Integer idPadre) throws Exception {
        String query = "SELECT h.IdHormiga, "
                + "h.IdCatalogoTipo, "
                + "h.IdCatalogoSexo, "
                + "h.IdCatalogoEstado, "
                + "h.IdCatalogoIngestaNativa, "
                + "h.IdCatalogoGenoAllimento, "
                + "h.Nombre, "
                + "h.Estado, "
                + "h.FechaCrea, "
                + "h.FechaModifica "
                + " FROM Hormiga h "
                + " JOIN Catalogo c ON h.IdCatalogoTipo = c.IdCatalogo "
                + " WHERE c.Nombre = 'HormigaTipo' AND h.IdCatalogoTipo = ?";
        return executeReadByPadre(query, rs -> newDTO(rs), idPadre);
    }

    @Override
    public List<AKHormigaDTO> readAll() throws Exception {
        String query = SELECT_QUERY;
        return executeReadAll(query, rs -> newDTO(rs));
    }

    @Override
    public boolean create(AKHormigaDTO dto) throws Exception {
        String query = "INSERT INTO Hormiga ("
                + "IdCatalogoTipo, "
                + "IdCatalogoSexo, "
                + "IdCatalogoEstado, "
                + "IdCatalogoIngestaNativa, "
                + "IdCatalogoGenoAllimento, "
                + "Nombre, "
                + "Estado, "
                + "FechaCrea"
                + ") VALUES (?,?,?,?,?,?,?,?)";
        return execute(query,
                dto.getIdCatalogoTipo(),
                dto.getIdCatalogoSexo(),
                dto.getIdCatalogoEstado(),
                dto.getIdCatalogoIngestaNativa(),
                dto.getIdCatalogoGenoAllimento(),
                dto.getNombre(),
                dto.getEstado(),
                dto.getFechaCrea());
    }

    @Override
    public boolean update(AKHormigaDTO dto) throws Exception {
        String query = "UPDATE Hormiga SET "
                + "IdCatalogoTipo = ?, "
                + "IdCatalogoSexo = ?, "
                + "IdCatalogoEstado = ?, "
                + "IdCatalogoIngestaNativa = ?, "
                + "IdCatalogoGenoAllimento = ?, "
                + "Nombre = ?, "
                + "Estado = ?, "
                + "FechaModifica = ? "
                + "WHERE IdHormiga = ?";
        return execute(query,
                dto.getIdCatalogoTipo(),
                dto.getIdCatalogoSexo(),
                dto.getIdCatalogoEstado(),
                dto.getIdCatalogoIngestaNativa(),
                dto.getIdCatalogoGenoAllimento(),
                dto.getNombre(),
                dto.getEstado(),
                getDateTimeNow(),
                dto.getIdHormiga());
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Hormiga SET Estado = ?, FechaModifica = ? WHERE IdHormiga = ?";
        return execute(query, "X", getDateTimeNow(), id);
    }

    @Override
    public Integer getMaxRow() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'getMaxRow'");
    }
}
