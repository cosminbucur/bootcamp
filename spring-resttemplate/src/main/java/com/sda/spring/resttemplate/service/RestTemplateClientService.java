package com.sda.spring.resttemplate.service;

import com.sda.spring.resttemplate.dto.PaperRequest;
import com.sda.spring.resttemplate.dto.PaperResponse;
import com.sda.spring.resttemplate.dto.PaperResponseList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class RestTemplateClientService {

    // check url
    private static final String BOOKS_RESOURCE = "http://localhost:8081/papers";
    private static final Logger log = LoggerFactory.getLogger(RestTemplateClientService.class);

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateClientService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    // CRUD

    // POST http://localhost:8081/papers body: paper
    public PaperResponse save(PaperRequest request) {
        log.info("rest template create");
        return restTemplate.postForObject(BOOKS_RESOURCE, request, PaperResponse.class);
    }

    // GET http://localhost:8081/papers
    public List<PaperResponse> findAll() {
        log.info("rest template find all");

        ResponseEntity<PaperResponse[]> response =
                restTemplate.getForEntity(
                        BOOKS_RESOURCE,
                        PaperResponse[].class);
        PaperResponse[] responses = response.getBody();
        return Arrays.asList(responses);
    }

    // TIP: good practice is to wrap lists in object
    // GET http://localhost:8081/papers
    public PaperResponseList findAllUsingWrapperClass() {
        log.info("rest template find all");
        return restTemplate.getForObject(BOOKS_RESOURCE, PaperResponseList.class);
    }

    // GET http://localhost:8081/papers/{paperId}
    public PaperResponse findById(Long paperId) {
        log.info("rest template find by id");
        return restTemplate.getForObject(BOOKS_RESOURCE + "/" + paperId, PaperResponse.class);
    }

    // PUT http://localhost:8081/papers body: paper
    public void update(Long id, PaperRequest request) {
        log.info("rest template update");
        restTemplate.put(BOOKS_RESOURCE + "/" + id, request);
    }

    // DELETE http://localhost:8081/papers/{paperId}
    public void delete(Long paperId) {
        log.info("rest template delete");

        // can use map to set params
        Map<String, Long> params = Map.of("paperId", paperId);
        restTemplate.delete(BOOKS_RESOURCE + "/" + paperId, params);
    }

}
