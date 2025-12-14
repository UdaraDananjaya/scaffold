package lk.gtsactive.scaffold.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import lk.gtsactive.scaffold.dto.requests.auth.LoginRequest;
import lk.gtsactive.scaffold.dto.responses.auth.LoginResponse;
import lk.gtsactive.scaffold.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @PermitAll
    public LoginResponse login( @RequestBody LoginRequest request, HttpServletRequest httpRequest) {

        return authService.login(
                request.getUsername(),
                request.getPassword(),
                httpRequest
        );
    }
}
