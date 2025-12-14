package lk.gtsactive.scaffold.service;

import lk.gtsactive.scaffold.entity.AuthDataEntity;
import lk.gtsactive.scaffold.entity.UserEntity;
import lk.gtsactive.scaffold.repository.AuthDataJpaRepository;
import lk.gtsactive.scaffold.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AuthDataJpaRepository authDataRepository;

    public String login(String username, String password, HttpServletRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                username,
                                password
                        )
                );

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String token = jwtTokenProvider.generateToken(userDetails);

        UserEntity user =
                userService.getUserEntityByEmail(username);

        AuthDataEntity authData = new AuthDataEntity();
        authData.setUser(user);
        authData.setToken(token);
        authData.setStatus("ACTIVE");
        authData.setIpAddress(request.getRemoteAddr());

        authDataRepository.save(authData);

        return token;
    }
}
