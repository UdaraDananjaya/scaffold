package lk.gtsactive.scaffold.dto.requests.auth;

import lombok.*;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}
