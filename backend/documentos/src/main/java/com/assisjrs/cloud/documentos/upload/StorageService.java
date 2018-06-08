package com.assisjrs.cloud.documentos.upload;

import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageService {
    public Stream<Path> loadAll() {
        return null;
    }

    public Resource loadAsResource(final String filename)
    {
        return new PathResource("./" + filename);
    }

    public void store(final MultipartFile multipartFile) throws IOException
    {
        final byte[] bytes = multipartFile.getBytes();
        final Path filePath = Paths.get("./" + multipartFile.getOriginalFilename());

        Files.deleteIfExists(filePath);

        Files.createFile(filePath);
        Files.write(filePath, bytes);
    }
}
