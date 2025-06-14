package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import java.util.*;


public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository repositoryLibro;
    private final AutorRepository repositoryAutor;
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor) {
        this.repositoryLibro = repositoryLibro;
        this.repositoryAutor = repositoryAutor;
    }

    public void muestraElMenu() {
        int opcion;
        do {
            System.out.println("""
                    *********************************
                    1 - Buscar libros por título
                    2 - Mostrar libros registrados
                    3 - Mostrar autores registrados
                    4 - Autores vivos en determinado año
                    5 - Buscar libros por idioma
                    6 - Top 10 libros más descargados
                    7 - Libro más y menos descargado
                    0 - Salir
                    *********************************
                    """);

            while (!teclado.hasNextInt()) {
                System.out.println("Formato inválido, ingrese un número válido.");
                teclado.nextLine();
            }

            opcion = teclado.nextInt();
            teclado.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> buscarLibro();
                case 2 -> repositoryLibro.findAll().forEach(System.out::println);
                case 3 -> repositoryAutor.findAll().forEach(System.out::println);
                case 4 -> autoresVivosPorAnio();
                case 5 -> buscarLibroPorIdioma();
                case 6 -> repositoryLibro.top10LibrosMasDescargados().forEach(System.out::println);
                case 7 -> rankingLibro();
                case 0 -> System.out.println("Finalizando la aplicación...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private DatosBusqueda obtenerBusqueda() {
        System.out.print("Escribe el nombre del libro: ");
        String nombre = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + nombre.replace(" ", "%20"));
        return conversor.obtenerDatos(json, DatosBusqueda.class);
    }

    private void buscarLibro() {
        DatosBusqueda datos = obtenerBusqueda();
        if (datos == null || datos.resultado().isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        DatosLibro datosLibro = datos.resultado().get(0);
        Libro libro = new Libro(datosLibro);

        if (repositoryLibro.findByTitulo(libro.getTitulo()).isPresent()) {
            System.out.println("\nEl libro ya está registrado\n");
            return;
        }

        if (!datosLibro.autor().isEmpty()) {
            Autor autor = new Autor(datosLibro.autor().get(0));
            Autor autorFinal = repositoryAutor.findByNombre(autor.getNombre()).orElseGet(() -> repositoryAutor.save(autor));
            libro.setAutor(autorFinal);
            repositoryLibro.save(libro);

            System.out.printf("""
                    ********** Libro **********
                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Número de Descargas: %d
                    ***************************
                    """, libro.getTitulo(), autorFinal.getNombre(), libro.getLenguaje(),
                    Optional.ofNullable(libro.getNumero_descargas()).orElse(0));
        } else {
            System.out.println("Sin autor.");
        }
    }

    private void autoresVivosPorAnio() {
        System.out.print("Ingresa el año: ");

        while (!teclado.hasNextInt()) {
            System.out.println("Por favor, ingresa un año válido (número entero).");
            teclado.nextLine();
        }

        int anio = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = repositoryAutor.listaAutoresVivosPorAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(System.out::println);
        }
    }

    private void buscarLibroPorIdioma() {
        int opcion;
        do {
            System.out.println("""
                    1. en - Inglés
                    2. es - Español
                    3. fr - Francés
                    4. pt - Portugués
                    0. Volver al menú
                    """);

            while (!teclado.hasNextInt()) {
                System.out.println("Formato inválido, ingrese un número válido.");
                teclado.nextLine();
            }

            opcion = teclado.nextInt();
            teclado.nextLine();

            String idioma = switch (opcion) {
                case 1 -> "[en]";
                case 2 -> "[es]";
                case 3 -> "[fr]";
                case 4 -> "[pt]";
                case 0 -> null;
                default -> {
                    System.out.println("Idioma no válido.");
                    yield null;
                }
            };

            if (idioma != null) {
                repositoryLibro.findByLenguaje(Idioma.fromString(idioma))
                        .forEach(System.out::println);
            }
        } while (opcion != 0);
    }

    private void rankingLibro() {
        List<Libro> libros = repositoryLibro.findAll().stream()
                .filter(l -> l.getNumero_descargas() != null && l.getNumero_descargas() > 0)
                .toList();

        if (libros.isEmpty()) {
            System.out.println("No hay libros con descargas registradas.");
            return;
        }

        Comparator<Libro> comparador = Comparator.comparingInt(Libro::getNumero_descargas);
        Libro menosDescargado = Collections.min(libros, comparador);
        Libro masDescargado = Collections.max(libros, comparador);

        System.out.printf("""
                ******************************************************
                Libro más descargado: %s
                Número de descargas: %d

                Libro menos descargado: %s
                Número de descargas: %d
                ******************************************************
                """, masDescargado.getTitulo(), masDescargado.getNumero_descargas(),
                menosDescargado.getTitulo(), menosDescargado.getNumero_descargas());
    }
}
