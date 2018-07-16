/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.model;

import java.math.BigDecimal;

/**
 *
 * @author root
 */
public class DetalleFactura {
    
    private Integer codDetalle;
    private Integer codFactura;
    private Integer codProducto;
    private String codBarra;
    private String nombreProducto;
    private Integer cantidad;
    private BigDecimal precioVenta;
    private BigDecimal total;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer codFactura, Integer codProducto, String codBarra, String nombreProducto, Integer cantidad, BigDecimal precioVenta, BigDecimal total) {
        this.codFactura = codFactura;
        this.codProducto = codProducto;
        this.codBarra = codBarra;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.total = total;
    }

    public Integer getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(Integer codDetalle) {
        this.codDetalle = codDetalle;
    }

    public Integer getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(Integer codFactura) {
        this.codFactura = codFactura;
    }

    public Integer getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Integer codProducto) {
        this.codProducto = codProducto;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }    
    
}
