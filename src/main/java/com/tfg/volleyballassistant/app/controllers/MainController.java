package com.tfg.volleyballassistant.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView showHomePage() {
        // Renderiza la página principal (index.html) desde la carpeta resources/templates
        return new ModelAndView("entrenamientos/index");
    }

    @GetMapping("/tecnica")
    public ModelAndView showTecnicaPage() {
        // Renderiza la página técnica
        return new ModelAndView("entrenamientos/tecnica");
    }

    @GetMapping("/fisico")
    public ModelAndView showFisicoPage() {
        // Renderiza la página físico
        return new ModelAndView("entrenamientos/fisico");
    }

    @GetMapping("/estrategia")
    public ModelAndView showEstrategiaPage() {
        // Renderiza la página estrategia
        return new ModelAndView("entrenamientos/estrategia");
    }
}
