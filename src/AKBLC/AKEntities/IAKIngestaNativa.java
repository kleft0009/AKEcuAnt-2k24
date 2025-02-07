package AKBLC.AKEntities;

/**
 * Interfaz que define el comportamiento de una ingesta nativa en relación con
 * su consumo.
 */
public interface IAKIngestaNativa {

    /**
     * Método para consumir una ingesta nativa.
     *
     * @param alimento La ingesta nativa que será consumida.
     */
    void AKComer(AKIngestaNativa alimento);
}
