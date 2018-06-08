package com.assisjrs.cloud.documentos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentosApplicationTests {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    public void isAvailable() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity(url("/health"), Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    private String url(final String context){
        return "http://localhost:" + port + context;
    }
}
