package lk.gtsactive.scaffold.dtos.requests.users;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CreateUserRequest {
    private String username;
    private String password;
    private String firstName;
}
