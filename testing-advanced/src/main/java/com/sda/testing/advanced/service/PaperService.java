package com.sda.testing.advanced.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.testing.advanced.dto.PaperMapper;
import com.sda.testing.advanced.dto.PaperRequest;
import com.sda.testing.advanced.dto.PaperResponse;
import com.sda.testing.advanced.model.Paper;
import com.sda.testing.advanced.repository.PaperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    private static final Logger log = LoggerFactory.getLogger(PaperService.class);
    private final PaperRepository paperRepository;
    private final PaperMapper paperMapper;
    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    public PaperService(PaperRepository paperRepository, PaperMapper paperMapper, ObjectMapper jacksonObjectMapper) {
        this.paperRepository = paperRepository;
        this.paperMapper = paperMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
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
        log.debug("find all papers...");
        List<Paper> papers = paperRepository.findAll();
        return paperMapper.toDto(papers);
    }

    public PaperResponse findById(Long id) {
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("paper not found"));
        return paperMapper.toDto(paper);
    }

    public List<PaperResponse> findByAuthor(String author) {
        List<Paper> papers = paperRepository.findByAuthor(author);
        return paperMapper.toDto(papers);
    }

    public PaperResponse update(Long id, PaperRequest request) {
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("paper not found"));

        updateFields(request, paper);

        Paper savedPaper = paperRepository.save(paper);

        return paperMapper.toDto(savedPaper);
    }

    private void updateFields(PaperRequest request, Paper paper) {
        paper.setTitle(request.getTitle());
        paper.setAuthor(request.getAuthor());
        paper.setPublished(request.getPublished());
    }

    public void delete(Long id) {
        paperRepository.deleteById(id);
    }

    public PaperResponse partialUpdate(Long id, PaperRequest updates) {
        try {
            Paper paper = paperRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("paper not found"));

            // Jackson deserializes and copies value to the already initialized DTO
            jacksonObjectMapper.readerForUpdating(paper)
                    .readValue(jacksonObjectMapper.writeValueAsBytes(updates));

            Paper updatedPaper = paperRepository.save(paper);

            return paperMapper.toDto(updatedPaper);
        } catch (Exception e) {
            log.error("could not patch paper with id: {} and updates: {}", id, updates);
        }
        return null;
    }
}
