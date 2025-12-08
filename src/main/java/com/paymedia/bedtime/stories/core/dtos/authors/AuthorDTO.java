package com.paymedia.bedtime.stories.core.dtos.authors;

import com.paymedia.bedtime.stories.core.entities.AuthorEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorDTO {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String imageKey;

    private String createdAt;


    public AuthorDTO(){
        // default
    }

    public AuthorDTO(AuthorEntity authorEntity){
        this.id = authorEntity.getId();
        this.firstName = authorEntity.getFirstName();
        this.lastName = authorEntity.getLastName();
        this.email = authorEntity.getEmail();
        this.imageKey = authorEntity.getImageKey();
        this.createdAt = authorEntity.getCreatedAt().toString();
    }
}
