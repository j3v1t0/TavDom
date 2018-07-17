/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.model;

/**
 *
 * @author root
 */
public class tipo_negocio {
    private Integer codTipo_Negocio;
    private String clasificacion;
    private String tipo_negocio;

    public tipo_negocio() {
    }

    public tipo_negocio(Integer codTipo_Negocio, String clasificacion, String tipo_negocio) {
        this.codTipo_Negocio = codTipo_Negocio;
        this.clasificacion = clasificacion;
        this.tipo_negocio = tipo_negocio;
    }

    public Integer getCodTipo_Negocio() {
        return codTipo_Negocio;
    }

    public void setCodTipo_Negocio(Integer codTipo_Negocio) {
        this.codTipo_Negocio = codTipo_Negocio;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTipo_negocio() {
        return tipo_negocio;
    }

    public void setTipo_negocio(String tipo_negocio) {
        this.tipo_negocio = tipo_negocio;
    }

    @Override
    public String toString() {
        return tipo_negocio;
    }
    
    
}
