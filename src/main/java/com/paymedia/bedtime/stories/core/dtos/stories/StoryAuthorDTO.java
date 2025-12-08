package com.paymedia.bedtime.stories.core.dtos.stories;

import com.paymedia.bedtime.stories.core.dtos.authors.AuthorDTO;
import com.paymedia.bedtime.stories.core.entities.AuthorEntity;
import com.paymedia.bedtime.stories.core.entities.StoryEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoryAuthorDTO extends StoryDTO{

    private AuthorDTO author;

    public StoryAuthorDTO() {
        super();

        // default
    }


    public StoryAuthorDTO(StoryEntity storyEntity, AuthorEntity authorEntity) {
        super(storyEntity);
        this.author = new AuthorDTO(authorEntity);
    }
}
