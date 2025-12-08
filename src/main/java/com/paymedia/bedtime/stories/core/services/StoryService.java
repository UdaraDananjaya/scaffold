package com.paymedia.bedtime.stories.core.services;

import com.paymedia.bedtime.stories.core.dtos.files.FileMetaDTO;
import com.paymedia.bedtime.stories.core.dtos.requests.stories.AddRemoveLikeRequest;
import com.paymedia.bedtime.stories.core.dtos.requests.stories.CreateDraftStoryRequest;
import com.paymedia.bedtime.stories.core.dtos.requests.stories.GetStoriesByAuthorRequest;
import com.paymedia.bedtime.stories.core.dtos.responses.BaseResponse;
import com.paymedia.bedtime.stories.core.dtos.responses.stories.GetPublishedStoriesResponse;
import com.paymedia.bedtime.stories.core.dtos.responses.stories.GetStoriesByAuthorIdResponse;
import com.paymedia.bedtime.stories.core.dtos.stories.LikeDTO;
import com.paymedia.bedtime.stories.core.dtos.stories.StoryAuthorDTO;
import com.paymedia.bedtime.stories.core.dtos.stories.StoryDTO;
import com.paymedia.bedtime.stories.core.entities.StoryEntity;
import com.paymedia.bedtime.stories.core.constants.enums.CommonResponse;
import com.paymedia.bedtime.stories.core.repositories.AuthorRepository;
import com.paymedia.bedtime.stories.core.repositories.StoryRepository;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;
    private final AuthorRepository authorRepository;
    private final StorageService storageService;
    private final Logger logger = LoggerFactory.getLogger(StoryService.class);

    public StoryService(StoryRepository storyRepository, StorageService storageService, AuthorRepository authorRepository) {
        this.storyRepository = storyRepository;
        this.storageService = storageService;
        this.authorRepository = authorRepository;
    }

    public BaseResponse<GetStoriesByAuthorIdResponse> getStoriesByAuthorId(GetStoriesByAuthorRequest request) {
        try {
            List<StoryDTO> stories = storyRepository.findByAuthorId(request.getAuthorId())
                    .stream().map(se -> new StoryDTO(se)).toList();

            return new BaseResponse<>(HttpStatus.OK, new GetStoriesByAuthorIdResponse(stories));
        }
        catch (Exception e) {
            logger.error("getStoriesByAuthorId() - Exception occurred");
            logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR);
        }
    }


    public BaseResponse<GetPublishedStoriesResponse> getPublishedStories(){
        try {
            return new BaseResponse<>(HttpStatus.OK, new GetPublishedStoriesResponse(storyRepository.findAllPublishedWithAuthors()));
        }
        catch (Exception e) {
            logger.error("getPublishedStories() - Exception occurred");
            logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error. refer to the internal server logs");
        }
    }


    public BaseResponse<StoryAuthorDTO> getStory(long id){
        try {
            StoryAuthorDTO storyAuthor = storyRepository.findByIdWithAuthor(id);
            if (storyAuthor != null) return new BaseResponse<>(HttpStatus.OK, storyAuthor);
            return new BaseResponse<>(HttpStatus.OK, CommonResponse.RECORD_NOT_FOUND);
        }
        catch (Exception e) {
            logger.error("getStory() - Exception occurred");
            logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR);
        }
    }


    public BaseResponse<LikeDTO> addOrRemoveLike(AddRemoveLikeRequest request){
        try {
            BaseResponse<LikeDTO> response;

            // check for validity
            StoryEntity story =storyRepository.findById(request.getStoryId()).orElse(null);
            if(story != null){

                if(request.isLiked() || story.getLikes()>0){

                    if(request.isLiked()){
                        story.setLikes(story.getLikes() + 1);
                    }
                    else{
                        story.setLikes(story.getLikes() - 1);
                    }

                    storyRepository.save(story);
                    response = new BaseResponse<>(HttpStatus.OK, new LikeDTO(story.getLikes()));
                }
                else{
                    response = new BaseResponse<>(HttpStatus.BAD_REQUEST, "unable to reduce likes");
                }
            }
            else{
                response = new BaseResponse<>(HttpStatus.BAD_REQUEST, CommonResponse.RECORD_NOT_FOUND);
            }

            return response;
        }
        catch (Exception e) {
            logger.error("addOrRemoveLike() - Exception occurred");
            logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR);
        }
    }


    public BaseResponse<String> createDraftStory(CreateDraftStoryRequest request) {

        try {

            BaseResponse<String> response;

            // update existing record if record exists, otherwise add new record
            if(request.getId() != null && storyRepository.findById(request.getId()).isPresent()) {
                response = this.updateStoryRecord(request);
            }
            else{
                // add new record
                if(request.getTitle() != null){
                    response = this.createStoryRecord(request);
                }
                else{
                    response = new BaseResponse<>(HttpStatus.BAD_REQUEST, "Story title is required");
                }
            }

            return response;
        }
        catch (Exception e) {
            logger.error("createDraftStory() - Exception occurred");
            logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR);
        }
    }


    private BaseResponse<String> updateStoryRecord(CreateDraftStoryRequest request) throws FileUploadException {
        // update existing record
        StoryEntity existingStory = storyRepository.findById(request.getId()).orElseThrow();

        if(request.getTitle() != null) existingStory.setTitle(request.getTitle());
        if(request.getBody() != null) existingStory.setBody(request.getBody());
        if(request.getThumbnail() != null){
            FileMetaDTO fileMetaDTO = storageService.saveFile(request.getThumbnail());
            existingStory.setThumbnailKey(fileMetaDTO.getFileKey());
        }
        existingStory.setPublished(request.getIsPublished());
        storyRepository.save(existingStory);

        return new BaseResponse<>(HttpStatus.OK, "Successfully updated the story");
    }


    private BaseResponse<String> createStoryRecord(CreateDraftStoryRequest request) throws FileUploadException {

       BaseResponse<String> response;

        if(authorRepository.findById(request.getAuthorId()).isPresent()){
            StoryEntity newStory = new StoryEntity();
            newStory.setTitle(request.getTitle());
            newStory.setBody(request.getBody());
            newStory.setPublished(request.getIsPublished());
            newStory.setLikes(0);
            newStory.setAuthorId(request.getAuthorId());

            if(request.getThumbnail() != null){
                FileMetaDTO fileMetaDTO = storageService.saveFile(request.getThumbnail());
                newStory.setThumbnailKey(fileMetaDTO.getFileKey());
            }

            storyRepository.save(newStory);
            response = new BaseResponse<>(HttpStatus.OK, "Successfully created the story");
        }
        else{
            response = new BaseResponse<>(HttpStatus.BAD_REQUEST, "Author does not exist");
        }

        return response;
    }
}
