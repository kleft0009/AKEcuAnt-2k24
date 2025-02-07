package AKBLC.AKEntities;

/**
 * Representa una larva dentro del sistema. Es una subclase de AKHormiga y se
 * inicializa con el tipo "Larva".
 */
public class AKHLarva extends AKHormiga {

    /**
     * Constructor de la clase AKHLarva.
     *
     * @param id Identificador único de la larva.
     */
    public AKHLarva(int id) {
        super(id);
        this.setTipo("Larva"); // Establece el tipo específico como "Larva"
    }
}
