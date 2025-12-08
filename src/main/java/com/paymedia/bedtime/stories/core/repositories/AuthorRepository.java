package com.paymedia.bedtime.stories.core.repositories;

import com.paymedia.bedtime.stories.core.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findByEmail(String email);
}
