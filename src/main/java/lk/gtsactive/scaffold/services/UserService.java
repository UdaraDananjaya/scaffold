package lk.gtsactive.scaffold.services;

import lk.gtsactive.scaffold.dtos.requests.users.CreateUserRequest;
import lk.gtsactive.scaffold.entities.UserEntity;
import lk.gtsactive.scaffold.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(CreateUserRequest request) {
        try {
            UserEntity userEntity = userRepository.findByEmail(request.getUsername());
            if (userEntity != null) {
                return "User already exists";
            }else {
                UserEntity user = new UserEntity();
                user.setEmail(request.getUsername());
                user.setPassword(request.getPassword());
                user.setFirstName(request.getFirstName());
                userRepository.save(user);
                return "User created";
            }
        }catch (Exception e) {
            return e.getMessage();
        }
    }
}
