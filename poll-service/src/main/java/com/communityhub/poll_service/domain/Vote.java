package com.communityhub.poll_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "votes")
@CompoundIndex(name = "poll_user_idx", def = "{'pollId': 1, 'userId': 1}", unique = true)
public class Vote {

    @Id
    private String id;
    private String pollId;
    private String userId;
    private String optionId;

    public Vote(String id, String pollId, String userId, String optionId) {
        this.id = id;
        this.pollId = pollId;
        this.userId = userId;
        this.optionId = optionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }
}
