package org.example.retodos.repository;

import org.example.retodos.models.Usuario;
import org.example.retodos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UsuarioRepository extends HibernateRepository<Usuario> {
    public UsuarioRepository() {
        super(Usuario.class);
    }

    public Usuario findByUsuarioContrasenia (String usuario, String contrasenia) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String consulta = "FROM Usuario WHERE nombreUsuario = :usuario AND contrasenia = :contrasenia";
            Query<Usuario> query = session.createQuery(
                    consulta, Usuario.class
            );
            return query.uniqueResult();
        }
    }
}
