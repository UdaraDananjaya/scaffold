package com.paymedia.bedtime.stories.core.dtos.stories;

import com.paymedia.bedtime.stories.core.entities.StoryEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class StoryDTO {

    private Long id;

    private String title;

    private String body;

    private int likes;

    private boolean isPublished;

    private String thumbnailKey;

    private long authorId;

    private String updatedAt;

    public StoryDTO() {
        // default
    }

    public StoryDTO(StoryEntity storyEntity) {
        this.id = storyEntity.getId();
        this.title = storyEntity.getTitle();
        this.body = storyEntity.getBody();
        this.likes = storyEntity.getLikes();
        this.isPublished = storyEntity.isPublished();
        this.thumbnailKey = storyEntity.getThumbnailKey();
        this.authorId = storyEntity.getAuthorId();
        this.updatedAt = storyEntity.getUpdatedAt().toString();
    }
}
