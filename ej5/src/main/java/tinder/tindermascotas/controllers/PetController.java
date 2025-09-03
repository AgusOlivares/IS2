package tinder.tindermascotas.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tinder.tindermascotas.entities.Pet;
import tinder.tindermascotas.enums.Type;
import tinder.tindermascotas.enums.Sexo;
import tinder.tindermascotas.exceptions.ErrorService;
import tinder.tindermascotas.service.PetService;
import tinder.tindermascotas.service.UserService;
import tinder.tindermascotas.entities.User;

@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
@Controller
@RequestMapping("/mascota")
public class PetController {

    @Autowired
    private UserService userService;
    @Autowired
    private PetService petService;

    @GetMapping("/editar-perfil")
    public String editarPerfil(HttpSession Session, @RequestParam(required = false) String id, ModelMap model) {
        /* Poner cuando funcione el login aca y en registrar
        User login = (User)session.getAttribute("usuariosession");
        if (login == null){
        return "redirect:/inicio
        }
        * */
        Pet pet = new Pet();
        if (id != null && !id.isEmpty()) {
            try {
                pet = petService.searchById(id);
            } catch (ErrorService e) {
                throw new RuntimeException(e);
            }
        }
        model.put("perfil", pet);
        model.put("Sexos", Sexo.values());
        model.put("Tipos", Type.values());
        return "mascota";
    }

    @PreAuthorize("hasAnyROle('ROLE_USUARIO_REGISTRADO')")
    @PostMapping("/actualizar-perfil")
    public String registrar(ModelMap model, HttpSession Session, MultipartFile file, @RequestParam(required = false) String id, @RequestParam String idUser, @RequestParam String name, @RequestParam Sexo sexo, @RequestParam Type type ){
        Pet pet = new Pet();
        try {
            if (id == null || id.isEmpty()) {
                petService.addPet(file, idUser, name, sexo, type);
            } else{
                petService.modify(file, idUser, id, name, sexo, type);
            }
        } catch (ErrorService e) {
            pet.setId(id);
            pet.setNombre(name);
            pet.setSexo(sexo);
            pet.setType(type);
            model.put("sexos", Sexo.values());
            model.put("tipos", Type.values());
            model.put("error", e.getMessage());
            model.put("perfil", idUser); ///deberia ser el usuario aca

        }
        return "perfil";
    }

}
