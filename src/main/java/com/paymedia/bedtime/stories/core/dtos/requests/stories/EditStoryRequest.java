package com.paymedia.bedtime.stories.core.dtos.requests.stories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class EditStoryRequest extends BaseStoryRequest {

    private Long id;
    private String title = null;
    private String body = null;
    private MultipartFile thumbnail = null;
}
