package com.example.ej4.Controller;


import com.example.ej4.entity.VideojuegoEntity;
import com.example.ej4.service.VideojuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    private String viewList= "view/videojuego/lVideojuego.html";
    private String redirectList= "redirect:view/videojuego/listVideojuego";
    private String viewEdit= "view/videojuego/eVideojuego.html";

    ///////////////// VIEW: lVideojuego ///////////////

    @GetMapping("/videojuego/listVideojuego")
    public String listVideojuego(Model model) {

        try {
            Collection<VideojuegoEntity> videojuegos = videojuegoService.obtenerTodos();

            model.addAttribute("videojuegos", videojuegos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return viewList;
    }



}
