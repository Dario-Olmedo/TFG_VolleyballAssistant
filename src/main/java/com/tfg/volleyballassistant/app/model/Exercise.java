package com.tfg.volleyballassistant.app.model;

import jakarta.persistence.*;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descripcion;

    @Column(name = "url_explicacion_visual")
    private String urlExplicacionVisual;

    @Enumerated(EnumType.STRING) // Usar el enum Categoria
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

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

    public String getUrlExplicacionVisual() {
        return urlExplicacionVisual;
    }

    public void setUrlExplicacionVisual(String urlExplicacionVisual) {
        this.urlExplicacionVisual = urlExplicacionVisual;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
