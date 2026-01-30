package org.example.retodos.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.example.retodos.models.Pelicula;
import org.example.retodos.models.Rol;
import org.example.retodos.models.Usuario;

public class DataSeeder {

    public static void seed() {
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        try {
            long count = em.createQuery("SELECT COUNT(u) FROM Usuario u", Long.class).getSingleResult();

            if (count == 0) {
                System.out.println("--- BASE DE DATOS VACÍA DETECTADA: CREANDO DATOS DE PRUEBA ---");

                em.getTransaction().begin();

                // 1. Crear Usuario Admin
                Usuario admin = new Usuario();
                admin.setNombreUsuario("admin");
                admin.setContrasenia("admin");
                admin.setRol(Rol.ADMIN);
                em.persist(admin);

                // 2. Crear Usuario Normal
                Usuario user = new Usuario();
                user.setNombreUsuario("pablo");
                user.setContrasenia("1234");
                user.setRol(Rol.USER);
                em.persist(user);

                // 3. Crear una Película de ejemplo
                Pelicula peli = new Pelicula();
                peli.setTitulo("Origen");
                peli.setDirector("Christopher Nolan");
                peli.setGenero("Ciencia Ficción");
                peli.setAnio(2010);
                peli.setDescripcion("Un ladrón que roba secretos corporativos a través del uso de la tecnología de compartir sueños...");
                em.persist(peli);

                em.getTransaction().commit();
                System.out.println("--- DATOS DE PRUEBA INSERTADOS CORRECTAMENTE ---");
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}