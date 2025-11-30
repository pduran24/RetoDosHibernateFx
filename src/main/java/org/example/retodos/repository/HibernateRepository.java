package org.example.retodos.repository;

import org.example.retodos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateRepository<T> implements Repository<T> {

    private final Class<T> type;

    public HibernateRepository(Class<T> type) {
        this.type = type;
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + type.getName(), type).list();
        }
    }

    @Override
    public T findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(type, id);
        }
    }

    @Override
    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }
}
