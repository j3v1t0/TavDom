/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.dao;

import org.hibernate.Session;
import sys.model.Factura;

/**
 *
 * @author root
 */
public interface facturaDao {
    //Obtener el ultimo registro en la tabla Factura
    public Factura obtenerUltimoRegistro() throws Exception;
    //Averiguar si la tabla tiene registros
    public Long obtenerTotalRegistrosEnFactura();
}
