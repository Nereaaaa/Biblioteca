package com.biblioteca.mi_biblioteca.Entidades;

import java.time.LocalDate;

import com.biblioteca.mi_biblioteca.Enums.LibroGenre;
import com.biblioteca.mi_biblioteca.Enums.LibroStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    
    @ManyToOne
    private Autor autor; 

    @Enumerated(EnumType.STRING)
    private LibroGenre genero;
    @Enumerated(EnumType.STRING) 
    private LibroStatus estado; 
    
    private boolean comprado;
    
    private int nota;
    
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
}
