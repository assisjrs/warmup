package com.assisjrs.cloud.documentos;

import com.assisjrs.cloud.documentos.upload.StorageFileNotFoundException;
import com.assisjrs.cloud.documentos.upload.StorageService;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class DownloadDeve {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storage;

    @Ignore
    @Test
    public void shouldListAllFiles() throws Exception {
        given(storage.loadAll())
                .willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));

        mvc.perform(get("/")).andExpect(status().isOk())
           .andExpect(model().attribute("files", Matchers.contains("http://localhost/files/first.txt", "http://localhost/files/second.txt")));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void enviar404QuandoNaoEncontrarOArquivo() throws Exception {
        given(storage.loadAsResource("test.txt"))
       .willThrow(StorageFileNotFoundException.class);

        mvc.perform(get("/files/test.txt"))
           .andExpect(status().isNotFound());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void enviar200QuandoEncontrarOArquivo() throws Exception {
        given(storage.loadAsResource("test.txt"))
       .willReturn(new ByteArrayResource(new byte[]{}));

        mvc.perform(get("/files/test.txt"))
           .andExpect(status().isOk());
    }
}
