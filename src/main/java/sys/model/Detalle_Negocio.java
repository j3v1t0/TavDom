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
public class Detalle_Negocio {
    
    private Integer codDetalle_Negocio;
    private Cliente cliente;
    private Tipo_Negocio tipo_negocio;
    private String negocio;
    private String rnc;
    private String direccion;
    private Integer credito;
    private char estatus_negocio;

    public Detalle_Negocio() {
    }

    public Detalle_Negocio(Integer codDetalle_Negocio, Cliente cliente, Tipo_Negocio tipo_negocio, String negocio, String rnc, String direccion, Integer credito, char estatus_negocio) {
        this.codDetalle_Negocio = codDetalle_Negocio;
        this.cliente = cliente;
        this.tipo_negocio = tipo_negocio;
        this.negocio = negocio;
        this.rnc = rnc;
        this.direccion = direccion;
        this.credito = credito;
        this.estatus_negocio = estatus_negocio;
    }

    public Integer getCodDetalle_Negocio() {
        return codDetalle_Negocio;
    }

    public void setCodDetalle_Negocio(Integer codDetalle_Negocio) {
        this.codDetalle_Negocio = codDetalle_Negocio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tipo_Negocio getTipo_negocio() {
        return tipo_negocio;
    }

    public void setTipo_negocio(Tipo_Negocio tipo_negocio) {
        this.tipo_negocio = tipo_negocio;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getCredito() {
        return credito;
    }

    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    public char getEstatus_negocio() {
        return estatus_negocio;
    }

    public void setEstatus_negocio(char estatus_negocio) {
        this.estatus_negocio = estatus_negocio;
    }

    
}
