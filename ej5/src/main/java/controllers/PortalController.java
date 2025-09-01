package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
        System.out.println(nombre + " " + apellido + " " + mail + " " + clave1  + " " + clave2);
        return "registro.html";
    }
}
