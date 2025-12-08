package lk.gtsactive.scaffold.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.security.authorization.SingleResultAuthorizationManager.permitAll;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @PostMapping("/ping")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("OK");
    }
}
