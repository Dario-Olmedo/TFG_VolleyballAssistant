package com.tfg.volleyballassistant.app.controllers;

import com.tfg.volleyballassistant.app.model.Categoria;
import com.tfg.volleyballassistant.app.model.Entrenamiento;
import com.tfg.volleyballassistant.app.repository.EntrenamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String guardarEntrenamiento(@ModelAttribute Entrenamiento entrenamiento) {
        entrenamientoRepository.save(entrenamiento);
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
    public String actualizarEntrenamiento(@PathVariable Long id, @ModelAttribute Entrenamiento entrenamiento) {
        entrenamiento.setId(id);
        entrenamientoRepository.save(entrenamiento);
        return "redirect:/entrenamientos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEntrenamiento(@PathVariable Long id) {
        entrenamientoRepository.deleteById(id);
        return "redirect:/entrenamientos";
    }
}
