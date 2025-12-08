package com.paymedia.bedtime.stories.core.dtos.requests.stories;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRemoveLikeRequest {
    private Long storyId;
    private boolean liked;
}
