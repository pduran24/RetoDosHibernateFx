package org.example.retodos.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase de utilidad para gestionar la factoría de entidades de JPA.
 * Sustituye al antiguo HibernateUtil.
 */
public class JPAUtil {

    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("PeliculasPU");
        } catch (Throwable ex) {
            System.err.println("Error al iniciar JPA: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}