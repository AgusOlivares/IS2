package tinder.tindermascotas.service;

import tinder.tindermascotas.entities.Pet;
import tinder.tindermascotas.entities.Photo;
import tinder.tindermascotas.entities.Usser;
import tinder.tindermascotas.enums.Sexo;
import tinder.tindermascotas.exceptions.ErrorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tinder.tindermascotas.repositories.UsserRepository;
import tinder.tindermascotas.repositories.PetRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private UsserRepository usserRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PhotoService photoService;

    @Transactional
    public void addPet(MultipartFile file, String idUsser, String name, Sexo sexo) throws ErrorService {
        Usser usser = usserRepository.findById(idUsser).get();
        validate(name, sexo);
        Pet pet = new Pet();
        pet.setNombre(name);
        pet.setSexo(sexo);
        pet.setAlta(new Date());

        Photo photo = photoService.save(file);
        pet.setPhoto(photo);
        petRepository.save(pet);
    }

    @Transactional
    public void delete(MultipartFile file, String idUsser, String idPet) throws ErrorService {
        Optional<Pet> response = petRepository.findById(idPet);
        if (response.isPresent()) {
            Pet pet = response.get();
            if (pet.getUsser().getId().equals(idUsser)) {
                pet.setBaja(new Date());

                String idPhoto =null;
                if (pet.getPhoto() != null) {
                    idPhoto = pet.getPhoto().getId();
                }
                Photo photo = photoService.update(idPhoto, file);
                pet.setPhoto(photo);
                petRepository.save(pet);
            }
        } else
            throw new ErrorService("Mascota no encontrada");

    }

    ///habria que setear el id del usuario en la mascota??
    @Transactional
    public void modify(String idUsser, String idPet, String name, Sexo sexo) throws ErrorService {
        validate(name, sexo);
        Optional<Pet> response = petRepository.findById(idPet);
        if (response.isPresent()) {
            Pet pet = response.get();
            if (pet.getUsser().getId().equals(idUsser)) {
                pet.setNombre(name);
                pet.setSexo(sexo);
                petRepository.save(pet);
            } else {
                throw new ErrorService("No tiene permisos para esta operación");
            }
        }else {
            throw new ErrorService("No existe una mascota con el identificador solicitado");
        }
    }


    public void validate(String name, Sexo sexo) throws ErrorService {
        if (name==null|| name.isEmpty()){
            throw new ErrorService("El nombre de la mascota no puede ser nulo");
        }
        if (sexo==null){
            throw new ErrorService("El sexo no puede ser nulo");
        }
    }
}
