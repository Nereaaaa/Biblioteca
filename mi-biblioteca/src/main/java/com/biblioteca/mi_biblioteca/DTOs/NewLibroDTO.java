package com.biblioteca.mi_biblioteca.DTOs;

import java.time.LocalDate;

import com.biblioteca.mi_biblioteca.Enums.LibroGenre;
import com.biblioteca.mi_biblioteca.Enums.LibroStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewLibroDTO {
    private String titulo;
    private String autorNombre;
    private LibroGenre genero;
    private LibroStatus estado;
    private boolean comprado;
    private int nota;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
