/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

/**
 *
 * @author luispavezleon
 */
public class RegionDTO {
    
   private Integer codigo;
   private String NombreRegion;

    public RegionDTO() {
    }

    public RegionDTO(Integer codigo, String NombreRegion) {
        this.codigo = codigo;
        this.NombreRegion = NombreRegion;
    }

        
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombreRegion() {
        return NombreRegion;
    }

    public void setNombreRegion(String NombreRegion) {
        this.NombreRegion = NombreRegion;
    }
    
    
    
}
