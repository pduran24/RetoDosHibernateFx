package org.example.retodos.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.example.retodos.models.Copia;

import java.util.List;

/**
 * Repositorio específico para gestionar las Copias.
 * Implementa consultas personalizadas usando JPQL.
 */
public class CopiaRepository extends JpaRepository<Copia> {

    public CopiaRepository() {
        super(Copia.class);
    }

    /**
     * Busca todas las copias asociadas a un usuario específico.
     * Utiliza JOIN FETCH para cargar la película asociada en la misma consulta y evitar LazyInitializationException.
     * * @param idUsuario El ID del usuario del que queremos las copias.
     * @return Lista de copias del usuario.
     */
    public List<Copia> findByUsuarioId(Integer idUsuario) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT c FROM Copia c JOIN FETCH c.pelicula WHERE c.usuario.id = :idUsuario";

            TypedQuery<Copia> query = em.createQuery(jpql, Copia.class);
            query.setParameter("idUsuario", idUsuario);

            return query.getResultList();
        } finally {
            em.close();
        }
    }
}