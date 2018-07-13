/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import sys.model.Vendedor;

/**
 *
 * @author root
 */
public interface vendedorDao {

    public List<Vendedor> listarVendedor();

    public boolean newVendedor(Vendedor vendedor);

    public boolean updateVendedor(Vendedor vendedor);

    public boolean deleteVendedor(Vendedor vendedor);

}
