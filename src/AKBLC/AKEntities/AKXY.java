package AKBLC.AKEntities;

/**
 * Representa un tipo específico de alimento genético que puede afectar la
 * evolución de una hormiga.
 */
public class AKXY extends AKGenoAlimento {

    /**
     * Constructor de AKXY.
     *
     * @param tipo Tipo de alimento genético.
     * @param característicasGenéticas Características genéticas asociadas al
     * alimento.
     */
    public AKXY(String tipo, String característicasGenéticas) {
        super(tipo, característicasGenéticas);
    }

    /**
     * Aplica una evolución a una hormiga, cambiando su tipo a "Tipo XY".
     *
     * @param hormiga Hormiga a la que se le aplicará la evolución.
     */
    @Override
    public void AKAplicarEvolucion(AKHormiga hormiga) {
        hormiga.setTipo("Tipo XY");
    }

    /**
     * Método no implementado para inyectar un alimento genético. Lanza una
     * excepción si se intenta utilizar.
     *
     * @param genoAlimento Alimento genético a inyectar.
     * @return No retorna nada ya que lanza una excepción.
     * @throws UnsupportedOperationException Si se llama al método.
     */
    @Override
    public boolean AKInyectar(AKGenoAlimento genoAlimento) {
        throw new UnsupportedOperationException("Unimplemented method 'inyectar'");
    }

    /**
     * Método no implementado para obtener el alimento inyectado. Lanza una
     * excepción si se intenta utilizar.
     *
     * @return No retorna nada ya que lanza una excepción.
     * @throws UnsupportedOperationException Si se llama al método.
     */
    @Override
    protected AKXY getInyectadoCon() {
        System.out.println("Error en RAXX");
        throw new UnsupportedOperationException("Unimplemented method 'getInyectadoCon'");
    }
}
