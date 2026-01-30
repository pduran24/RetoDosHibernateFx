package org.example.retodos.repository;

import org.example.retodos.models.Pelicula;

public class PeliculaRepository extends JpaRepository<Pelicula> {
    public PeliculaRepository() {
        super(Pelicula.class);
    }
}
