package lk.gtsactive.scaffold.controller;

import lk.gtsactive.scaffold.security.crypto.Encrypted;
import lk.gtsactive.scaffold.util.crypto.CryptoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class HealthController {
    @PostMapping("/ping")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/health")
    @PreAuthorize("isAuthenticated()")

    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now());
        response.put("app", "Scaffold API");

        return ResponseEntity.ok(response);
    }

    @Encrypted
    @GetMapping("/encrypt")
    public ResponseEntity<String> encrypt() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> decrypt(@RequestBody String payload) {

        String decrypted = CryptoUtil.decrypt(payload);

        return ResponseEntity.ok(decrypted);
    }

}
