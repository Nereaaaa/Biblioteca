package com.biblioteca.mi_biblioteca.Controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteca.mi_biblioteca.Entidades.Libro;
import com.biblioteca.mi_biblioteca.Enums.*;
import com.biblioteca.mi_biblioteca.Repositorios.*;
import com.biblioteca.mi_biblioteca.Servicios.LibroService;

import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutoresRepositorio autoresRepositorio;

    @GetMapping("/libros")
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepositorio.findAll();
    }
    @GetMapping("/libros/id/{id}")
    public Optional<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroRepositorio.findById(id);
    }

    @GetMapping("/libros/titulo/{titulo}")
    public Optional<Libro> obtenerLibroPorTitulo(@PathVariable String titulo) {
        return libroRepositorio.findByTitulo(titulo);
    }

    @GetMapping("/libros/autor/{autorNombre}")
    public List<Libro> obtenerLibrosPorAutor(@PathVariable String autorNombre) {
        return libroRepositorio.findByAutor(autoresRepositorio.findByNombre(autorNombre));
    }

    @GetMapping("/librosPorGenero")
    public List<Libro> obtenerLibrosPorGenero(@RequestParam LibroGenre genero) {
        return libroRepositorio.findByGenero(genero);
    }

    @GetMapping("/librosPorStatus")
    public List<Libro> obtenerLibrosPorStatus(@RequestParam LibroStatus status) {
        return libroRepositorio.findByEstado(status);
    }

     @PutMapping("/libros/{id}/actualizarEstado")
    public Libro actualizarEstadoLibro(@PathVariable Long id, @RequestParam String nuevoEstado) {
        return libroService.actualizarLibroPorStatus(id, LibroStatus.valueOf(nuevoEstado.toUpperCase()));
    }
    
    @PostMapping("/libros")
    public Libro guardarLibro(@RequestParam String titulo, @RequestParam String autorNombre, 
                               @RequestParam LibroGenre genero, @RequestParam LibroStatus estado, 
                               @RequestParam boolean comprado, @RequestParam int nota, 
                               @RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin) {
        return libroService.guardarLibro(titulo, autorNombre, genero, estado, comprado, nota, 
                                          fechaInicio, fechaFin);
    }

}
