package com.communityhub.poll_service.presentation.dto;

public class VoteDTO {
    private String userId;
    private String optionId;

    public VoteDTO(String userId, String optionId) {
        this.userId = userId;
        this.optionId = optionId;
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
