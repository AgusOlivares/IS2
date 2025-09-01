package tinder.tindermascotas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tinder.tindermascotas.entities.Zone;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PortalController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        try {
            List<Zone> zonas = new ArrayList<Zone>();
            model.addAttribute("zonas", zonas); // TODO enviar las zonas verdaderas
            return "registro";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
        System.out.println(nombre + " " + apellido + " " + mail + " " + clave1  + " " + clave2);
        return "registro.html";
    }
}
