package tinder.tindermascotas.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tinder.tindermascotas.config.CustomUserDetails;
import tinder.tindermascotas.entities.Pet;
import tinder.tindermascotas.entities.User;
import tinder.tindermascotas.enums.Sexo;
import tinder.tindermascotas.enums.Type;
import tinder.tindermascotas.exceptions.ErrorService;
import tinder.tindermascotas.service.PetService;
import tinder.tindermascotas.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/mascota")
public class PetController {

    @Autowired
    private UserService userService;
    @Autowired
    private PetService petService;

    @GetMapping("/mis-mascotas")
    public String misMascotas(ModelMap model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Pet> mascotas = petService.searchByUser(customUserDetails.getId());
        model.put("mascotas", mascotas);
        return "mascotas";
    }
/// ver que editar parece que crea uno nuevo
    @GetMapping("/agregarMascota")
    public String agregar(ModelMap model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.put("sexos", Sexo.values());
        model.put("types", Type.values());
        Pet pet = new Pet();
        model.put("pet", pet);
        return "mascota";
    }


    @PostMapping("/actualizar")
    public String actualizar(ModelMap model, MultipartFile file, @RequestParam(required = false) String id, @AuthenticationPrincipal CustomUserDetails userDetails, @RequestParam String name, @RequestParam Sexo sexo, @RequestParam Type type ){
        User user = userService.searchById(userDetails.getId());
        try {
            if (id == null || id.isEmpty()) {
                petService.addPet(file, user.getId(), name, sexo, type);
            } else{
                petService.modify(file, user.getId(), id, name, sexo, type);
            }
            return "redirect:/mascotas";
        } catch (ErrorService e) {
            model.put("sexos", Sexo.values());
            model.put("types", Type.values());
            model.put("error", e.getMessage());
            model.put("perfil", user);
        }
        return "mascota";
    }

}
