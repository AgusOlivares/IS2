package ar.edu.uncuyo.videojuegos.controller;

import ar.edu.uncuyo.videojuegos.entity.Category;
import ar.edu.uncuyo.videojuegos.entity.Studio;
import ar.edu.uncuyo.videojuegos.entity.Videogame;
import ar.edu.uncuyo.videojuegos.repository.VideogameRepository;
import ar.edu.uncuyo.videojuegos.service.CategoryService;
import ar.edu.uncuyo.videojuegos.service.StudioService;
import ar.edu.uncuyo.videojuegos.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class HomeController {

    @Autowired
    private VideogameService videogameService;
    @Autowired
    private StudioService studioService;
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/index")
    public String inicio(Model model) {

        Collection<Videogame> videogameList = videogameService.getAllVideogames();
        Collection<Studio> studioList = studioService.getAllStudios();
        Collection<Category> categoryList = categoryService.getAllCategories();

        model.addAttribute("studios", studioList);
        model.addAttribute("categories", categoryList);

        model.addAttribute("videogameLista", videogameList);
        model.addAttribute("cantidadVideogamess", videogameList.size());

        return "plantilla/index";
    }
}
