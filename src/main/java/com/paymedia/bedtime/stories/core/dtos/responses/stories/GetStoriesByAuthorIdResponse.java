package com.paymedia.bedtime.stories.core.dtos.responses.stories;

import com.paymedia.bedtime.stories.core.dtos.stories.StoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GetStoriesByAuthorIdResponse {
    private List<StoryDTO> stories;

    public GetStoriesByAuthorIdResponse(List<StoryDTO> stories) {
        this.stories = stories;
    }

    public GetStoriesByAuthorIdResponse() {
        // default
    }
}
