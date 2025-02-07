package AKBLC.AKEntities;

/**
 * Representa un alimento abstracto dentro del sistema. Define atributos y
 * métodos comunes para todos los alimentos.
 */
public abstract class AKAlimento {

    private String tipo; // Tipo de alimento 

    /**
     * Constructor de la clase AKAlimento.
     *
     * @param tipo El tipo de alimento.
     */
    public AKAlimento(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el tipo de alimento.
     *
     * @return El tipo de alimento como una cadena de texto.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Método abstracto que define la acción de consumir el alimento.
     */
    public abstract void consumir();

    /**
     * Método abstracto para establecer el estado del alimento.
     *
     * @param estado El nuevo estado del alimento.
     */
    protected abstract void setEstado(String estado);
}
