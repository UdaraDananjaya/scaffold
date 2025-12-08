package com.paymedia.bedtime.stories.core.dtos.requests.stories;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetStoriesByAuthorRequest {

    private Long authorId;

    public GetStoriesByAuthorRequest() {
        // default
    }

    public GetStoriesByAuthorRequest(Long authorId) {
        this.authorId = authorId;
    }
}
