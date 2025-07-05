package com.biblioteca.mi_biblioteca.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.mi_biblioteca.Entidades.Autor;
import com.biblioteca.mi_biblioteca.Entidades.Libro;
import com.biblioteca.mi_biblioteca.Enums.*;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar libros por título, autor, género, etc.
    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(Autor autor);
    List<Libro> findByGenero(LibroGenre genero);
    List<Libro> findByEstado(LibroStatus status);
}
