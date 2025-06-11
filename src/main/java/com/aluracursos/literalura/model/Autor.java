package com.aluracursos.literalura.model;

import jakarta.persistence.*;

public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;

    public Autor(){}
}
