package com.paymedia.bedtime.stories.core.dtos.requests.stories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class BaseStoryRequest {

    private String title;
    private String body;
    private MultipartFile thumbnail = null;
}
