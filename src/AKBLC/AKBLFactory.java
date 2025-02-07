package AKBLC;

import AKDAC.AKIDAO;
import java.util.List;
import java.util.function.Supplier;

/**
 * Clase genérica que actúa como una fábrica para manejar operaciones de base de
 * datos a través de la abstracción de un DAO (Data Access Object).
 *
 * @param <T> Tipo de objeto que será gestionado por el DAO.
 */
public class AKBLFactory<T> {

    private final AKIDAO<T> oDAO; // DAO que manejará las operaciones de base de datos.

    /**
     * Constructor que recibe un proveedor de DAO y lo inicializa.
     *
     * @param supplier Proveedor que crea una instancia de AKIDAO para el tipo
     * T.
     */
    public AKBLFactory(Supplier<AKIDAO<T>> supplier) {
        this.oDAO = supplier.get();
    }

    /**
     * Obtiene todos los registros del tipo T almacenados en la base de datos.
     *
     * @return Lista de objetos de tipo T.
     * @throws Exception Si ocurre un error en la consulta.
     */
    public List<T> getAll() throws Exception {
        return oDAO.readAll();
    }

    /**
     * Obtiene un registro específico por su ID.
     *
     * @param id Identificador único del objeto.
     * @return Objeto de tipo T correspondiente al ID proporcionado.
     * @throws Exception Si ocurre un error en la consulta.
     */
    public T getBy(Integer id) throws Exception {
        return oDAO.readBy(id);
    }

    /**
     * Agrega un nuevo objeto a la base de datos.
     *
     * @param oT Objeto a agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre un error en la inserción.
     */
    public boolean add(T oT) throws Exception {
        return oDAO.create(oT);
    }

    /**
     * Actualiza un objeto existente en la base de datos.
     *
     * @param oT Objeto con la información actualizada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre un error en la actualización.
     */
    public boolean upd(T oT) throws Exception {
        return oDAO.update(oT);
    }

    /**
     * Elimina un objeto de la base de datos basado en su ID.
     *
     * @param id Identificador del objeto a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre un error en la eliminación.
     */
    public boolean del(Integer id) throws Exception {
        return oDAO.delete(id);
    }
}
