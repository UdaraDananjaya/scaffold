package lk.gtsactive.scaffold.controller;

import jakarta.annotation.security.PermitAll;
import lk.gtsactive.scaffold.dto.requests.user.CreateUserRequest;
import lk.gtsactive.scaffold.service.UserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PermitAll
    @PostMapping("create")
    public String createUser( @ModelAttribute CreateUserRequest request) {
        return userService.addUser(request);
    }
}
