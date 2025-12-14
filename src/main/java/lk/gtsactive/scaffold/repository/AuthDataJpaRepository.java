package lk.gtsactive.scaffold.repository;

import lk.gtsactive.scaffold.entity.AuthDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthDataJpaRepository  extends JpaRepository<AuthDataEntity, Long> {
    boolean existsByTokenAndStatus(String token, String status);
}
