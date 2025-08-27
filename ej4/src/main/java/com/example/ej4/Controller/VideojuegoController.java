package com.example.ej4.Controller;

import com.example.ej4.entity.VideojuegoEntity;
import com.example.ej4.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService service;

    // --- Métodos MVC ---
    @PostMapping
    public String crear(@ModelAttribute("videojuego") VideojuegoEntity videojuego) {
        service.guardar(videojuego);
        return "redirect:/inicio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/inicio";
    }

    // --- Métodos REST ---
    @GetMapping("/api")
    @ResponseBody
    public List<VideojuegoEntity> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<VideojuegoEntity> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
