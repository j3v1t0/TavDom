/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author root
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = builSessionFactory();

    private static SessionFactory builSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            return configuration.configure("/hibernate.cfg.xml").buildSessionFactory(
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
        } catch (Throwable ex) {
            System.err.println("El inicio de la sesion fallo." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
