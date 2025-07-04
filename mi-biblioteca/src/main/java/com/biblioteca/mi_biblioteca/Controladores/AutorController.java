package com.biblioteca.mi_biblioteca.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.mi_biblioteca.Entidades.Autor;
import com.biblioteca.mi_biblioteca.Entidades.Libro;
import com.biblioteca.mi_biblioteca.Repositorios.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin(origins = "*")
public class AutorController {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutoresRepositorio autoresRepositorio;

     @GetMapping("/autores")
    public List<Autor> obtenerAutores() {
        return autoresRepositorio.findAll();
    } 

    @GetMapping("/autores/{nombre}")
    public Autor obtenerAutorPorNombre(@PathVariable String nombre) {
        return autoresRepositorio.findByNombre(nombre);
    }
    
    @GetMapping("/autores/{nombreAutor}/libros")
    public List<Libro> obtenerLibrosPorNombreAutor(@PathVariable String nombreAutor) {
        return libroRepositorio.findByAutor(autoresRepositorio.findByNombre(nombreAutor));
    }
    
    @PostMapping("/autores")
    public Autor guardarAutor(@RequestParam String nombre, @RequestParam int edad, @RequestParam String nacionalidad) {
        Autor autor = Autor.builder()
                .nombre(nombre)
                .edad(edad)
                .nacionalidad(nacionalidad)
                .build();
        return autoresRepositorio.save(autor);
    }
    
}
