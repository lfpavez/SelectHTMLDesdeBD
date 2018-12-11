
package modelo.dto;


public class ComunaDTO {
    
    private Integer codigo;
    private String NombreComuna;
    private Integer codRegion;

    public ComunaDTO(Integer codigo, String NombreCiudad, Integer codRegion) {
        this.codigo = codigo;
        this.NombreComuna = NombreCiudad;
        this.codRegion = codRegion;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreComuna() {
        return NombreComuna;
    }

    public void setNombreComuna(String NombreComuna) {
        this.NombreComuna = NombreComuna;
    }

    public Integer getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(Integer codRegion) {
        this.codRegion = codRegion;
    }

        
   
    
    
}
