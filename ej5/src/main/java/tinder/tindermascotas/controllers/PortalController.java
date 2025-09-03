package tinder.tindermascotas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tinder.tindermascotas.entities.Zone;
import tinder.tindermascotas.exceptions.ErrorService;
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

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout,  ModelMap model) {
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
            List<Zone> zonas = (List<Zone>) zoneRepository.findAll();
            model.put("zonas", zonas);
            ///model.addAttribute("zonas", zonas); // TODO enviar las zonas verdaderas
            return "registro";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap model , MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2, @RequestParam String idZona) {
        try {
            userService.register(archivo, nombre, apellido, mail, clave1, clave2, idZona);
        } catch (ErrorService ex){
            List<Zone> zonas = (List<Zone>) zoneRepository.findAll();
            model.put("zonas", zonas);
            model.put("error", ex.getMessage());
            model.put("nombre", nombre);
            model.put("apellido", apellido);
            model.put("mail", mail);
            model.put("clave1", clave1);
            model.put("clave2", clave2);
            return "registro";
        }
        return "exito";
    }
}
