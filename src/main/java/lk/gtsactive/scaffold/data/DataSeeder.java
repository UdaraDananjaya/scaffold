package lk.gtsactive.scaffold.data;

import lk.gtsactive.scaffold.entity.UserEntity;
import lk.gtsactive.scaffold.repository.UserJpaRepository;
import lk.gtsactive.scaffold.util.password.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder  implements CommandLineRunner {
    private final UserJpaRepository userRepository;

    @Override
    public void run(String... args) {

        if (userRepository.existsByEmail("admin@gtsactive.lk")) {
            return; // already seeded
        }

        UserEntity admin = new UserEntity();
        admin.setEmail("admin@gtsactive.lk");
        admin.setPassword( PasswordUtil.encode("admin123")); // use encoder in real apps
        admin.setFirstName("Admin");

        userRepository.save(admin);

        System.out.println("✅ Admin user seeded");
    }
}
