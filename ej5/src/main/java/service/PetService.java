package service;

import entities.Pet;
import entities.Usser;
import enums.Sexo;
import exceptions.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UsserRepository;
import repositories.PetRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    private UsserRepository usserRepository;
    @Autowired
    private PetRepository petRepository;

    public void addPet(String idUsser, String name, Sexo sexo) throws ErrorService {
        Usser usser = usserRepository.findById(idUsser).get();

        validate(name, sexo);

        Pet pet = new Pet();
        pet.setNombre(name);
        pet.setSexo(sexo);
        pet.setAlta(new Date());

        petRepository.save(pet);
    }

    public void delete(String idUsser, String idPet) throws ErrorService {
        Optional<Pet> response = petRepository.findById(idPet);
        if (response.isPresent()) {
            Pet pet = response.get();
            if (pet.getUsser().getId().equals(idUsser)) {
                pet.setBaja(new Date());
                petRepository.save(pet);
            }
        } else
            throw new ErrorService("Mascota no encontrada");

    }

    //habria que setear el id del usuario en la mascota??
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
