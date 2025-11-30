package org.example.retodos.util;

import org.example.retodos.models.Usuario;
import org.example.retodos.repository.UsuarioRepository;

import java.util.Optional;

public class Auth {

    UsuarioRepository usuarioRepository;

    public Auth(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> validarUsuario (String usuario, String contrasenia) {
        return Optional.ofNullable(usuarioRepository.findByUsuarioContrasenia(usuario, contrasenia));
    }

}
