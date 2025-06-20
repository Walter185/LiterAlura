package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNascimiento,
        @JsonAlias("death_year") Integer fechaFallecimiento){

}