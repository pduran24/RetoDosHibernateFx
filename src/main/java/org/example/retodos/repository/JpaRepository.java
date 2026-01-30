package org.example.retodos.repository;

import javax.persistence.EntityManager;
import org.example.retodos.util.JPAUtil;
import java.util.List;

/**
 * Implementación de repositorio genérico usando JPA y ObjectDB.
 * @param <T> El tipo de entidad.
 */
public class JpaRepository<T> implements Repository<T> {

    private final Class<T> type;

    public JpaRepository(Class<T> type) {
        this.type = type;
    }

    public EntityManager getEntityManager() {
        return JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        try {
            String q = "SELECT e FROM " + type.getSimpleName() + " e";
            return em.createQuery(q, type).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public T findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(type, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void save(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Nota: ObjectDB a veces requiere que el objeto esté "managed" para updates.
    @Override
    public void update(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            // En JPA, para borrar, la entidad debe estar "managed" (conectada)
            // Si viene desconectada de la tabla, hacemos merge primero
            T managedEntity = em.merge(entity);
            em.remove(managedEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}