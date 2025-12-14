package lk.gtsactive.scaffold.service;

import lk.gtsactive.scaffold.dto.requests.user.CreateUserRequest;
import lk.gtsactive.scaffold.entity.UserEntity;
import lk.gtsactive.scaffold.repository.UserJpaRepository;
import lk.gtsactive.scaffold.util.password.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserJpaRepository userRepository;

    public String addUser(CreateUserRequest request) {
        try {
            if (userRepository.existsByEmail(request.getUsername())) {
                return "User already exists";
            }else {
                UserEntity user = new UserEntity();
                user.setEmail(request.getUsername());
                user.setPassword (PasswordUtil.encode(request.getPassword()));
                user.setFirstName(request.getFirstName());
                userRepository.save(user);
                return "User created";
            }
        }catch (Exception e) {
            return e.getMessage();
        }
    }
    public UserEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) {

        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
