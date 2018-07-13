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
import org.hibernate.criterion.Restrictions;
import sys.dao.productoDao;
import sys.model.Producto;
import sys.util.HibernateUtil;

/**
 *
 * @author root
 */
public class productoDaoImp implements productoDao {

    Session sesion;
    Transaction tx;

    @Override
    public List<Producto> listarProducto() {
        List<Producto> lista = null;
        try {
            this.iniciarOperacion();
            lista = sesion.createCriteria(Producto.class).addOrder(Order.asc("codProducto"))
                    .list();
        } catch (HibernateException he) {
            throw he;
        } finally {
            if (sesion.isOpen()) {
                sesion.close();
            }
        }
        return lista;
    }

    @Override
    public boolean newProducto(Producto producto) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.save(producto);
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
    public boolean updateProducto(Producto producto) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.saveOrUpdate(producto);
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
    public boolean deleteProducto(Producto producto) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.delete(producto);
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

    @Override
    public Producto obtenerProductoPorCodBarra(String codBarra) throws Exception {
        Producto pro = null;
        try {
            iniciarOperacion();
            tx.setTimeout(2);
            pro = (Producto) sesion.createCriteria(Producto.class)
                    .add(Restrictions.eq("codBarra", codBarra))
                    .addOrder(Order.asc("codProducto")).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sesion.isOpen()) {
                sesion.close();
            }
        }
        return pro;
    }

}
