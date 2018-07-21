/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import sys.dao.facturaDao;
import sys.model.Factura;
import sys.util.HibernateUtil;

/**
 *
 * @author root
 */
public class facturaDaoImp implements facturaDao {

    private Session sesion;
    private Transaction tx;

    public void iniciarOperacion() throws HibernateException {

        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    public void manejaException(HibernateException he) throws HibernateException {

        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos. ", he);
    }

    @Override
    public Factura obtenerUltimoRegistro() throws Exception {
        this.iniciarOperacion();
        List<Factura> busqueda = sesion
                .createCriteria(Factura.class)
                .list();

        return (Factura) busqueda;
    }

    @Override
    public Long obtenerTotalRegistrosEnFactura() {
        this.iniciarOperacion();
        Long maxid = (Long) sesion.createCriteria(Factura.class)
                .setProjection(Projections.count("codFactura")).uniqueResult();

        return maxid;
    }
}
