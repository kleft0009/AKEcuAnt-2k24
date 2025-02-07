package AKBLC.AKEntities;

public class AKXY extends AKGenoAlimento {

    public AKXY(String tipo, String característicasGenéticas) {
        super(tipo, característicasGenéticas);
    }

    @Override
    public void AKAplicarEvolucion(AKHormiga hormiga) {
        hormiga.setTipo("Tipo XY");
    }

    @Override
    public boolean AKInyectar(AKGenoAlimento genoAlimento) {
        throw new UnsupportedOperationException("Unimplemented method 'inyectar'");
    }

    @Override
    protected AKXY getInyectadoCon() {
        System.out.println("Error en RAXX");
        throw new UnsupportedOperationException("Unimplemented method 'getInyectadoCon'");
    }
}
