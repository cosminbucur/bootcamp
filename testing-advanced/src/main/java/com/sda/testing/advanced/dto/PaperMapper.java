package com.sda.testing.advanced.dto;

import com.sda.testing.advanced.model.Paper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaperMapper {

    public List<PaperResponse> toDto(List<Paper> papers) {
        return papers.stream()       // convert list to stream
                .map(this::toDto)   // convert entity to dto
                .collect(Collectors.toList());  // convert stream to list
    }

    public PaperResponse toDto(Paper paper) {
        PaperResponse dto = new PaperResponse();
        dto.setId(paper.getId());
        dto.setTitle(paper.getTitle());
        dto.setAuthor(paper.getAuthor());
        dto.setPublished(paper.getPublished());
        return dto;
    }

    public List<Paper> toEntity(List<PaperRequest> paperRequests) {
        List<Paper> papers = new ArrayList<>();
        for (PaperRequest paperRequest : paperRequests) {
            papers.add(toEntity(paperRequest));
        }
        return papers;
    }

    public Paper toEntity(PaperRequest paperRequest) {
        Paper entity = new Paper();
        entity.setTitle(paperRequest.getTitle());
        entity.setAuthor(paperRequest.getAuthor());
        entity.setPublished(paperRequest.getPublished());
        return entity;
    }

}
