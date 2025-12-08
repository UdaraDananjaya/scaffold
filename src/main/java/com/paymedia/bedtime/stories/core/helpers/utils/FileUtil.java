package com.paymedia.bedtime.stories.core.helpers.utils;

import com.paymedia.bedtime.stories.core.dtos.files.FileMetaDTO;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

public class FileUtil {
    private static final String UPLOAD_DIRECTORY ="uploads/";

    public FileMetaDTO getFileMeta(MultipartFile file) {
        final FileMetaDTO fileMeta = new FileMetaDTO();

        String fileName = UUID
                .randomUUID()+"-"+ StringUtils.cleanPath(
                        file.getOriginalFilename() != null? file.getOriginalFilename() :
                                TimeUtil.getCurrentTimestamp());
        String filePath = UPLOAD_DIRECTORY + fileName;
        String fileKey = Base64.getEncoder().encodeToString(filePath .getBytes());

        fileMeta.setFileKey(fileKey);
        fileMeta.setFileName(fileName);
        fileMeta.setFilePath(filePath);

        return fileMeta;
    }


    public String getDecodedFileKey(String fileKey) {
        return  new String(Base64.getDecoder().decode(fileKey));
    }


    public Path getDecodedFileKeyAsPath(String fileKey) {
        return Paths.get(new String(Base64.getDecoder().decode(fileKey))).toAbsolutePath().normalize();
    }
}
