package com.paymedia.bedtime.stories.core.controllers;

import com.paymedia.bedtime.stories.core.dtos.authors.AuthorDTO;
import com.paymedia.bedtime.stories.core.dtos.requests.authors.CreateAuthorRequest;
import com.paymedia.bedtime.stories.core.dtos.responses.BaseResponse;
import com.paymedia.bedtime.stories.core.dtos.responses.authors.ListAuthorsResponse;
import com.paymedia.bedtime.stories.core.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public BaseResponse<String> createAuthor(@ModelAttribute CreateAuthorRequest createAuthorRequest) {
        return authorService.createAuthor(createAuthorRequest);
    }


    @GetMapping("/list")
    public BaseResponse<ListAuthorsResponse> listAuthors() {
        return authorService.listAuthors();
    }
}
