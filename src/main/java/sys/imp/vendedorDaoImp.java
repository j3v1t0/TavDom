/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.imp;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import sys.dao.vendedorDao;
import sys.model.Vendedor;
import sys.util.HibernateUtil;

/**
 *
 * @author root
 */
public class vendedorDaoImp implements vendedorDao{

    Session sesion;
    Transaction tx;

    @Override
    public List<Vendedor> listarVendedor() {
        List<Vendedor> lista = null;
        try {
            this.iniciarOperacion();
            lista = sesion.createCriteria(Vendedor.class).addOrder(Order.asc("codVendedor"))
                    .list();
        } catch (HibernateException he) {
            throw he;
        } finally {
            if (sesion.isOpen()) {
                sesion.close();
            }
        }

        /*      Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Cliente";
        
        try {
            lista = session.createQuery(hql).list();
            t.commit();
            session.close();
        }catch(Exception e){
            t.rollback();
        }*/
        return lista;
    }

    @Override
    public boolean newVendedor(Vendedor vendedor) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.save(vendedor);
            tx.commit();
            resp = true;
        } catch (HibernateException he) {
            tx.rollback();
            manejaException(he);
            resp = false;
            throw he;
        } finally {
            if (sesion.isOpen()) {
                sesion.close();
            }
        }
        return resp;
    }

    @Override
    public boolean updateVendedor(Vendedor vendedor) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.saveOrUpdate(vendedor);
            tx.commit();
            resp = true;
        } catch (HibernateException he) {
            tx.rollback();
            manejaException(he);
            resp = false;
            throw he;
        } finally {
            if (sesion.isOpen()) {
                sesion.close();
            }
        }
        return resp;
    }

    @Override
    public boolean deleteVendedor(Vendedor vendedor) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.delete(vendedor);
            tx.commit();
            resp = true;
        } catch (HibernateException he) {
            tx.rollback();
            manejaException(he);
            resp = false;
            throw he;
        } finally {
            if (sesion.isOpen()) {
                sesion.close();
            }
        }
        return resp;
    }

    public void iniciarOperacion() throws HibernateException {

        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        tx.setTimeout(2);
    }

    public void manejaException(HibernateException he) throws HibernateException {

        tx.rollback();
        throw new HibernateException("Ocurrio un error en la capa de acceso a datos. ", he);
    }
}
