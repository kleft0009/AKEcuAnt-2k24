package AKBLC.AKEntities;

import java.util.List;

import AKBLC.BLFactory;
import AKDAC.DAO.HormigaDAO;
import AKDAC.DTO.HormigaDTO;

public class AKHormiga implements IHormiga {

    protected HormigaDTO hormigaDTO = new HormigaDTO();
    private BLFactory<HormigaDTO> bl = new BLFactory<>(AKHormigaDAO::new);
    public IngestaNativa aComido;

    public AKHormiga() {

    }

    public boolean add(Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado, String nombre) throws Exception {
        hormigaDTO = new HormigaDTO(idCatalogoTipo, idCatalogoSexo, idCatalogoEstado, nombre);
        return bl.add(hormigaDTO);
    }

    public boolean upd(Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado, String nombre) throws Exception {
        if (hormigaDTO != null) {
            hormigaDTO.setIdCatalogoTipo(idCatalogoTipo);
            hormigaDTO.setIdCatalogoSexo(idCatalogoSexo);
            hormigaDTO.setIdCatalogoEstado(idCatalogoEstado);
            hormigaDTO.setNombre(nombre);
            return bl.upd(hormigaDTO);

        }
        return false;
    }

    public List<HormigaDTO> getAll() throws Exception {
        return bl.getAll();
    }

    public HormigaDTO getBy(Integer id) throws Exception {
        hormigaDTO = bl.getBy(id);
        return hormigaDTO;
    }

    public boolean del() throws Exception {
        if (hormigaDTO != null) {
            return bl.del(hormigaDTO.getIdHormiga());
        }
        return false;
    }

    public HormigaDTO get() {
        return hormigaDTO;
    }

    public void set(HormigaDTO hormigaDTO) {
        this.hormigaDTO = hormigaDTO;
    }

    public AKHormiga(int id, String tipo, String sexo, String estado) {
        // this.id = id;
        // this.tipo = tipo;
        // this.sexo = sexo;
        // this.estado = estado;
    }

    // public Integer getId(){
    //     return id;
    // }
    // public void setId(Integer id){
    //     this.id = id;
    // }
    @Override
    public String toString() {
        return hormigaDTO
                + " - " + aComido.getTipo()
                + "\n";
    }

    @Override
    public AKHormiga comer(IngestaNativa alimento) {
        this.aComido = alimento;

        if (hormigaDTO.getEstado().equals("Muerta")) {
            hormigaDTO.setEstado("Muertada");
            return this;
        }

        // Se usa instanceof para verificar el tipo de alimento
        if (this instanceof HSoldado) {
            if (!(alimento.getInyectadoCon() instanceof XX)) {
                hormigaDTO.setEstado("Muertada :C");
            }
        } else if (this instanceof HLarva) {
            // Aquí estamos verificando si el alimento inyectado es una instancia de Carnivoro
            if (alimento.getInyectadoCon() instanceof Carnivoro && alimento.getInyectadoCon() instanceof XY) {
                return new HSoldado(this.hormigaDTO.getIdHormiga());
            }
            if (!(alimento.getInyectadoCon() instanceof Nectarivoro)) {
                this.hormigaDTO.setEstado("Muertada :C");
            }
        }

        return this;
    }

}
