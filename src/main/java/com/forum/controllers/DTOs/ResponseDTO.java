package com.forum.controllers.DTOs;

import com.forum.models.Response;
import com.forum.models.Topic;

import java.time.LocalDateTime;

public class ResponseDTO {

    private Long id;
    private String message;
    private LocalDateTime createDate;
    private String authorName;

    public ResponseDTO(Response response) {
        this.id = response.getId();
        this.message = response.getMessage();
        this.createDate = response.getCreateDate();
        this.authorName = response.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getAuthorName() {
        return authorName;
    }
}
