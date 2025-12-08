package com.paymedia.bedtime.stories.core.controllers;

import com.paymedia.bedtime.stories.core.services.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/bytes/{key}")
    public byte[] getBytes(@PathVariable String key) {
        return this.storageService.getFileAsBytes(key);
    }

    @GetMapping("/stream/{key}")
    public StreamingResponseBody getStream(@PathVariable String key) {
        return this.storageService.getFileAsStream(key);
    }
}
