package org.example.retodos.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.example.retodos.models.Usuario;

import java.util.List;

public class UsuarioRepository extends JpaRepository<Usuario> {
    public UsuarioRepository() {
        super(Usuario.class);
    }

    public Usuario findByUsuarioContrasenia(String usuario, String contrasenia) {
        EntityManager em = getEntityManager();
        try {
            // ObjectDB usa JPQL estándar
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombreUsuario = :user AND u.contrasenia = :pass",
                    Usuario.class
            );
            query.setParameter("user", usuario);
            query.setParameter("pass", contrasenia);

            // getSingleResult lanza excepción si no encuentra nada, mejor usar getResultList
            List<Usuario> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }
}
