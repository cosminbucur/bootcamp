package com.sda.spring.resttemplate.dto;

import java.util.List;

public class PaperResponseList {

    private List<PaperResponse> paperResponses;

    public List<PaperResponse> getPaperResponses() {
        return paperResponses;
    }

    public void setPaperResponses(List<PaperResponse> paperResponses) {
        this.paperResponses = paperResponses;
    }
}
