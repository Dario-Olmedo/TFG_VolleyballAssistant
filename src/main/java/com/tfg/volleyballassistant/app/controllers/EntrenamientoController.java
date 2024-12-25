package com.tfg.volleyballassistant.app.controllers;

import com.tfg.volleyballassistant.app.model.Categoria;
import com.tfg.volleyballassistant.app.model.Entrenamiento;
import com.tfg.volleyballassistant.app.repository.EntrenamientoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenamientos")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    @GetMapping
    public String listarEntrenamientos(Model model) {
        model.addAttribute("entrenamientos", entrenamientoRepository.findAll());
        return "entrenamientos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("entrenamiento", new Entrenamiento());
        model.addAttribute("categorias", Categoria.values());
        return "entrenamientos/formulario";
    }

    @PostMapping
    public String guardarEntrenamiento(@Valid @ModelAttribute Entrenamiento entrenamiento,
                                       RedirectAttributes attributes) {
        entrenamientoRepository.save(entrenamiento);
        attributes.addFlashAttribute("mensaje", "Entrenamiento guardado exitosamente.");
        return "redirect:/entrenamientos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("entrenamiento", entrenamiento);
        model.addAttribute("categorias", Categoria.values());
        return "entrenamientos/formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarEntrenamiento(@PathVariable Long id,
                                          @Valid @ModelAttribute Entrenamiento entrenamiento,
                                          RedirectAttributes attributes) {
        entrenamiento.setId(id);
        entrenamientoRepository.save(entrenamiento);
        attributes.addFlashAttribute("mensaje", "Entrenamiento actualizado exitosamente.");
        return "redirect:/entrenamientos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenamiento(@PathVariable Long id, RedirectAttributes attributes) {
        entrenamientoRepository.deleteById(id);
        attributes.addFlashAttribute("mensaje", "Entrenamiento eliminado exitosamente.");
        return "redirect:/entrenamientos";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String manejarErrores(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // Vista para mostrar errores de forma amigable
    }
}