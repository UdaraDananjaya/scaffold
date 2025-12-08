package com.paymedia.bedtime.stories.core.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "author")
public class AuthorEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name = "image_key")
    private String imageKey;

    @Column(name = "created_at")@CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at") @UpdateTimestamp
    private Timestamp updatedAt;
}
