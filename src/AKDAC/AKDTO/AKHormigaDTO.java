package AKDAC.AKDTO;

public class AKHormigaDTO {

    private Integer IdHormiga;
    private Integer IdCatalogoTipo;
    private Integer IdCatalogoSexo;
    private Integer IdCatalogoEstado;
    private Integer IdCatalogoIngestaNativa;
    private Integer IdCatalogoGenoAllimento;
    private String Nombre;
    private String Estado;
    private String FechaCrea;
    private String FechaModifica;

    public AKHormigaDTO(Integer idHormiga, Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado,
            Integer idCatalogoIngestaNativa, Integer idCatalogoGenoAllimento, String nombre) {
        IdHormiga = idHormiga;
        IdCatalogoTipo = idCatalogoTipo;
        IdCatalogoSexo = idCatalogoSexo;
        IdCatalogoEstado = idCatalogoEstado;
        IdCatalogoIngestaNativa = idCatalogoIngestaNativa;
        IdCatalogoGenoAllimento = idCatalogoGenoAllimento;
        Nombre = nombre;
    }

    public AKHormigaDTO(Integer idHormiga, Integer idCatalogoTipo, Integer idCatalogoSexo, Integer idCatalogoEstado,
            Integer idCatalogoIngestaNativa, Integer idCatalogoGenoAllimento, String nombre,
            String estado, String fechaCrea, String fechaModifica) {
        IdHormiga = idHormiga;
        IdCatalogoTipo = idCatalogoTipo;
        IdCatalogoSexo = idCatalogoSexo;
        IdCatalogoEstado = idCatalogoEstado;
        IdCatalogoIngestaNativa = idCatalogoIngestaNativa;
        IdCatalogoGenoAllimento = idCatalogoGenoAllimento;
        Nombre = nombre;
        Estado = estado;
        FechaCrea = fechaCrea;
        FechaModifica = fechaModifica;
    }

    public Integer getIdHormiga() {
        return IdHormiga;
    }

    public void setIdHormiga(Integer idHormiga) {
        IdHormiga = idHormiga;
    }

    public Integer getIdCatalogoTipo() {
        return IdCatalogoTipo;
    }

    public void setIdCatalogoTipo(Integer idCatalogoTipo) {
        IdCatalogoTipo = idCatalogoTipo;
    }

    public Integer getIdCatalogoSexo() {
        return IdCatalogoSexo;
    }

    public void setIdCatalogoSexo(Integer idCatalogoSexo) {
        IdCatalogoSexo = idCatalogoSexo;
    }

    public Integer getIdCatalogoEstado() {
        return IdCatalogoEstado;
    }

    public void setIdCatalogoEstado(Integer idCatalogoEstado) {
        IdCatalogoEstado = idCatalogoEstado;
    }

    public Integer getIdCatalogoIngestaNativa() {
        return IdCatalogoIngestaNativa;
    }

    public void setIdCatalogoIngestaNativa(Integer idCatalogoIngestaNativa) {
        IdCatalogoIngestaNativa = idCatalogoIngestaNativa;
    }

    public Integer getIdCatalogoGenoAllimento() {
        return IdCatalogoGenoAllimento;
    }

    public void setIdCatalogoGenoAllimento(Integer idCatalogoGenoAllimento) {
        IdCatalogoGenoAllimento = idCatalogoGenoAllimento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    public AKHormigaDTO() {
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IdHormiga                 : " + getIdHormiga()
                + "\n IdCatalogoTipo            : " + getIdCatalogoTipo()
                + "\n IdCatalogoSexo            : " + getIdCatalogoSexo()
                + "\n IdCatalogoEstado          : " + getIdCatalogoEstado()
                + "\n IdCatalogoIngestaNativa   : " + getIdCatalogoIngestaNativa()
                + "\n IdCatalogoGenoAllimento    : " + getIdCatalogoGenoAllimento()
                + "\n Nombre                    : " + getNombre()
                + "\n Estado                    : " + getEstado()
                + "\n FechaCrea                 : " + getFechaCrea()
                + "\n FechaModifica             : " + getFechaModifica();

    }

}
