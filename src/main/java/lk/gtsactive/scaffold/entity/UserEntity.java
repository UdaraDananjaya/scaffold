package lk.gtsactive.scaffold.entity;

import jakarta.persistence.*;
import lk.gtsactive.scaffold.helper.converter.EncryptConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Setter
@Getter

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Convert(converter = EncryptConverter.class)
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "created_at")@CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at") @UpdateTimestamp
    private Timestamp updatedAt;

}
