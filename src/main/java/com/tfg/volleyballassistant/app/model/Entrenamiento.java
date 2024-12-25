package com.tfg.volleyballassistant.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    // Constructor vacío (necesario para JPA)
    public Entrenamiento() {
    }

    // Constructor con argumentos
    public Entrenamiento(String titulo, String descripcion, Categoria categoria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    // Constructor con ID
    public Entrenamiento(Long id, String titulo, String descripcion, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
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

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                '}';
    }

    public enum Categoria {
        TECNICA,
        FISICO,
        ESTRATEGIA
    }
}
