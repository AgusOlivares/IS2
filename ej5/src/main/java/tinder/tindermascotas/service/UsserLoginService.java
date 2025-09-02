package tinder.tindermascotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tinder.tindermascotas.entities.Usser;
import tinder.tindermascotas.repositories.UsserRepository;


@Service
public class UsserLoginService implements UserDetailsService {

        @Autowired
        private UsserRepository usserRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usser usser = usserRepository.searchByMail(username);
            if (usser == null) {
                throw new UsernameNotFoundException("Invalid username or password");
            }
            System.out.println("Autenticando: " + usser.getMail());

            return User.builder()
                    .username(usser.getMail())
                    .password(usser.getClave()) // ya encriptada
                    .roles("USER")
                    .build();

    }

}
