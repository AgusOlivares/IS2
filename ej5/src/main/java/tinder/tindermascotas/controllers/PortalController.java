package tinder.tindermascotas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tinder.tindermascotas.entities.Zone;
import tinder.tindermascotas.repositories.ZoneRepository;
import tinder.tindermascotas.service.UserService;

import java.util.List;

@Controller
public class PortalController {
    @Autowired
    private UserService userService;
    @Autowired
    private ZoneRepository zoneRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o clave incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Se ha deslogueado correctamente");
        }
        return "login";
    }

    @GetMapping("/registro")
    public String registro(ModelMap model) {
        try {
            List<Zone> zonas = zoneRepository.findAll();
            model.put("zonas", zonas);
            return "registro";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error"; // TODO no sé si debería devolver esto
        }
    }
}
