package tinder.tindermascotas.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tinder.tindermascotas.repositories.UserRepository;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Builder
@ToString
@RequiredArgsConstructor
class UserSessionDetails implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String nombre;
    private final String apellido;
}

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public CustomAuthSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UserSessionDetails userSessionDetails = UserSessionDetails.builder()
                .id(userDetails.getId())
                .nombre(userDetails.getNombre())
                .apellido(userDetails.getApellido())
                .build();

        HttpSession session = request.getSession(true);
        session.setAttribute("ussersession", userSessionDetails);

        response.sendRedirect("/inicio");
    }
}
