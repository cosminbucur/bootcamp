package com.sda.spring.boot.rest.server.service;

import com.sda.spring.boot.rest.server.dto.PaperMapper;
import com.sda.spring.boot.rest.server.dto.PaperRequest;
import com.sda.spring.boot.rest.server.dto.PaperResponse;
import com.sda.spring.boot.rest.server.model.Paper;
import com.sda.spring.boot.rest.server.repository.PaperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperAdvancedService {

    private static final Logger log = LoggerFactory.getLogger(PaperAdvancedService.class);
    private final PaperRepository paperRepository;
    private final PaperMapper paperMapper;

    @Autowired
    public PaperAdvancedService(PaperRepository paperRepository, PaperMapper paperMapper) {
        this.paperRepository = paperRepository;
        this.paperMapper = paperMapper;
    }

    // CRUD

    public PaperResponse save(PaperRequest request) {
        log.debug("saving paper {}", request);

        Paper existingPaper = paperRepository.findByTitle(request.getTitle());
        if (existingPaper != null) {
            throw new RuntimeException("paper already exists");
        }

        Paper paper = paperMapper.toEntity(request);
        Paper savedPaper = paperRepository.save(paper);
        return paperMapper.toDto(savedPaper);
    }

    public List<PaperResponse> findAll() {
        log.debug("find all papers");

        return paperRepository.findAll().stream()
                .map(paperMapper::toDto)
                .collect(Collectors.toList());
    }

    public PaperResponse findById(Long id) {
        log.debug("find author by id: {}", id);

        return paperRepository.findById(id)
                .map(paperMapper::toDto)
                .orElseThrow(() -> new RuntimeException("paper not found"));
    }

    public List<PaperResponse> findByAuthor(String author) {
        log.debug("find papers by author: {}", author);

        return paperRepository.findByAuthor(author).stream()
                .map(paperMapper::toDto)
                .collect(Collectors.toList());
    }

    public PaperResponse update(Long id, PaperRequest request) {
        log.debug("updating paper id: {} and request body : {}", id, request);

        return paperRepository.findById(id)
                .map(author -> paperMapper.toEntity(author, request))
                .map(paperRepository::save)
                .map(paperMapper::toDto)
                .orElseThrow(() -> new RuntimeException(("paper not found")));
    }

    public void delete(Long id) {
        log.debug("deleting author with id: {}", id);

        paperRepository.deleteById(id);
    }

}
