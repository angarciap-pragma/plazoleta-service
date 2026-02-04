package com.poo.demo.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioServicio {
    private final List<Usuario> usuarios = new ArrayList<>();

    public void guardar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Optional<Usuario> buscarPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }
}
