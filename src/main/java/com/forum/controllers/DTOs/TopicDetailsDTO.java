package com.forum.controllers.DTOs;

import com.forum.models.Topic;
import com.forum.models.TopicalStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDetailsDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime createDate;
    private String authorName;
    private TopicalStatus topicalStatus;
    private List<ResponseDTO> responses;

    public TopicDetailsDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createDate = topic.getCreateDate();
        this.authorName = topic.getAuthor().getName();
        this.topicalStatus = topic.getStatus();
        this.responses = new ArrayList<>();
        this.responses.addAll(topic.getResponses().stream().map(ResponseDTO::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public TopicalStatus getTopicalStatus() {
        return topicalStatus;
    }

    public List<ResponseDTO> getResponses() {
        return responses;
    }
}
