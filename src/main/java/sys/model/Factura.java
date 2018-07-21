/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author root
 */
public class Factura {
    
    private Integer codFactura;
    private Integer numeroFactura;
    private Integer codVendedor;
    private Integer codCliente;
    private BigDecimal totalVenta;
    private Date fechaRegistro;

    public Factura() {
    }

    public Factura(Integer codFactura, Integer numeroFactura, Integer codVendedor, Integer codCliente, BigDecimal totalVenta, Date fechaRegistro) {
        this.codFactura = codFactura;
        this.numeroFactura = numeroFactura;
        this.codVendedor = codVendedor;
        this.codCliente = codCliente;
        this.totalVenta = totalVenta;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(Integer codFactura) {
        this.codFactura = codFactura;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Integer getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(Integer codVendedor) {
        this.codVendedor = codVendedor;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
}
