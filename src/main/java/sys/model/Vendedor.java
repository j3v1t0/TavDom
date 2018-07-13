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
public class Vendedor {
    
    private int codVendedor;
    private String nombres;
    private String apellidos;
    private String dui;
    private String celular;
    private String direccion;

    public Vendedor() {
    }

    public Vendedor(int codVendedor, String nombres, String apellidos, String dui, String celular, String direccion) {
        this.codVendedor = codVendedor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dui = dui;
        this.celular = celular;
        this.direccion = direccion;
    }

    public int getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }    
    
}
