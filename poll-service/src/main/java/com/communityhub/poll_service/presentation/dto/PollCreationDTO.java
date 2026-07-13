package com.communityhub.poll_service.presentation.dto;

import java.util.List;

public class PollCreationDTO {

    private String title;
    private String description;
    List<String> options;

    public PollCreationDTO(String title, String description, List<String> options) {
        this.title = title;
        this.description = description;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
