package com.communityhub.poll_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "polls")
public class Poll {
    @Id
    private String id;
    private String title;
    private String description;

    List<PollOption> options = new ArrayList<>();

    public Poll(String id, String title, String description, List<PollOption> options) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<PollOption> getOptions() {
        return options;
    }

    public void setOptions(List<PollOption> options) {
        this.options = options;
    }

}
