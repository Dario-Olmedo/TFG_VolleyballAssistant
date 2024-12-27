package com.tfg.volleyballassistant.app.controllers;

import com.tfg.volleyballassistant.app.model.Entrenamiento;
import com.tfg.volleyballassistant.app.repository.EntrenamientoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/entrenamientos")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    // Lista de entrenamientos por categoría
    @GetMapping("/{categoria}")
    public String listarEntrenamientosPorCategoria(@PathVariable String categoria, Model model) {
        Entrenamiento.Categoria categoriaEnum = Entrenamiento.Categoria.valueOf(categoria.toUpperCase());
        List<Entrenamiento> entrenamientos = entrenamientoRepository.findByCategoria(categoriaEnum);
        model.addAttribute("entrenamientos", entrenamientos);
        model.addAttribute("categoria", categoriaEnum.name());
        return "entrenamientos/categoria";
    }

    // Formulario para nuevo entrenamiento
    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("entrenamiento", new Entrenamiento());
        model.addAttribute("categorias", Entrenamiento.Categoria.values());
        return "entrenamientos/formulario";
    }

    // Guardar entrenamiento
    @PostMapping
    public String guardarEntrenamiento(@Valid @ModelAttribute Entrenamiento entrenamiento,
                                       RedirectAttributes attributes) {
        entrenamientoRepository.save(entrenamiento);
        attributes.addFlashAttribute("mensaje", "Entrenamiento guardado exitosamente.");
        return "redirect:/entrenamientos/" + entrenamiento.getCategoria().name().toLowerCase();
    }

    // Ver detalle del entrenamiento
    @GetMapping("/ver/{id}")
    public String verEntrenamiento(@PathVariable Long id, Model model) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("entrenamiento", entrenamiento);
        return "entrenamientos/detalle";
    }

    // Eliminar entrenamiento desde el detalle
    @PostMapping("/eliminar/{id}")
    public String eliminarEntrenamientoDesdeDetalle(@PathVariable Long id, RedirectAttributes attributes) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        entrenamientoRepository.deleteById(id);
        attributes.addFlashAttribute("mensaje", "Entrenamiento eliminado exitosamente.");
        return "redirect:/entrenamientos/" + entrenamiento.getCategoria().name().toLowerCase();
    }

    // Manejo de errores
    @ExceptionHandler(IllegalArgumentException.class)
    public String manejarErrores(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}