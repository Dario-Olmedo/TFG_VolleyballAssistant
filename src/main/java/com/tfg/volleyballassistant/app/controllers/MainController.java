package com.tfg.volleyballassistant.app.controllers;

import com.tfg.volleyballassistant.app.model.Entrenamiento;
import com.tfg.volleyballassistant.app.services.EntrenamientoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final EntrenamientoService entrenamientoService;

    // Constructor para inyectar el servicio
    public MainController(EntrenamientoService entrenamientoService) {
        this.entrenamientoService = entrenamientoService;
    }

    @GetMapping("/")
    public String showHomePage() {
        // Renderiza la página principal (index.html) desde la carpeta resources/templates
        return "entrenamientos/index";
    }

    @GetMapping("/tecnica")
    public String showTecnicaPage(Model model) {
        // Obtiene los entrenamientos de la categoría TECNICA
        List<Entrenamiento> entrenamientos = entrenamientoService.listarEntrenamientosPorCategoria(Entrenamiento.Categoria.TECNICA);
        model.addAttribute("entrenamientos", entrenamientos);
        return "entrenamientos/tecnica";
    }

    @GetMapping("/fisico")
    public String showFisicoPage(Model model) {
        // Obtiene los entrenamientos de la categoría FISICO
        List<Entrenamiento> entrenamientos = entrenamientoService.listarEntrenamientosPorCategoria(Entrenamiento.Categoria.FISICO);
        model.addAttribute("entrenamientos", entrenamientos);
        return "entrenamientos/fisico";
    }

    @GetMapping("/estrategia")
    public String showEstrategiaPage(Model model) {
        // Obtiene los entrenamientos de la categoría ESTRATEGIA
        List<Entrenamiento> entrenamientos = entrenamientoService.listarEntrenamientosPorCategoria(Entrenamiento.Categoria.ESTRATEGIA);
        model.addAttribute("entrenamientos", entrenamientos);
        return "entrenamientos/estrategia";
    }
}
