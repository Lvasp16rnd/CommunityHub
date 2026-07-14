package com.communityhub.poll_service.domain;

import java.util.List;
import java.util.UUID;

public class PollOption {

    private String id;
    private String text;
    private int voteCount;

    public PollOption( String text) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.voteCount = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

}
