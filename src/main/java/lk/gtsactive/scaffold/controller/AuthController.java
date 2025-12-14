package lk.gtsactive.scaffold.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lk.gtsactive.scaffold.dto.requests.auth.LoginRequest;
import lk.gtsactive.scaffold.security.JwtTokenProvider;
import lk.gtsactive.scaffold.service.AuthService;
import lk.gtsactive.scaffold.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request,
            HttpServletRequest httpRequest
    ) {
        String token = authService.login(
                request.getUsername(),
                request.getPassword(),
                httpRequest
        );

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}
