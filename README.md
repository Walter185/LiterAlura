# LiterAlura 📚 - Challenge Alura

Este proyecto forma parte del **Challenge Alura Backend Java**, donde construiremos una aplicación Java llamada **LiterAlura** utilizando el framework **Spring Boot**.

---

## 🚀 Fase 1: Configuración del entorno de desarrollo

En esta primera fase, configuraremos el entorno necesario para iniciar el desarrollo del proyecto **LiterAlura**. A continuación se detallan los requisitos y pasos para crear la base del proyecto.

---

### ✅ Requisitos previos

Asegúrate de contar con los siguientes programas y versiones instaladas:

| Herramienta          | Versión mínima | Enlace de descarga                                |
|----------------------|----------------|---------------------------------------------------|
| Java JDK             | 21             | [Descargar JDK](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) |
| Maven                | 4 o superior   | [Descargar Maven](https://maven.apache.org/download.cgi) |
| Spring Boot          | 3.5.0          | [Spring Initializr](https://start.spring.io/)     |
| PostgreSQL           | 17  | [Descargar PostgreSQL](https://www.postgresql.org/download/) |
| IntelliJ IDEA (opcional) | Última versión | [Descargar IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/?section=windows) |

---

### ⚙️ Creación del proyecto con Spring Initializr

Utilizaremos [Spring Initializr](https://start.spring.io/) para generar la estructura base del proyecto.

#### Configuraciones principales:

- **Project:** Maven
- **Language:** Java
- **Spring Boot:** 3.5.0
- **Packaging:** JAR
- **Java:** 21

#### Dependencias necesarias:

- **Spring Data JPA**
- **PostgreSQL Driver**

---

## 🌐 API Gutendex

La API **Gutendex** es un catálogo de información de más de 70.000 libros presentes en **Project Gutenberg**, una biblioteca en línea y gratuita.

En esta etapa del desafío, es fundamental comprender cómo funciona esta API, revisar su documentación y aprender a realizar consultas.  
⚠️ *No es necesario obtener una clave de acceso*. Basta con hacer solicitudes conforme lo indica la documentación.

- 📘 **Enlace de la API:** [https://gutendex.com/](https://gutendex.com/)
- 🛠️ **Repositorio oficial (código fuente):** [https://github.com/garethbjohnson/gutendex](https://github.com/garethbjohnson/gutendex)

> *Dejamos el código oficial por si tienes curiosidad sobre cómo está construida la API.*

---

### 📁 Estructura inicial del proyecto

Una vez generado el proyecto con Spring Initializr y descomprimido el ZIP, podrás abrirlo en tu IDE preferido, como IntelliJ IDEA, y comenzar el desarrollo del backend del sistema LiterAlura.

---

### 📝 Notas

- Este repositorio irá evolucionando conforme avancemos en las próximas fases del challenge.
- La base de datos utilizada será PostgreSQL, por lo tanto se recomienda tenerla correctamente instalada y configurada para pruebas locales.

---

### 💻 Autor

Desarrollado por **Walter Liendo** como parte del programa de formación de **Alura ONE + Oracle**.

---
