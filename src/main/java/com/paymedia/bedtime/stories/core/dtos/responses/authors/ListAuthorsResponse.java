package com.paymedia.bedtime.stories.core.dtos.responses.authors;

import com.paymedia.bedtime.stories.core.dtos.authors.AuthorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ListAuthorsResponse {
    private List<AuthorDTO> authors;

    public ListAuthorsResponse(List<AuthorDTO> authors) {
        this.authors = authors;
    }


    public ListAuthorsResponse() {
        // default
    }
}
