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
import sys.dao.clienteDao;
import sys.model.Cliente;
import sys.util.HibernateUtil;

/**
 *
 * @author root
 */
public class clienteDaoImp implements clienteDao {

    Session sesion;
    Transaction tx;

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = null;
        try {
            this.iniciarOperacion();
            lista = sesion.createCriteria(Cliente.class).addOrder(Order.asc("codCliente"))
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
    public boolean newCliente(Cliente cliente) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.save(cliente);
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
    public boolean updateCliente(Cliente cliente) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.saveOrUpdate(cliente);
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
    public boolean deleteCliente(Cliente cliente) {
        boolean resp = false;
        try {
            iniciarOperacion();
            //tr.setTimeout(2);
            sesion.delete(cliente);
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
    public Cliente obtenerClientePorCodigo(int codCliente) throws Exception {
        Cliente cl = null;
        try {
            iniciarOperacion();
            tx.setTimeout(2);
            cl = (Cliente) sesion.createCriteria(Cliente.class)
                    .add(Restrictions.eq("codCliente", codCliente))
                    .addOrder(Order.asc("codCliente")).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sesion.isOpen()) {
                sesion.close();
            }
        }
        return cl;
    }

}
