package com.sda.spring.boot.rest.server.service;

import com.sda.spring.boot.rest.server.dto.PaperMapper;
import com.sda.spring.boot.rest.server.dto.PaperRequest;
import com.sda.spring.boot.rest.server.dto.PaperResponse;
import com.sda.spring.boot.rest.server.exception.DuplicatePaperException;
import com.sda.spring.boot.rest.server.exception.NotFoundException;
import com.sda.spring.boot.rest.server.model.Paper;
import com.sda.spring.boot.rest.server.repository.PaperRepository;
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

    @Autowired
    public PaperService(PaperRepository paperRepository, PaperMapper paperMapper) {
        this.paperRepository = paperRepository;
        this.paperMapper = paperMapper;
    }

    // CRUD

    public PaperResponse save(PaperRequest request) {
        log.debug("saving paper {}", request);

        Paper existingPaper = paperRepository.findByTitle(request.getTitle());
        if (existingPaper != null) {
            throw new DuplicatePaperException("paper already exists");
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
        log.debug("find author by id: {}", id);

        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("paper not found"));
        return paperMapper.toDto(paper);
    }

    public List<PaperResponse> findByAuthor(String author) {
        log.debug("find papers by author: {}", author);

        List<Paper> papers = paperRepository.findByAuthor(author);
        return paperMapper.toDto(papers);
    }

    public PaperResponse update(Long id, PaperRequest request) {
        log.debug("updating paper id: {} and request body : {}", id, request);

        // search entity to update
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("paper not found"));

        // update data
        Paper updatedPaper = paperMapper.toEntity(paper, request);

        // save updated entity
        Paper savedPaper = paperRepository.save(updatedPaper);

        // convert to dto
        return paperMapper.toDto(savedPaper);
    }

    public void delete(Long id) {
        log.debug("deleting author with id: {}", id);

        paperRepository.deleteById(id);
    }

}
