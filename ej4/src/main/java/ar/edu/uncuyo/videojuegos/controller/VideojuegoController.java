package ar.edu.uncuyo.videojuegos.controller;

import ar.edu.uncuyo.videojuegos.entity.VideojuegoEntity;
import ar.edu.uncuyo.videojuegos.service.CategoriaService;
import ar.edu.uncuyo.videojuegos.service.EstudioService;
import ar.edu.uncuyo.videojuegos.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.*;
import java.util.List;

@Controller
public class VideojuegoController {

    @Autowired
    private VideojuegoService service;

    @Autowired
    private EstudioService estudioService;

    @Autowired
    private CategoriaService categoriaService;
    

    @GetMapping("/videojuegos/listarVideojuegos")
    public String listarVideojuegos(Model model){

        try {
            List<VideojuegoEntity> lista = service.obtenerTodos();
            model.addAttribute("listarVideojuegos", lista);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "view/videojuegos/lVideojuegos";
    }

    @GetMapping("/videojuegos/consultalVideojuego")
    public String consultarVideojuego(Model model, Long id){
        try {
            VideojuegoEntity videojuego = service.obtenerPorId(id).get();
            model.addAttribute("videojuego", videojuego);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "view/videojuegos/vVideojuego";
    }


    @GetMapping("/formulario/videojuegos/{id}")
    public String formVideojuego(Model model, @PathVariable("id") Long id) {

        try {

            model.addAttribute("estudios", estudioService.obtenerTodos());
            model.addAttribute("categorias", categoriaService.obtenerTodos());

            if (id == 0 ){
                model.addAttribute("videojuego", new VideojuegoEntity());
            } else {
                VideojuegoEntity videojuego = service.obtenerPorId(id).get();
                model.addAttribute("videojuego", videojuego);
            }
            List<VideojuegoEntity> lista = service.obtenerTodos();
            model.addAttribute("listarVideojuegos", lista);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "view/videojuegos/lVideojuegos";
    }
}
