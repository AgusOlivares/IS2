package com.example.ej4;

import com.example.ej4.entity.VideojuegoEntity;
import com.example.ej4.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    @Autowired
    private VideojuegoService videojuegoService;

    @GetMapping("/inicio")
    public String index(Model model) {
        try {
            model.addAttribute("videojuegos", videojuegoService.obtenerTodos());
            model.addAttribute("videojuego", new VideojuegoEntity()); // Para el formulario
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index";
    }
}
