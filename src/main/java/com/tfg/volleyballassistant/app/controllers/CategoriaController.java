package com.tfg.volleyballassistant.app.controllers;

import com.tfg.volleyballassistant.app.model.Categoria; // Import del enum
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriaController {

    // Obtener todas las categorías
    @GetMapping
    public List<Categoria> getAllCategories() { // Retornar el enum Categoria
        return Arrays.asList(Categoria.values());
    }

    // Retornar la ruta para una categoría específica
    @GetMapping("/{categoria}/url")
    public String getCategoryUrl(@PathVariable String categoria) {
        try {
            Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
            return "/entrenamientos/" + categoriaEnum.name().toLowerCase();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoría no válida: " + categoria);
        }
    }
}