package com.paymedia.bedtime.stories.core.dtos.responses.stories;

import com.paymedia.bedtime.stories.core.dtos.stories.StoryAuthorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetPublishedStoriesResponse {
    private List<StoryAuthorDTO> stories;

    public GetPublishedStoriesResponse(List<StoryAuthorDTO> stories) {
        this.stories = stories;
    }

    public GetPublishedStoriesResponse() {
        // default
    }
}
