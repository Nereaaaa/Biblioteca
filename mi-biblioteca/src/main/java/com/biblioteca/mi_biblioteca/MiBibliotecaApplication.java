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
					.nombre("Andrea Longarela")
					.edad(39)
					.nacionalidad("Española")
					.build());
			autoresRepositorio.save(Autor.builder()
					.nombre("María Martinez")
					.edad(36)
					.nacionalidad("Española")
					.build());
			autoresRepositorio.save(Autor.builder()
					.nombre("Alice Kellen")
					.edad(36)
					.nacionalidad("Española")
					.build());
			
			libroRepositorio.save(Libro.builder()
					.titulo("Te espero en el fin del mundo")
					.autor(autoresRepositorio.findByNombre("Andrea Longarela"))
					.genero(LibroGenre.ROMANCE_ADULTO)
					.estado(LibroStatus.LEIDO)
					.comprado(true)
					.nota(9)
					.fechaInicio(null)
					.fechaFin(null)
					.build());
			libroRepositorio.save(Libro.builder()
					.titulo("Nosotros en la luna")
					.autor(autoresRepositorio.findByNombre("Alice Kellen"))
					.genero(LibroGenre.ROMANCE_ADULTO)
					.estado(LibroStatus.LEIDO)
					.comprado(true)
					.nota(6)
					.fechaInicio(null)
					.fechaFin(null)
					.build());
			libroRepositorio.save(Libro.builder()
					.titulo("La fragilidad de un corazón bajo la lluvia")
					.autor(autoresRepositorio.findByNombre("María Martinez"))
					.genero(LibroGenre.ROMANCE_JOVEN)
					.estado(LibroStatus.LEIDO)
					.comprado(true)
					.nota(7)
					.fechaInicio(null)
					.fechaFin(null)
					.build());
		};
	}
}
