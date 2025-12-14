package lk.gtsactive.scaffold.util.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

public final class FileUtils {

    public static String generateSafeFileName(String originalFilename) {
        String extension = getExtension(originalFilename);
        return UUID.randomUUID() + (extension.isEmpty() ? "" : "." + extension);
    }

    public static String getExtension(String filename) {
        if (filename == null) return "";
        int lastDot = filename.lastIndexOf('.');
        return (lastDot == -1) ? "" : filename.substring(lastDot + 1);
    }

    public static void validateExtension(
            String filename,
            Set<String> allowedExtensions
    ) {
        String ext = getExtension(filename).toLowerCase();
        if (!allowedExtensions.contains(ext)) {
            throw new IllegalArgumentException("File type not allowed: " + ext);
        }
    }

    public static void validateSize(long size, long maxSizeBytes) {
        if (size > maxSizeBytes) {
            throw new IllegalArgumentException(
                    "File size exceeds limit: " + maxSizeBytes + " bytes"
            );
        }
    }

    public static Path save(
            MultipartFile file,
            Path directory
    ) throws IOException {

        if (Files.notExists(directory)) {
            Files.createDirectories(directory);
        }

        String safeName = generateSafeFileName(file.getOriginalFilename());
        Path target = directory.resolve(safeName);

        Files.copy(
                file.getInputStream(),
                target,
                StandardCopyOption.REPLACE_EXISTING
        );

        return target;
    }

    public static void delete(Path path) throws IOException {
        Files.deleteIfExists(path);
    }

    public static Path resolveSafe(Path baseDir, String filename) {
        Path resolved = baseDir.resolve(filename).normalize();
        if (!resolved.startsWith(baseDir)) {
            throw new SecurityException("Invalid file path");
        }
        return resolved;
    }
}
