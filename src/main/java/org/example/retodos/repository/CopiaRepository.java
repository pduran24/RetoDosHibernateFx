package org.example.retodos.repository;

import org.example.retodos.models.Copia;
import org.example.retodos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CopiaRepository extends HibernateRepository<Copia> {
    public CopiaRepository() {
        super(Copia.class);
    }

    public List<Copia> findByUsuarioId (Integer idUsuario) {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             String consulta = "FROM Copia c JOIN FETCH c.pelicula WHERE c.usuario.id = :idUsuario";
             Query<Copia> query = session.createQuery(consulta, Copia.class);
             query.setParameter("idUsuario", idUsuario);
             return query.list();
         }
    }
}
