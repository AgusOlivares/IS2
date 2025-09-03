package tinder.tindermascotas.service;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import tinder.tindermascotas.entities.Photo;
import tinder.tindermascotas.entities.User;
import tinder.tindermascotas.entities.Zone;
import tinder.tindermascotas.exceptions.ErrorService;
import tinder.tindermascotas.repositories.UserRepository;
import tinder.tindermascotas.repositories.ZoneRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private ZoneRepository zoneRepository;

    @Transactional
    public void register(MultipartFile file, String nombre, String apellido, String mail, String clave1, String clave2, String idZona) throws ErrorService {
        Zone zone = zoneRepository.getZoneById(idZona);
        validate(nombre, apellido, mail, clave1, clave2, zone);

        User user = new User();
        user.setZone(zone);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setMail(mail);
        user.setClave(clave1);
        user.setAlta(new Date());

        Photo photo = photoService.save(file);
        user.setPhoto(photo);

        userRepository.save(user);

        ///notificationService.send("Bienvenido al Tinder de Mascotas!", "Tinder de Mascotas", usser.getMail());
    }

    @Transactional
    public void modify(MultipartFile file, String id, String nombre, String apellido, String mail, String clave1, String clave2, String idZona) throws ErrorService {
        Zone zone = zoneRepository.getZoneById(idZona);
        validate(nombre, apellido, mail, clave1, clave2, zone);
        Optional<User> response = userRepository.findById(id);
        if (response.isPresent()) {
            User user = response.get();
            user.setNombre(nombre);
            user.setApellido(apellido);
            user.setMail(mail);
            user.setClave(clave1);
            user.setZone(zone);

            String idPhoto = null;
            if (user.getPhoto() != null) {
                idPhoto = user.getPhoto().getId();
            }
            Photo photo = photoService.update(idPhoto, file);
            user.setPhoto(photo);

            userRepository.save(user);
        } else {
            throw new ErrorService("No se encontro el usuario solicitado");
        }

    }

    @Transactional
    public void enable(String id) throws ErrorService {
        Optional<User> response = userRepository.findById(id);
        if (response.isPresent()) {
            User user = response.get();
            user.setBaja(null);
            userRepository.save(user);
        } else {
            throw new ErrorService("No se encontro el usuario solicitado");
        }
    }

    @Transactional
    public void disable(String id) throws ErrorService {
        Optional<User> response = userRepository.findById(id);
        if (response.isPresent()) {
            User user = response.get();
            user.setBaja(new Date());
            userRepository.save(user);
        } else {
            throw new ErrorService("No se encontro el usuario solicitado");
        }
    }


    private void validate(String nombre, String apellido, String mail, String clave1, String clave2, Zone zone) throws ErrorService {
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorService("El nombre del usuario no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorService("El apellido del usuario no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {
            throw new ErrorService("El mail del usuario no puede ser nulo");
        }
        if (clave1 == null || clave1.length() <= 6) {
            throw new ErrorService("La clave del usuario no puede ser nula y tiene que ser de mas de 6 digitos");
        }
        if (!clave1.equals(clave2)) {
            throw new ErrorService("Las claves del usuario no coinciden");
        }
        if (zone == null) {
            throw new ErrorService("Zona no encontrada");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.searchByMail(mail);
        if (user != null) {
            List<GrantedAuthority> permits = new ArrayList<>();
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
            permits.add(p1);

            ServletRequestAttributes att = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession session = att.getRequest().getSession(true);
            session.setAttribute("ussersession", user);

            org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(user.getMail(), user.getClave(), permits);
            return secUser;
        } else {
            return null;
        }
    }
}

