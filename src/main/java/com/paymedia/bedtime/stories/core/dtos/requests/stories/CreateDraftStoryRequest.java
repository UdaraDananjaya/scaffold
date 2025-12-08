package com.paymedia.bedtime.stories.core.dtos.requests.stories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class CreateDraftStoryRequest extends BaseStoryRequest{

    private Long id = null;
    private Boolean isPublished;
    private Long authorId = null;
}
