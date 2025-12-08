package com.paymedia.bedtime.stories.core.dtos.requests.authors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class CreateAuthorRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private MultipartFile image;
}
