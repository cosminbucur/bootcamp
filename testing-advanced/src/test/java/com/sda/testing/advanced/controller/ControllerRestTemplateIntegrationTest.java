package com.sda.testing.advanced.controller;

import com.sda.testing.advanced.SpringTestingApplication;
import com.sda.testing.advanced.config.H2TestProfileJpaConfig;
import com.sda.testing.advanced.dto.PaperRequest;
import com.sda.testing.advanced.dto.PaperResponse;
import com.sda.testing.advanced.service.PaperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {SpringTestingApplication.class, H2TestProfileJpaConfig.class}
)
class ControllerRestTemplateIntegrationTest {

    @LocalServerPort
    static final int port = 8083;
    static final String API_BOOKS = "/papers";
    static final String BASE_URI = "http://localhost:" + port + "/api";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PaperService paperService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() {
    }

    @Test
    void givenRequest_whenFindAll_thenReturn200() {
        PaperRequest request = new PaperRequest();
        request.setTitle("game of thrones");
        request.setAuthor("george martin");
        request.setPublished(LocalDate.of(2000, 6, 30));
        paperService.save(request);

        ResponseEntity<PaperResponse[]> response = restTemplate.getForEntity(
                BASE_URI + API_BOOKS,
                PaperResponse[].class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").get(0)).isEqualTo("application/json");
        assertThat(response.getBody()[0].getTitle()).isEqualTo("game of thrones");
    }

    @Test
    void findById() {
    }

    @Test
    void getPapersByAuthor() {
    }

    @Test
    void update() {
    }

    @Test
    void partialUpdate() {
    }

    @Test
    void delete() {
    }
}