    package AKBLC.AKEntities;

    import java.util.List;

    import AKBLC.AKBLFactory;
    import AKDAC.AKDAO.AKHormigaDAO;
    import AKDAC.AKDTO.AKHormigaDTO;

    public class AKHormiga implements IAKHormiga {

        private int id;
        private String tipo;
        private String estado;
        private String nombre;
        protected AKHormigaDTO hormigaDTO = new AKHormigaDTO();
        private AKBLFactory<AKHormigaDTO> bl = new AKBLFactory<>(AKHormigaDAO::new);
        public AKIngestaNativa aComido;

        public AKHormiga() {
        }

        public boolean add(Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado, Integer idCatalogoIngestaNativa, Integer idCatalogoGenoAllimento, String nombre) throws Exception {
            hormigaDTO = new AKHormigaDTO(idCatalogoTipo, idCatalogoSexo, idCatalogoEstado, idCatalogoIngestaNativa, idCatalogoGenoAllimento, idCatalogoIngestaNativa, nombre);
            return bl.add(hormigaDTO);
        }

        public boolean upd(Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado, Integer idCatalogoIngestaNativa, Integer idCatalogoGenoAllimento, String nombre) throws Exception {
            if (hormigaDTO != null) {
                hormigaDTO.setIdCatalogoTipo(idCatalogoTipo);
                hormigaDTO.setIdCatalogoSexo(idCatalogoSexo);
                hormigaDTO.setIdCatalogoEstado(idCatalogoEstado);
                hormigaDTO.setIdCatalogoIngestaNativa(idCatalogoIngestaNativa);
                hormigaDTO.setIdCatalogoGenoAllimento(idCatalogoGenoAllimento);
                hormigaDTO.setNombre(nombre);
                return bl.upd(hormigaDTO);
            }
            return false;
        }

        public List<AKHormigaDTO> getAll() throws Exception {
            return bl.getAll();
        }

        public AKHormigaDTO getBy(Integer id) throws Exception {
            hormigaDTO = bl.getBy(id);
            return hormigaDTO;

        }

        public boolean del() throws Exception {
            if (hormigaDTO != null) {
                return bl.del(hormigaDTO.getIdHormiga());
            }
            return false;
        }

        public AKHormigaDTO get() {
            return hormigaDTO;
        }

        public void set(AKHormigaDTO hormigaDTO) {
            this.hormigaDTO = hormigaDTO;
        }

        public AKHormiga(int id) {
            this.id = id;
            this.estado = "VIVA";
            this.tipo = "Desconocida"; // o cualquier otro valor por defecto
            this.nombre = "Hormiga_" + id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        // Método para simular el proceso de comer
        public AKHormiga AKComer(AKIngestaNativa ingestaNativa) {
            // Aquí iría la lógica para la alimentación
            return this;  // Devuelve la misma hormiga tras alimentarse
        }

        @Override
        public String toString() {
            return hormigaDTO
                    + " - " + aComido.getTipo()
                    + "\n";
        }

        // @Override
        // public RAHormiga comer(IngestaNativa alimento) {
        //     this.aComido = alimento;
        //     if (hormigaDTO.getEstado().equals("Muerta")) {
        //         hormigaDTO.setEstado("Muertada");
        //         return this;
        //     }
        //     // Se usa instanceof para verificar el tipo de alimento
        //     if (this instanceof RAHRastreadora) {
        //         if (!(alimento.getInyectadoCon() instanceof RAXX)) {
        //             hormigaDTO.setEstado("Muertada :C");
        //         }
        //     } else if (this instanceof RAHLarva) {
        //         // Aquí estamos verificando si el alimento inyectado es una instancia de Carnivoro
        //         if (alimento.getInyectadoCon() instanceof RACarnivoro && alimento.getInyectadoCon() instanceof RAXY) {
        //             return new RAHRastreadora();
        //         }
        //         if (!(alimento.getInyectadoCon() instanceof RANectarivoro)) {
        //             this.hormigaDTO.setEstado("Muertada :C");
        //         }
        //     }
        //     return this;
        // }
    }
