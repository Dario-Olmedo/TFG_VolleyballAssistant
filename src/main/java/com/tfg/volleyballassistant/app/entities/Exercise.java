package com.tfg.volleyballassistant.app.entities;
import jakarta.persistence.*;

public class Exercise{

@Entity
@Table(name = "Ejercicios")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descripcion;

    @Column(name = "url_explicacion_visual")
    private String urlExplicacionVisual;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category category;

    // Getters y setters
    // ...
}

