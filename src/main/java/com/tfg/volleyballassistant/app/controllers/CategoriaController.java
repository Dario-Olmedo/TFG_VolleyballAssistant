package com.tfg.volleyballassistant.app.controllers;

import com.tfg.volleyballassistant.app.model.Categoria; // Import del enum
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriaController {

    @GetMapping
    public List<Categoria> getAllCategories() { // Retornar el enum Categoria
        return Arrays.asList(Categoria.values());
    }
}