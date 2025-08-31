package service;

import entities.Usser;
import exceptions.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UsserRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class UsserService {

    @Autowired
    private UsserRepository usserRepository;

    public void register(String nombre, String apellido, String mail, String clave) throws ErrorService {
        validate(nombre, apellido, mail, clave);
        Usser usser = new Usser();
        usser.setNombre(nombre);
        usser.setApellido(apellido);
        usser.setMail(mail);
        usser.setClave(clave);
        usser.setAlta(new Date());

        usserRepository.save(usser);
    }

    public void modify(String id, String nombre, String apellido, String mail, String clave) throws ErrorService {
        validate(nombre, apellido, mail, clave);
        Optional<Usser> response = usserRepository.findById(id);
        if (response.isPresent()) {
            Usser usser = response.get();
            usser.setNombre(nombre);
            usser.setApellido(apellido);
            usser.setMail(mail);
            usser.setClave(clave);
            usserRepository.save(usser);
        } else {
            throw new ErrorService("No se encontro el usuario solicitado");
        }

    }

    public void enable (String id) throws ErrorService {
        Optional<Usser> response = usserRepository.findById(id);
        if (response.isPresent()) {
            Usser usser = response.get();
            usser.setBaja(null);
            usserRepository.save(usser);
        } else {
            throw new ErrorService("No se encontro el usuario solicitado");
        }
    }
    public void disable (String id) throws ErrorService {
        Optional<Usser> response = usserRepository.findById(id);
        if (response.isPresent()) {
            Usser usser = response.get();
            usser.setBaja(new Date());
            usserRepository.save(usser);
        } else {
            throw new ErrorService("No se encontro el usuario solicitado");
        }
    }


    private void validate(String nombre, String apellido, String mail, String clave)  throws ErrorService{
        if (nombre ==null || nombre.isEmpty()){
            throw new ErrorService("El nombre del usuario no puede ser nulo");
        }
        if (apellido ==null || apellido.isEmpty()){
            throw new ErrorService("El apellido del usuario no puede ser nulo");
        }
        if (mail ==null || mail.isEmpty()){
            throw new ErrorService("El mail del usuario no puede ser nulo");
        }
        if (clave == null || clave.isEmpty() || clave.length()<=6){
            throw new ErrorService("La clave del usuario no puede ser nula y tiene que ser de mas de 6 digitos");
        }
    }
}
