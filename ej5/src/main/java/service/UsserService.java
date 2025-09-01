package service;

import entities.Photo;
import entities.Usser;
import exceptions.ErrorService;
import jakarta.mail.Multipart;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repositories.PhotoRepository;
import repositories.UsserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsserService implements UserDetailsService {

    @Autowired
    private UsserRepository usserRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PhotoService photoService;

    @Transactional
    public void register(MultipartFile file, String nombre, String apellido, String mail, String clave) throws ErrorService {
        validate(nombre, apellido, mail, clave);
        Usser usser = new Usser();
        usser.setNombre(nombre);
        usser.setApellido(apellido);
        usser.setMail(mail);
        usser.setClave(clave);
        usser.setAlta(new Date());

        Photo photo = photoService.save(file);
        usser.setPhoto(photo);

        usserRepository.save(usser);

        notificationService.send("Bienvenido al Tinder de Mascotas!", "Tinder de Mascotas", usser.getMail());
    }

    @Transactional
    public void modify(MultipartFile file, String id, String nombre, String apellido, String mail, String clave) throws ErrorService {
        validate(nombre, apellido, mail, clave);
        Optional<Usser> response = usserRepository.findById(id);
        if (response.isPresent()) {
            Usser usser = response.get();
            usser.setNombre(nombre);
            usser.setApellido(apellido);
            usser.setMail(mail);
            usser.setClave(clave);

            String idPhoto = null;
            if (usser.getPhoto() != null) {
                idPhoto = usser.getPhoto().getId();
            }
            Photo photo = photoService.update(idPhoto, file);
            usser.setPhoto(photo);

            usserRepository.save(usser);
        } else {
            throw new ErrorService("No se encontro el usuario solicitado");
        }

    }

    @Transactional
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

    @Transactional
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

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usser usser = usserRepository.searchByMail(mail);
        if (usser != null) {
            List<GrantedAuthority> permits = new ArrayList<>();
            GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_PHOTOS");
            permits.add(p1);
            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_PETS");
            permits.add(p1);
            GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_VOTES");
            permits.add(p1);

            User user = new User(usser.getMail(), usser.getClave(), permits);
            return user;
        } else {
            return null;
        }
    }
}

