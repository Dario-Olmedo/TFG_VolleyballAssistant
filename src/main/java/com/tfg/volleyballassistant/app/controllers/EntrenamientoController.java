package com.tfg.volleyballassistant.app.controllers;

import com.tfg.volleyballassistant.app.model.Entrenamiento;
import com.tfg.volleyballassistant.app.repository.EntrenamientoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/entrenamientos")
public class EntrenamientoController {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    // Lista de entrenamientos por categoría con paginación
    @GetMapping("/{categoria}")
    public String listarEntrenamientosPorCategoria(
            @PathVariable String categoria,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size,
            Model model) {
        Entrenamiento.Categoria categoriaEnum = Entrenamiento.Categoria.valueOf(categoria.toUpperCase());
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);
        Page<Entrenamiento> entrenamientosPage = entrenamientoRepository.findByCategoria(categoriaEnum, PageRequest.of(currentPage, pageSize));

        model.addAttribute("entrenamientos", entrenamientosPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", entrenamientosPage.getTotalPages());
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
        String videoUrl = entrenamiento.getExplicacionVisual();
        String embedUrl = videoUrl.replace("watch?v=", "embed/");
        model.addAttribute("embedUrl", embedUrl);
        return "entrenamientos/detalle";
    }

    // Editar detalle del entrenamiento
    @GetMapping("/editar/{id}")
    public String verFormularioEntrenamiento(@PathVariable Long id, Model model) {
        Entrenamiento entrenamiento = entrenamientoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("entrenamiento", entrenamiento);
        model.addAttribute("categorias", Entrenamiento.Categoria.values());
        return "entrenamientos/formulario";
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

    // Buscar entrenamientos
    @GetMapping("/{categoria}/buscar")
    public String buscarEntrenamientos(
            @PathVariable String categoria,
            @RequestParam("query") String query,
            Model model) {
        // Valida si la categoría es válida
        Entrenamiento.Categoria categoriaEnum = Entrenamiento.Categoria.valueOf(categoria.toUpperCase());

        // Realiza la búsqueda por título o descripción
        List<Entrenamiento> resultados = entrenamientoRepository.findByCategoriaAndTituloContainingOrDescripcionContaining(
                categoriaEnum, query, query);

        // Agrega los resultados al modelo
        model.addAttribute("entrenamientos", resultados);
        model.addAttribute("categoria", categoriaEnum.name());
        model.addAttribute("query", query); // Mantener la búsqueda en la barra

        return "entrenamientos/categoria"; // Renderiza la vista de categoría
    }

    // Manejo de errores
    @ExceptionHandler(IllegalArgumentException.class)
    public String manejarErrores(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}