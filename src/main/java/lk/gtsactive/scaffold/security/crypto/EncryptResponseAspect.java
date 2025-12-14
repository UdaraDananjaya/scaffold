package lk.gtsactive.scaffold.security.crypto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.gtsactive.scaffold.dto.responses.base.EncryptedResponse;
import lk.gtsactive.scaffold.util.crypto.CryptoUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EncryptResponseAspect {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Around("@annotation(encrypted)")
    public Object encryptResponse(ProceedingJoinPoint joinPoint, Encrypted encrypted) throws Throwable {
        Object result = joinPoint.proceed();

        String json;

        // If controller returned ResponseEntity, get the body
        if (result instanceof ResponseEntity<?> responseEntity) {
            json = objectMapper.writeValueAsString(responseEntity.getBody());
        } else {
            json = objectMapper.writeValueAsString(result);
        }

        // Encrypt JSON
        String encryptedData = CryptoUtil.encrypt(json);

        // Return as ResponseEntity<EncryptedResponse>
        return ResponseEntity.ok(new EncryptedResponse(encryptedData));
    }
}
