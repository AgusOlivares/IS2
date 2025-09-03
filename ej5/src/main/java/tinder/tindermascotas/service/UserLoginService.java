package tinder.tindermascotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tinder.tindermascotas.entities.User;
import tinder.tindermascotas.repositories.UserRepository;


@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.searchByMail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        System.out.println("Autenticando: " + user.getMail());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getMail())
                .password(user.getClave()) // ya encriptada
                .roles("USER")
                .build();
    }
}
