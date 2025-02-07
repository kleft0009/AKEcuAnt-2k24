package AKBLC.AKEntities;

/**
 * Representa IngestaNativa carnívoro dentro del sistema. Extiende de
 * AKIngestaNativa y define su propio comportamiento al ingerir alimentos.
 */
public class AKCarnivoro extends AKIngestaNativa {

    /**
     * Aplica la evolución de un alimento a un genoalimento.
     *
     * @param genoAlimento El alimento genético a inyectar.
     * @return true si el alimento se inyecta correctamente.
     */
    @Override
    public boolean AKInyectar(AKGenoAlimento genoAlimento) {
        genoAlimento.AKAplicarEvolucion(new AKHormiga(0));  // Puede alimentar la hormiga
        return true;
    }

    /**
     * Tipo de inyección aplicada. En esta clase, el método no está implementado
     * y lanza una excepción.
     *
     * @return No retorna un valor, ya que lanza una excepción.
     * @throws UnsupportedOperationException porque el método no está
     * implementado.
     */
    @Override
    protected AKXY getInyectadoCon() {
        System.out.println("Error en RACarnivoro");
        throw new UnsupportedOperationException("Unimplemented method 'getInyectadoCon'");
    }
}
