package AKBLC.AKEntities;

/**
 * Interfaz que define el comportamiento de una hormiga en relación con su
 * alimentación.
 */
public interface IAKHormiga {

    /**
     * Método para simular el proceso de alimentación de una hormiga.
     *
     * @param alimento La ingesta nativa que la hormiga consumirá.
     * @return La hormiga después de haber consumido el alimento.
     */
    AKHormiga AKComer(AKIngestaNativa alimento);
}
