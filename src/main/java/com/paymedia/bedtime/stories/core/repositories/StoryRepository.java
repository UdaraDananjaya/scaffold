package com.paymedia.bedtime.stories.core.repositories;

import com.paymedia.bedtime.stories.core.dtos.stories.StoryAuthorDTO;
import com.paymedia.bedtime.stories.core.entities.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryRepository extends JpaRepository<StoryEntity, Long> {

    List<StoryEntity> findByAuthorId(long authorId);

    @Query("SELECT new  com.paymedia.bedtime.stories.core.dtos.stories.StoryAuthorDTO(s, a) FROM StoryEntity s, AuthorEntity a " +
            "WHERE s.authorId = a.id AND s.isPublished = true")
    List<StoryAuthorDTO> findAllPublishedWithAuthors();

    @Query("SELECT new com.paymedia.bedtime.stories.core.dtos.stories.StoryAuthorDTO(s, a) FROM StoryEntity s, AuthorEntity a " +
            "WHERE s.authorId = a.id AND s.id = :id")
    StoryAuthorDTO findByIdWithAuthor(@Param("id") long id);
}
