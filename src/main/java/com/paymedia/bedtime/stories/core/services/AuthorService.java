package com.paymedia.bedtime.stories.core.services;

import com.paymedia.bedtime.stories.core.dtos.authors.AuthorDTO;
import com.paymedia.bedtime.stories.core.dtos.files.FileMetaDTO;
import com.paymedia.bedtime.stories.core.dtos.requests.authors.CreateAuthorRequest;
import com.paymedia.bedtime.stories.core.dtos.responses.BaseResponse;
import com.paymedia.bedtime.stories.core.dtos.responses.authors.ListAuthorsResponse;
import com.paymedia.bedtime.stories.core.entities.AuthorEntity;
import com.paymedia.bedtime.stories.core.constants.enums.CommonResponse;
import com.paymedia.bedtime.stories.core.repositories.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final StorageService storageService;
    private final Logger logger = LoggerFactory.getLogger(AuthorService.class);

    public AuthorService(AuthorRepository authorRepository, StorageService storageService) {
        this.authorRepository = authorRepository;
        this.storageService = storageService;
    }


    public BaseResponse<String> createAuthor(CreateAuthorRequest request){
        try{

            // check same author exists
            AuthorEntity existingAuthor = authorRepository.findByEmail(request.getEmail());

            if(existingAuthor == null){
                // uploading the file
                FileMetaDTO uploadedFileMeta = storageService.saveFile(request.getImage());

                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setFirstName(request.getFirstName());
                authorEntity.setLastName(request.getLastName());
                authorEntity.setEmail(request.getEmail());
                authorEntity.setPassword(request.getPassword());
                authorEntity.setImageKey(uploadedFileMeta.getFileKey());

                authorRepository.save(authorEntity);

                return new BaseResponse<>(HttpStatus.CREATED, "Author created");
            }

            return new BaseResponse<>(HttpStatus.BAD_REQUEST, "Author already exists");
        }
        catch(Exception e){
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR);
        }
    }


    public BaseResponse<ListAuthorsResponse> listAuthors(){
        try {

            List<AuthorDTO> authors = authorRepository.findAll()
                    .stream().map(ae -> new AuthorDTO(ae)).toList();

            return new BaseResponse<>(HttpStatus.OK, new ListAuthorsResponse(authors));
        }
        catch(Exception e){
            this.logger.error(e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, CommonResponse.INTERNAL_SERVER_ERROR);
        }
    }

}
