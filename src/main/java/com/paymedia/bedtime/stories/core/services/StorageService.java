package com.paymedia.bedtime.stories.core.services;

import com.paymedia.bedtime.stories.core.dtos.files.FileMetaDTO;
import com.paymedia.bedtime.stories.core.helpers.utils.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

    private final FileUtil fileUtil;
    private final Logger logger = LoggerFactory.getLogger(StorageService.class);

    public StorageService() {
        this.fileUtil = new FileUtil();
    }


    public FileMetaDTO saveFile(MultipartFile file) throws FileUploadException {
        try{
            FileMetaDTO fileMetaDto = fileUtil.getFileMeta(file);

            // create directory if not exist
            File newPath = new File(fileMetaDto.getFilePath().toString());
            if(!newPath.exists()) {
                boolean result = newPath.mkdirs();
                logger.info("saveFile - new directory creation result: %b" ,result);
            }

            // copy new file
            Path filePath = fileMetaDto.getFilePath();
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileMetaDto;
        }
        catch(Exception e){
            logger.error("saveFile() - Exception occurred: ");
            logger.error(e.getMessage());
            throw new FileUploadException(e.getMessage());
        }
    }


    public byte[] getFileAsBytes(String key){
        try{

            Path filePath = fileUtil.getDecodedFileKeyAsPath(key);
            return Files.readAllBytes(filePath);
        }
        catch (Exception e){
            logger.error("getFileAsBytes() - Exception occurred: ");
            logger.error(e.getMessage());
            return new byte[0];
        }
    }


    public StreamingResponseBody getFileAsStream(String key){
        try{
            return outputStream -> {

                String filePath = fileUtil.getDecodedFileKey(key);
                InputStream inputStream = new FileInputStream(new File(filePath));

                byte[] buffer = new byte[2048];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
            };
        }
        catch (Exception e){
            logger.error("getFileAsStream() - Exception occurred: ");
            logger.error(e.getMessage());
            return outputStream -> {};
        }
    }

}
