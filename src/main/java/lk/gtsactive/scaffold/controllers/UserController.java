package lk.gtsactive.scaffold.controllers;

import lk.gtsactive.scaffold.dtos.requests.users.CreateUserRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @PostMapping("create")
    public String createUser( @ModelAttribute CreateUserRequest request) {
        return "success";
    }
}
