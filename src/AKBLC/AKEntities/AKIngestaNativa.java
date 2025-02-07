package AKBLC.AKEntities;

public abstract class AKIngestaNativa {

    protected AKGenoAlimento inyectadoCon;

    public abstract boolean AKInyectar(AKGenoAlimento genoAlimento);

    public String getTipo() {
        if (inyectadoCon != null) {
            return inyectadoCon.getTipo();  // Llama al método getTipo() de Alimento
        }
        return "Desconocido";  // Si no hay alimento inyectado
    }

    protected abstract AKXY getInyectadoCon();
}
