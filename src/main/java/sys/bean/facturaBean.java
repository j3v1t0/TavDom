/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sys.dao.clienteDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.DetalleFactura;
import sys.model.Factura;
import sys.model.Producto;

@ManagedBean
@ViewScoped
public class facturaBean {

    private Cliente cliente;
    private Integer codigoCliente;
    private Producto producto;
    private String codBarra;
    private List<DetalleFactura> listaDetalleFactura;
    private String cantidadProducto;
    private String productoSeleccionado;
    private Factura factura;
    private String cantidadProducto2;

    public facturaBean() {
        this.factura = new Factura();
        this.listaDetalleFactura = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public List<DetalleFactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public void setListaDetalleFactura(List<DetalleFactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }

    public String getCantidadProducto2() {
        return cantidadProducto2;
    }

    public void setCantidadProducto2(String cantidadProducto2) {
        this.cantidadProducto2 = cantidadProducto2;
    }

    //Metodo para mostrar los datos de los clientes por medio del dialogClientes
    public void agregarDatosCliente(Integer codCliente) throws Exception {
        try {
            clienteDao cDao = new clienteDaoImp();
            this.cliente = cDao.obtenerClientePorCodigo(codCliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Metodo para mostrar los datos del cliente buscado por codCliente
    public void agregarDatosCliente2() {

        try {
            clienteDao cDao = new clienteDaoImp();
            this.cliente = cDao.obtenerClientePorCodigo(this.codigoCliente);

            if (this.cliente != null) {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del cliente agregado"));
            } else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos del cliente no encontrado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //Metodo para solicitar la cantidad de producto a vender
    public void pedirCantidadProducto(String codBarra) {
        this.productoSeleccionado = codBarra;
    }

    //Metodo para mostrar los datos del producto por medio del dialogProducto
    public void agregarDatosProducto() throws Exception {
        try {
            productoDao pDao = new productoDaoImp();
            this.producto = pDao.obtenerProductoPorCodBarra(this.productoSeleccionado);

            //Aqui agregamos los productos a lista de la tabla producto
            this.listaDetalleFactura.add(new DetalleFactura(null, this.producto.getCodProducto(), this.producto.getCodBarra(),
                    this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto), this.producto.getPrecioVenta(),
                    BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto) * this.producto.getPrecioVenta().floatValue())));

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));

            //llamada al metodo calcular totalFacturaVenta
            this.totalFacturaVenta();;

            this.cantidadProducto = null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Metodo para mostrar el dialogCantidadProducto2
    public void mostrarCantidadProducto2() {
        try {

            if (this.codBarra.equals("")) {
                return;
            }
            productoDao pDao = new productoDaoImp();
            this.producto = pDao.obtenerProductoPorCodBarra(this.codBarra);

            if (this.producto != null) {

                //Solicitar mostrar el dialog cantidadProducto2
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogCantidadProducto2').show();");

                this.codigoCliente = null;

                this.codBarra = null;
            } else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Producto no encontrado"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Metodo para mostrar los datos del producto buscado por codBarra
    public void agregarDatosProductos2() {

        this.listaDetalleFactura.add(new DetalleFactura(null, this.producto.getCodProducto(), this.producto.getCodBarra(),
                this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto2), this.producto.getPrecioVenta(),
                BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto2) * this.producto.getPrecioVenta().floatValue())));

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));
        this.cantidadProducto2 = null;
        
        //Llamada al metodo calcular totalFactura
        this.totalFacturaVenta();

    }

    // Metodo para calcular el total a vender en la factura
    public void totalFacturaVenta() {
        BigDecimal totalFacturaVenta = new BigDecimal(0);

        try {
            for (DetalleFactura item : this.listaDetalleFactura) {
                BigDecimal totalVentaPorProducto = item.getPrecioVenta().multiply(new BigDecimal(item.getCantidad()));
                item.setTotal(totalVentaPorProducto);
                totalFacturaVenta = totalFacturaVenta.add(totalVentaPorProducto);
            }

            this.factura.setTotalVenta(totalFacturaVenta);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
