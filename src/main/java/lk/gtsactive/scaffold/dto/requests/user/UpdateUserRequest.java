package lk.gtsactive.scaffold.dto.requests.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public class UpdateUserRequest {
    private String username;
    private String password;
    private String firstName;
}
