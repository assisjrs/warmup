package com.assisjrs.cloud.documentos;

import com.assisjrs.cloud.documentos.upload.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UploadDeve {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storage;

    @Test
    public void salvarOArquivo() throws Exception {
        final MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "Spring Framework".getBytes());
        mvc.perform(fileUpload("/").file(multipartFile))
           .andExpect(status().isFound())
           .andExpect(header().string("Location", "/"));

        then(storage).should()
                     .store(multipartFile);
    }
}
