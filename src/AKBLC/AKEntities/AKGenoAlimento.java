package AKBLC.AKEntities;

/**
 * Representa un alimento con características genéticas dentro del sistema.
 * Extiende de AKIngestaNativa y proporciona un comportamiento abstracto para
 * aplicar evolución a una hormiga.
 */
public abstract class AKGenoAlimento extends AKIngestaNativa {

    private String característicasGenéticas; // Características genéticas del alimento

    /**
     * Aplica la evolución del alimento a una hormiga.
     *
     * @param hormiga La hormiga a la que se le aplicará la evolución.
     */
    public abstract void AKAplicarEvolucion(AKHormiga hormiga);

    /**
     * Constructor de la clase AKGenoAlimento.
     *
     * @param tipo Tipo de alimento genético.
     * @param característicasGenéticas Características genéticas del alimento.
     */
    public AKGenoAlimento(String tipo, String característicasGenéticas) {
        super();
        this.característicasGenéticas = característicasGenéticas;
    }
}
