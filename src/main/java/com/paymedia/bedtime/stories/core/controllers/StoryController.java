package com.paymedia.bedtime.stories.core.controllers;

import com.paymedia.bedtime.stories.core.dtos.requests.stories.AddRemoveLikeRequest;
import com.paymedia.bedtime.stories.core.dtos.requests.stories.CreateDraftStoryRequest;
import com.paymedia.bedtime.stories.core.dtos.requests.stories.GetStoriesByAuthorRequest;
import com.paymedia.bedtime.stories.core.dtos.responses.BaseResponse;
import com.paymedia.bedtime.stories.core.dtos.responses.stories.GetPublishedStoriesResponse;
import com.paymedia.bedtime.stories.core.dtos.responses.stories.GetStoriesByAuthorIdResponse;
import com.paymedia.bedtime.stories.core.dtos.stories.LikeDTO;
import com.paymedia.bedtime.stories.core.dtos.stories.StoryAuthorDTO;
import com.paymedia.bedtime.stories.core.dtos.stories.StoryDTO;
import com.paymedia.bedtime.stories.core.services.StoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/story")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @PostMapping("/create")
    public BaseResponse<String> createStory(@ModelAttribute CreateDraftStoryRequest request) {
        return storyService.createDraftStory(request);
    }

    @GetMapping("/list")
    public BaseResponse<GetPublishedStoriesResponse> getStories(){
       return storyService.getPublishedStories();
    }

    @GetMapping("/list/author/{authorId}")
    public BaseResponse<GetStoriesByAuthorIdResponse> getAuthorStories(@PathVariable long authorId){
        return storyService.getStoriesByAuthorId(new GetStoriesByAuthorRequest(authorId));
    }


    @PostMapping("/like")
    public BaseResponse<LikeDTO> addOrRemoveLike(@RequestBody AddRemoveLikeRequest request){
        return storyService.addOrRemoveLike(request);
    }

    @GetMapping("/retrieve/{storyId}")
    public BaseResponse<StoryAuthorDTO> getStory(@PathVariable long storyId){
        return storyService.getStory(storyId);
    }
}
