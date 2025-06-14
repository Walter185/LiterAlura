package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Idioma;
import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findByLenguaje(Idioma lenguaje);

    @Query("SELECT l FROM Libro l ORDER BY l.numero_descargas DESC")
    List<Libro> top10LibrosMasDescargados();
}
