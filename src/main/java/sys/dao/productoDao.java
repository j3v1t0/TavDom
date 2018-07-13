/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import sys.model.Producto;

/**
 *
 * @author root
 */
public interface productoDao {

    public List<Producto> listarProducto();

    public boolean newProducto(Producto producto);

    public boolean updateProducto(Producto producto);

    public boolean deleteProducto(Producto producto);
    
    //metodo utilizado en la factura facturaBean
    public Producto obtenerProductoPorCodBarra(String codBarra) throws Exception;
}
