package com.biblioteca.mi_biblioteca;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.biblioteca.mi_biblioteca.Entidades.Autor;
import com.biblioteca.mi_biblioteca.Entidades.Libro;
import com.biblioteca.mi_biblioteca.Enums.*;
import com.biblioteca.mi_biblioteca.Repositorios.AutoresRepositorio;
import com.biblioteca.mi_biblioteca.Repositorios.LibroRepositorio;

@SpringBootApplication
public class MiBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiBibliotecaApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(LibroRepositorio libroRepositorio, AutoresRepositorio autoresRepositorio) {
		return args -> {
			// Aquí puedes agregar lógica de inicialización si es necesario
			autoresRepositorio.save(Autor.builder()
					.nombre("Antoine de Saint-Exupéry")
					.edad(44)
					.nacionalidad("Francesa")
					.build());
			autoresRepositorio.save(Autor.builder()
					.nombre("Gabriel García Márquez")
					.edad(87)
					.nacionalidad("Colombiana")
					.build());
			autoresRepositorio.save(Autor.builder()
					.nombre("George Orwell")
					.edad(46)
					.nacionalidad("Británica")
					.build());
			
			libroRepositorio.save(Libro.builder()
					.titulo("El Principito")
					.autor(autoresRepositorio.findByNombre("Antoine de Saint-Exupéry"))
					.genero(LibroGenre.FICCIÓN)
					.estado(LibroStatus.POR_LEER)
					.comprado(false)
					.nota(5)
					.fechaInicio(null)
					.fechaFin(null)
					.build());
			libroRepositorio.save(Libro.builder()
					.titulo("Cien años de soledad")
					.autor(autoresRepositorio.findByNombre("Gabriel García Márquez"))
					.genero(LibroGenre.FICCIÓN)
					.estado(LibroStatus.LEYENDO)
					.comprado(true)
					.nota(4)
					.fechaInicio(LocalDate.of(2023, 1, 1))
					.fechaFin(LocalDate.of(2023, 12, 31))
					.build());
			libroRepositorio.save(Libro.builder()
					.titulo("1984")
					.autor(autoresRepositorio.findByNombre("George Orwell"))
					.genero(LibroGenre.FICCIÓN)
					.estado(LibroStatus.POR_LEER)
					.comprado(false)
					.nota(5)
					.fechaInicio(null)
					.fechaFin(null)
					.build());
		};
	}
}
