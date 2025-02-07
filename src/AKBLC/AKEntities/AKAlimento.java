package AKBLC.AKEntities;

public abstract class AKAlimento{
    private String tipo;
    
    public AKAlimento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public abstract void consumir();

    protected abstract void setEstado(String estado);
}
