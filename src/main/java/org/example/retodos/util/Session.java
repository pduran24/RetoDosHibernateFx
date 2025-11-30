package org.example.retodos.util;

import org.example.retodos.models.Usuario;

public class Session {


    private static Session instance;
    private Usuario usuarioLogeado;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    public void cerrarSesion() {
        this.usuarioLogeado = null;
    }
}
