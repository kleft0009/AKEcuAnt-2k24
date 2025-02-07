package AKBLC.AKEntities;

import AKBLC.AKBLFactory;
import AKDAC.AKDAO.AKHormigaDAO;
import AKDAC.AKDTO.AKHormigaDTO;
import java.util.List;

/**
 * Representa una hormiga dentro del sistema. Implementa la interfaz IAKHormiga
 * y proporciona métodos para manipular datos de la hormiga.
 */
public class AKHormiga implements IAKHormiga {

    private int id; // Identificador único de la hormiga
    private String tipo; // Tipo de hormiga
    private String estado; // Estado actual de la hormiga (ej. "VIVA", "MUERTA")
    private String nombre; // Nombre de la hormiga
    protected AKHormigaDTO hormigaDTO = new AKHormigaDTO(); // DTO para la persistencia de datos
    private AKBLFactory<AKHormigaDTO> bl = new AKBLFactory<>(AKHormigaDAO::new); // Capa de negocio
    public AKIngestaNativa aComido; // Último alimento consumido por la hormiga

    /**
     * Constructor por defecto de AKHormiga.
     */
    public AKHormiga() {
    }

    /**
     * Constructor de AKHormiga con identificador.
     *
     * @param id Identificador único de la hormiga.
     */
    public AKHormiga(int id) {
        this.id = id;
        this.estado = "VIVA";
        this.tipo = "Desconocida"; // Tipo por defecto
        this.nombre = "Hormiga_" + id;
    }

    /**
     * Agrega una nueva hormiga al sistema.
     *
     * @param idCatalogoTipo Tipo de hormiga.
     * @param idCatalogoSexo Sexo de la hormiga.
     * @param idCatalogoEstado Estado actual de la hormiga.
     * @param idCatalogoIngestaNativa Tipo de ingesta de la hormiga.
     * @param idCatalogoGenoAllimento Características genéticas del alimento.
     * @param nombre Nombre de la hormiga.
     * @return true si se agregó correctamente, false en caso contrario.
     * @throws Exception Si ocurre un error en la operación.
     */
    public boolean add(Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado,
            Integer idCatalogoIngestaNativa, Integer idCatalogoGenoAllimento, String nombre) throws Exception {
        hormigaDTO = new AKHormigaDTO(idCatalogoTipo, idCatalogoSexo, idCatalogoEstado,
                idCatalogoIngestaNativa, idCatalogoGenoAllimento, idCatalogoIngestaNativa, nombre);
        return bl.add(hormigaDTO);
    }

    /**
     * Actualiza los datos de la hormiga en el sistema.
     *
     * @param idCatalogoTipo Tipo de hormiga.
     * @param idCatalogoSexo Sexo de la hormiga.
     * @param idCatalogoEstado Estado actual de la hormiga.
     * @param idCatalogoIngestaNativa Tipo de ingesta de la hormiga.
     * @param idCatalogoGenoAllimento Características genéticas del alimento.
     * @param nombre Nombre de la hormiga.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre un error en la operación.
     */
    public boolean upd(Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado,
            Integer idCatalogoIngestaNativa, Integer idCatalogoGenoAllimento, String nombre) throws Exception {
        if (hormigaDTO != null) {
            hormigaDTO.setIdCatalogoTipo(idCatalogoTipo);
            hormigaDTO.setIdCatalogoSexo(idCatalogoSexo);
            hormigaDTO.setIdCatalogoEstado(idCatalogoEstado);
            hormigaDTO.setIdCatalogoIngestaNativa(idCatalogoIngestaNativa);
            hormigaDTO.setIdCatalogoGenoAllimento(idCatalogoGenoAllimento);
            hormigaDTO.setNombre(nombre);
            return bl.upd(hormigaDTO);
        }
        return false;
    }

    /**
     * Obtiene la lista de todas las hormigas registradas en el sistema.
     *
     * @return Lista de objetos AKHormigaDTO.
     * @throws Exception Si ocurre un error en la operación.
     */
    public List<AKHormigaDTO> getAll() throws Exception {
        return bl.getAll();
    }

    /**
     * Obtiene una hormiga por su identificador.
     *
     * @param id Identificador de la hormiga.
     * @return Objeto AKHormigaDTO correspondiente a la hormiga.
     * @throws Exception Si ocurre un error en la operación.
     */
    public AKHormigaDTO getBy(Integer id) throws Exception {
        hormigaDTO = bl.getBy(id);
        return hormigaDTO;
    }

    /**
     * Elimina la hormiga actual del sistema.
     *
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws Exception Si ocurre un error en la operación.
     */
    public boolean del() throws Exception {
        if (hormigaDTO != null) {
            return bl.del(hormigaDTO.getIdHormiga());
        }
        return false;
    }

    /**
     * Obtiene el DTO de la hormiga.
     *
     * @return Objeto AKHormigaDTO.
     */
    public AKHormigaDTO get() {
        return hormigaDTO;
    }

    /**
     * Establece un nuevo DTO para la hormiga.
     *
     * @param hormigaDTO Nuevo objeto AKHormigaDTO.
     */
    public void set(AKHormigaDTO hormigaDTO) {
        this.hormigaDTO = hormigaDTO;
    }

    /**
     * Obtiene el identificador de la hormiga.
     *
     * @return ID de la hormiga.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece un nuevo identificador para la hormiga.
     *
     * @param id Nuevo ID de la hormiga.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de la hormiga.
     *
     * @return Tipo de hormiga.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece un nuevo tipo para la hormiga.
     *
     * @param tipo Nuevo tipo de hormiga.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el estado actual de la hormiga.
     *
     * @return Estado de la hormiga.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece un nuevo estado para la hormiga.
     *
     * @param estado Nuevo estado de la hormiga.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el nombre de la hormiga.
     *
     * @return Nombre de la hormiga.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece un nuevo nombre para la hormiga.
     *
     * @param nombre Nuevo nombre de la hormiga.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Simula el proceso de alimentación de la hormiga.
     *
     * @param ingestaNativa Tipo de alimento consumido.
     * @return La misma instancia de AKHormiga tras la alimentación.
     */
    public AKHormiga AKComer(AKIngestaNativa ingestaNativa) {
        // Aquí iría la lógica para la alimentación
        return this;
    }

    /**
     * Representación en cadena de la hormiga, incluyendo su estado y tipo de
     * alimento consumido.
     *
     * @return Cadena con los datos de la hormiga.
     */
    @Override
    public String toString() {
        return hormigaDTO
                + " - " + aComido.getTipo()
                + "\n";
    }
}
