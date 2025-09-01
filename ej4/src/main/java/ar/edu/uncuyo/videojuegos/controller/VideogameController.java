package ar.edu.uncuyo.videojuegos.controller;

import ar.edu.uncuyo.videojuegos.entity.Category;
import ar.edu.uncuyo.videojuegos.entity.Studio;
import ar.edu.uncuyo.videojuegos.entity.Videogame;
import ar.edu.uncuyo.videojuegos.error.BusinessException;
import ar.edu.uncuyo.videojuegos.service.CategoryService;
import ar.edu.uncuyo.videojuegos.service.StudioService;
import ar.edu.uncuyo.videojuegos.service.VideogameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/videogames")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    private String viewList = "view/videogame/list";
    private String viewEdit = "view/videogame/edit";
    private String redirectList = "redirect:/videogames";
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StudioService studioService;

    @GetMapping("")
    public String list(Model model) {
        try {
            Collection<Videogame> videogameList = videogameService.getAllVideogames();
            model.addAttribute("videogameList", videogameList);
        } catch(Exception e) {
            model.addAttribute("msgError", e.getMessage());
            System.out.println(e.getMessage());
        }
        return viewList;
    }

    @GetMapping("/{id}")
    public String consultar(@PathVariable("id") Long id, Model model) {
        populateSelectFields(model);
        try {
            Videogame videogame = videogameService.getVideogame(id);
            model.addAttribute("videogame", videogame);
            model.addAttribute("isDisabled", true);
            return viewEdit;

        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewEdit;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("msgError", "Error de sistemas");
            return redirectList;
        }
    }

    @GetMapping("/add")
    public String add(Model model) {
        populateSelectFields(model);
        model.addAttribute("videogame", new Videogame());
        model.addAttribute("isDisabled", false);
        return viewEdit;
    }

    @GetMapping("/{id}/edit")
    public String modificar(@PathVariable("id") Long id, Model model) {
        populateSelectFields(model);
        try {
            Videogame videogame = videogameService.getVideogame(id);
            model.addAttribute("videogame", videogame);
            model.addAttribute("isDisabled", false);
            return viewEdit;                      //"view/pais/ePais.html"

        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewEdit;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("msgError", "Error de sistemas");
            return redirectList;
        }
    }

    @PostMapping("/aceptarEdit")
    public String aceptarEdit(Videogame videogame,
                              BindingResult result,
                              RedirectAttributes attributes,
                              @RequestParam Long studioId,
                              @RequestParam Long categoryId,
                              Model model) {
        try {
            if (result.hasErrors()){
                model.addAttribute("msgError", "Error de Sistema");
                return viewEdit;
            }

            if (videogame.getId() == null)
                videogameService.createVideogame(videogame, studioId, categoryId);
            else
                videogameService.updateVideogame(videogame, studioId, categoryId);

            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewEdit;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("msgError", "Error de sistemas");
            return viewEdit;
        }
    }

    @GetMapping("/{id}/delete")
    public String baja(@PathVariable("id") Long id, RedirectAttributes attributes, Model model) {
        try {
            videogameService.deleteVideogame(id);
            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;
        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return redirectList;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("msgError", "Error de sistemas");
            return redirectList;
        }
    }

    private void populateSelectFields(Model model) {
        List<Studio> studios = studioService.getAllStudios();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("studios", studios);
        model.addAttribute("categories", categories);
    }
}
