package com.forum.controllers.DTOs;

import com.forum.models.Topic;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicDTO {

    private Long id;
    private String title;
    private String message;
    private LocalDateTime createDate;

    public TopicDTO(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.message = topic.getMessage();
        this.createDate = topic.getCreateDate();
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

    public static Page<TopicDTO> toConvert(Page<Topic> topics) {
        return topics.map(TopicDTO::new);
    }

}
