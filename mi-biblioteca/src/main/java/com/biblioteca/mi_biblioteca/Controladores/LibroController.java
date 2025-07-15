package com.biblioteca.mi_biblioteca.Controladores;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import com.biblioteca.mi_biblioteca.Entidades.Libro;
import com.biblioteca.mi_biblioteca.Enums.*;
import com.biblioteca.mi_biblioteca.Repositorios.*;
import com.biblioteca.mi_biblioteca.Servicios.LibroService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

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

    @PostMapping("/libros/{id}/portada")
    public ResponseEntity<String> subirPortada(@PathVariable Long id, @RequestParam("imagen") MultipartFile imagen)
            throws IOException {
        if (imagen == null || imagen.isEmpty()) {
            return ResponseEntity.badRequest().body("La imagen está vacía o no se subió correctamente");
        }

        Libro libro = libroRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.setPortada(imagen.getBytes());
        libroRepositorio.save(libro);

        return ResponseEntity.ok("Imagen guardada correctamente");
    }

    @GetMapping("/libros/{id}/portada")
    public ResponseEntity<Resource> obtenerPortada(@PathVariable Long id) {
        Libro libro = libroRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        byte[] datosImagen = libro.getPortada();
        if (datosImagen == null || datosImagen.length == 0) {
            return ResponseEntity.notFound().build();
        }

        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM; // Valor por defecto

        try {
            String mimeType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(datosImagen));
            if (mimeType != null) {
                mediaType = MediaType.parseMediaType(mimeType);
            }
        } catch (IOException e) {
            System.err.println("Error detectando el tipo MIME: " + e.getMessage());
        }

        Resource recurso = new ByteArrayResource(datosImagen);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(recurso);
    }

    @PutMapping("/libros/id/{id}")
    public ResponseEntity<Libro> actualizarLibro(
            @PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String autorNombre,
            @RequestParam LibroGenre genero,
            @RequestParam LibroStatus estado,
            @RequestParam boolean comprado,
            @RequestParam int nota,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        Libro actualizado = libroService.actualizarLibro(
                id, titulo, autorNombre, genero, estado, comprado, nota, fechaInicio, fechaFin);
        return ResponseEntity.ok(actualizado);
    }
}
