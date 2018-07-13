/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author root
 */
public class Cliente {

    private int codCliente;
    private String nombres;
    private String apellidos;
    private String direccion;
//    private Set<Factura> facturas = new HashSet<Factura>(0);

    public Cliente() {
    }

    public Cliente(int codCliente, String nombres, String apellidos, String direccion) {
        this.codCliente = codCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
