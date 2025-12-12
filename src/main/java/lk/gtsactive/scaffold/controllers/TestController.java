package lk.gtsactive.scaffold.controllers;

import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.authorization.SingleResultAuthorizationManager.permitAll;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @PostMapping("/ping")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/health")
    @PermitAll
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("app", "Scaffold API");

        return ResponseEntity.ok(response);
    }

}
