package com.paymedia.bedtime.stories.core.dtos.files;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMetaDTO {
    @Getter @Setter
    private String fileName;

    @Setter
    private String filePath;

    @Getter @Setter
    private String fileKey;

    public Path getFilePath() {
        return Paths.get(this.filePath).toAbsolutePath().normalize();
    }
}
