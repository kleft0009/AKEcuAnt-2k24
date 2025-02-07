package AKBLC.AKEntities;

/**
 * Representa un tipo de ingesta nativa dentro del sistema. Sirve como una base
 * abstracta para definir la relación entre un alimento genético y su inyección
 * en una entidad.
 */
public abstract class AKIngestaNativa {

    protected AKGenoAlimento inyectadoCon; // Alimento genético inyectado en la ingesta

    /**
     * Inyecta un alimento genético en la ingesta nativa. Las subclases deben
     * proporcionar una implementación específica.
     *
     * @param genoAlimento Alimento genético a inyectar.
     * @return true si la inyección fue exitosa, false en caso contrario.
     */
    public abstract boolean AKInyectar(AKGenoAlimento genoAlimento);

    /**
     * Obtiene el tipo de alimento inyectado. Si no hay alimento inyectado,
     * devuelve "Desconocido".
     *
     * @return Tipo de alimento inyectado o "Desconocido" si no hay ninguno.
     */
    public String getTipo() {
        if (inyectadoCon != null) {
            return inyectadoCon.getTipo();  // Llama al método getTipo() de AKGenoAlimento
        }
        return "Desconocido";  // Si no hay alimento inyectado
    }

    /**
     * Obtiene la información sobre el tipo específico de inyección realizada.
     * Debe ser implementado por las subclases.
     *
     * @return Un objeto de tipo AKXY que representa la inyección realizada.
     */
    protected abstract AKXY getInyectadoCon();
}
