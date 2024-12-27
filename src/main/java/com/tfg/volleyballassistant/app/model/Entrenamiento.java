package com.tfg.volleyballassistant.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String titulo;

    @NotBlank
    @Column(length = 500)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Categoria categoria;

    @NotBlank
    @Pattern(regexp = "^(https?|ftp)://.*$", message = "Debe ser una URL válida")
    @Column(length = 500)
    private String explicacionVisual;

    // Constructor vacío (necesario para JPA)
    public Entrenamiento() {
    }

    // Constructor con argumentos
    public Entrenamiento(String titulo, String descripcion, Categoria categoria, String explicacionVisual) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.explicacionVisual = explicacionVisual;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getExplicacionVisual() {
        return explicacionVisual;
    }

    public void setExplicacionVisual(String explicacionVisual) {
        this.explicacionVisual = explicacionVisual;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                ", explicacionVisual='" + explicacionVisual + '\'' +
                '}';
    }

    public enum Categoria {
        TECNICA,
        FISICO,
        ESTRATEGIA
    }
}