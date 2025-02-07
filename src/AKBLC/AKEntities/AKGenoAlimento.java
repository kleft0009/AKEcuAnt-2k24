package AKBLC.AKEntities;

public abstract class AKGenoAlimento extends AKIngestaNativa{
    
        private String característicasGenéticas;    
    
        public abstract void AKAplicarEvolucion(AKHormiga hormiga);
    
        public AKGenoAlimento(String tipo, String característicasGenéticas) {
            super();
            this.característicasGenéticas = característicasGenéticas;
    }

}