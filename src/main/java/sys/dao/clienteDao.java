/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import java.util.List;
import org.hibernate.Session;
import sys.model.Cliente;

/**
 *
 * @author root
 */
public interface clienteDao {

    public List<Cliente> listarClientes();

    public boolean newCliente(Cliente cliente);

    public boolean updateCliente(Cliente cliente);

    public boolean deleteCliente(Cliente cliente);

    //este metodo se utilizara en la factura, facturaBean
    
    public Cliente obtenerClientePorCodigo(int codCliente) throws Exception;
    
}
