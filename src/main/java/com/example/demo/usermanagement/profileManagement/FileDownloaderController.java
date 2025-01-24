package com.example.demo.usermanagement.profileManagement;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class FileDownloaderController {

    @Operation(summary = "Retrieve an image", description = "Retrieves an image file based on the user ID")
    @GetMapping("/{userId}")
    public ResponseEntity<byte[]> getImage(
            @Parameter(description = "The UUID of the user to identify the file", required = true, schema = @Schema(implementation = UUID.class))
            @PathVariable UUID userId) {
        try {
            String filename = userId.toString();
            Path uploadDir = Paths.get(System.getProperty("user.dir") + "/uploads");
            Optional<Path> matchedFile = Files.walk(uploadDir)
                    .filter(path -> path.getFileName().toString().startsWith(filename))
                    .findFirst();

            if (matchedFile.isPresent()) {
                Path filePath = matchedFile.get();
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists() || resource.isReadable()) {
                    byte[] fileContent = Files.readAllBytes(filePath);
                    String contentType = determineContentType(filePath);
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                            .contentType(MediaType.parseMediaType(contentType))
                            .body(fileContent);
                } else {
                    throw new RuntimeException("Could not read the file!");
                }
            } else {
                throw new RuntimeException("File not found!");
            }
        } catch (Exception e) {
            // Return error details as text in the response body
            String error = "Could not deliver the file. Error: " + e.getMessage();
            return ResponseEntity.badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(error.getBytes());
        }
    }

    private String determineContentType(Path path) {
        try {
            return Files.probeContentType(path);
        } catch (IOException e) {
            return "application/octet-stream"; // Default MIME type if detection fails
        }
    }
}
