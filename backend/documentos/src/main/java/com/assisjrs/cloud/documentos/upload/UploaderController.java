package com.assisjrs.cloud.documentos.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@Controller
public class UploaderController {
    @Autowired
    private StorageService storage;

    @PostMapping("/")
    public String upload(final @RequestParam("file") MultipartFile file) throws IOException {
        storage.store(file);

        return "redirect:/";
    }
}
