package com.paymedia.bedtime.stories.core.dtos.stories;

import lombok.Getter;
import lombok.Setter;

public class LikeDTO {
    @Getter @Setter
    private int likes;

    public LikeDTO(int likes) {
        this.likes = likes;
    }

    public LikeDTO() {
        // default
    }
}
