package org.example.retodos;

import org.example.retodos.models.Usuario;
import org.example.retodos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Logger;

public class Main {
    static void main() {

        Logger logger = Logger.getLogger(Main.class.getName());

        logger.info("Prueba de conexión a Hibernate");

        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            logger.info("Conexión exitosa a la base de datos");
            session.beginTransaction();


            Query<Usuario> query = session.createQuery("from Usuario", Usuario.class);
            List<Usuario> usuarios = query.list();

            usuarios.forEach(System.out::println);

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            logger.info("Cerrando Hibernate");
            session.close();
            HibernateUtil.shutdown();
        }
    }
}
