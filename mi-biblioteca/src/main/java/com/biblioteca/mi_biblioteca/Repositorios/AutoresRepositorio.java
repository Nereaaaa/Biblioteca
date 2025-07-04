package com.biblioteca.mi_biblioteca.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.mi_biblioteca.Entidades.Autor;

@Repository
public interface AutoresRepositorio extends JpaRepository<Autor, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, para buscar autores por nombre o apellido
    Autor findByNombre(String nombre);
    
}
