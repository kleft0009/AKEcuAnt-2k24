package AKBLC.AKEntities;

public class AKCarnivoro extends AKIngestaNativa {

    @Override
    public boolean AKInyectar(AKGenoAlimento genoAlimento) {
        // Lógica específica para Carnívoro
        genoAlimento.AKAplicarEvolucion(new AKHormiga(0));  // Puede alimentar la hormiga
        return true;
    }

    @Override
    protected AKXY getInyectadoCon() {
        System.out.println("Error en RACarnivoro");
        throw new UnsupportedOperationException("Unimplemented method 'getInyectadoCon'");
    }
}
