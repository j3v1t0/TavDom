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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import sys.dao.clienteDao;
import sys.dao.facturaDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.facturaDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.DetalleFactura;
import sys.model.Factura;
import sys.model.Producto;
import sys.util.HibernateUtil;

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
    private Long numeroFactura;
    
    private Session session;
    private Transaction transaction;

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

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
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
            if (!(this.cantidadProducto.matches("[0-9]*")) || this.cantidadProducto.equals("0") || this.cantidadProducto.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
                this.cantidadProducto = "";
            } else {

                productoDao pDao = new productoDaoImp();
                this.producto = pDao.obtenerProductoPorCodBarra(this.productoSeleccionado);

                //Aqui agregamos los productos a lista de la tabla producto
                this.listaDetalleFactura.add(new DetalleFactura(null, this.producto.getCodProducto(), this.producto.getCodBarra(),
                        this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto), this.producto.getPrecioVenta(),
                        BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto) * this.producto.getPrecioVenta().floatValue())));

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));

                //llamada al metodo calcular totalFacturaVenta
                this.totalFacturaVenta();;

                this.cantidadProducto = "";
            }
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
        if (!(this.cantidadProducto2.matches("[0-9]*")) || this.cantidadProducto2.equals("0") || this.cantidadProducto2.equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La cantidad es incorrecta"));
            this.cantidadProducto2 = "";
        } else {
            this.listaDetalleFactura.add(new DetalleFactura(null, this.producto.getCodProducto(), this.producto.getCodBarra(),
                    this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto2), this.producto.getPrecioVenta(),
                    BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto2) * this.producto.getPrecioVenta().floatValue())));

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto agregado"));
            this.cantidadProducto2 = "";

            //Llamada al metodo calcular totalFactura
            this.totalFacturaVenta();
        }

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
        }
    }

    //Metodo para quitar un producto de la factura
    public void quitarProductoDetalleFactura(String codBarra, Integer filaSeleccionada) {
        try {
            int i = 0;
            for (DetalleFactura item : this.listaDetalleFactura) {
                if (item.getCodBarra().equals(codBarra) && filaSeleccionada.equals(i)) {
                    this.listaDetalleFactura.remove(i);
                    break;
                }
                i++;
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Informacion", "Se retiro el producto de la factura"));
            //Recalcular el total de la factura, para actualizar el total
            this.totalFacturaVenta();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
        }
    }

    //Metodos para editar la cantidad del producto en la tabla productosFactura
    public void onRowEdit(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se actualizo la cantidad"));
        //Recalcular el total de la factura, para actualizar el total
        this.totalFacturaVenta();
    }

    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "No se actualizo la cantidad"));
    }

    //Metodo para generar el numero de factura
    public void numeracionFactura() {

        this.session = null;
        this.transaction = null;
        
        try {
            
            //this.session = HibernateUtil.getSessionFactory().openSession();
            //this.transaction = this.session.beginTransaction();
            facturaDao fDao = new facturaDaoImp();

            //verifica si hay registros en la tabla factura de la base de datos
            this.numeroFactura = fDao.obtenerTotalRegistrosEnFactura();
            
            if (this.numeroFactura <=0 || this.numeroFactura==null){
                this.numeroFactura = Long.valueOf("1");
            }else {
                //Recuperamos el ultimo registro que exista en la tabla factura de la base de datos
                this.factura = fDao.obtenerUltimoRegistro();
                this.numeroFactura = Long.valueOf(this.factura.getNumeroFactura()+1);
            }
            //this.transaction.commit();
            
        }catch(Exception e){
            if (this.transaction != null){
                this.transaction.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", e.getMessage()));
        }
    }
    
}
