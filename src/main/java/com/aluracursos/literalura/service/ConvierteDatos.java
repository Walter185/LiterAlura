package com.aluracursos.literalura.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ConvierteDatos {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (IOException e) {
            System.out.println("Error al convertir datos: " + e.getMessage());
            return null;
        }
    }
}

