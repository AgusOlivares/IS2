package ar.edu.uncuyo.videojuegos.controller;

import ar.edu.uncuyo.videojuegos.entity.Category;
import ar.edu.uncuyo.videojuegos.error.BusinessException;
import ar.edu.uncuyo.videojuegos.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private String viewList = "view/category/list";
    private String viewEdit = "plantilla/view/category/edit";
    private String redirectList = "redirect:/index";

    @GetMapping("")
    public String list(Model model) {
        try {
            Collection<Category> categoryList = categoryService.getAllCategories();
            model.addAttribute("categoryList", categoryList);
        } catch(Exception e) {
            model.addAttribute("msgError", e.getMessage());
            System.out.println(e.getMessage());
        }
        return viewList;
    }

    @GetMapping("/{id}")
    public String consultar(@PathVariable("id") Long id, Model model) {
        try {
            Category category = categoryService.getCategory(id);
            model.addAttribute("category", category);
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
        model.addAttribute("category", new Category());
        model.addAttribute("isDisabled", false);
        return viewEdit;
    }

    @GetMapping("/{id}/edit")
    public String modificar(@PathVariable("id") Long id, Model model) {

        try {
            Category category = categoryService.getCategory(id);
            model.addAttribute("category", category);
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
    public String aceptarEdit(Category category, BindingResult result, RedirectAttributes attributes, Model model) {
        try {
            if (result.hasErrors()){
                model.addAttribute("msgError", "Error de Sistema");
                return viewEdit;
            }

            if (category.getId() == null)
                categoryService.createCategory(category.getName());
            else
                categoryService.updateCategory(category.getId(), category.getName());

            attributes.addFlashAttribute("msgExito", "La acción fue realizada correctamente.");
            return redirectList;

        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return viewEdit;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            model.addAttribute("msgError", "Error de sistemas");
            return redirectList;
        }
    }

    @GetMapping("/{id}/delete")
    public String baja(@PathVariable("id") Long id, RedirectAttributes attributes, Model model) {
        try {
            categoryService.deleteCategory(id);
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
}