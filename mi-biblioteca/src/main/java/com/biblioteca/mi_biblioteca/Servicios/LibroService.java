package com.biblioteca.mi_biblioteca.Servicios;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.mi_biblioteca.Entidades.*;
import com.biblioteca.mi_biblioteca.Enums.*;
import com.biblioteca.mi_biblioteca.Repositorios.*;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LibroService {
    
    @Autowired
    private AutoresRepositorio autoresRepositorio;

    @Autowired
    private LibroRepositorio librosRepositorio;
    
    public Libro guardarLibro(String titulo, String autorNombre, LibroGenre genero, LibroStatus estado, boolean comprado, int nota, LocalDate fechaInicio, LocalDate fechaFin) {

        Autor autor = autoresRepositorio.findByNombre(autorNombre);
        Libro libro = Libro.builder()
                .titulo(titulo)
                .autor(autor)
                .genero(genero)
                .estado(estado)
                .comprado(comprado)
                .nota(nota)
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .build();
        
        return librosRepositorio.save(libro);
    }

    public Libro actualizarLibroPorStatus(Long id, LibroStatus nuevoEstado) {
        Optional<Libro> libro = librosRepositorio.findById(id);
        libro.ifPresent(l -> {
            l.setEstado(nuevoEstado);
            librosRepositorio.save(l);
        });
        return libro.orElse(null);
    }
}
