package com.assisjrs.cloud.documentos;

import com.assisjrs.cloud.documentos.upload.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageServiceDeve {

    @Autowired
    private StorageService storage;

    @Test
    public void salvarOArquivoNoDisco() throws Exception
    {
        final MultipartFile file = mock(MultipartFile.class);

        when(file.getName()).thenReturn("salvarOArquivoNoDisco.txt");
        when(file.getOriginalFilename()).thenReturn("salvarOArquivoNoDisco.txt");
        when(file.getBytes()).thenReturn(new byte[]{0x65});

        storage.store(file);

        assertTrue(new File("./" + file.getName()).exists());
    }

    @Test
    public void lerOArquivoDoDisco() throws Exception
    {
        final Path path = Paths.get("./lerOArquivoDoDisco.txt");
        Files.deleteIfExists(path);

        Files.createFile(path);
        Files.write(path, new byte[]{0x60});

        final Resource resource = storage.loadAsResource("lerOArquivoDoDisco.txt");

        assertTrue(resource.exists());
    }
}
