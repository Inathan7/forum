package com.forum.controllers;

import com.forum.controllers.DTOs.TopicDTO;
import com.forum.controllers.DTOs.TopicDetailsDTO;
import com.forum.controllers.forms.TopicForm;
import com.forum.controllers.forms.TopicFormUpdate;
import com.forum.models.Topic;
import com.forum.repositories.CourseRepository;
import com.forum.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    @Cacheable(value = "listTopics")
    public Page<TopicDTO> listTopics(@RequestParam(required = false) String courseName,
                                     @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pagination) {

        Page<Topic> topics;
        if(courseName==null) {
            topics = topicRepository.findAll(pagination);
        }else {
            topics = topicRepository.findByCourseName(courseName, pagination);
        }
        return TopicDTO.toConvert(topics);

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<TopicDTO> register(@RequestBody @Valid TopicForm topicForm, UriComponentsBuilder uriBuilder) {
        Topic topic = topicForm.toConvert(courseRepository);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDTO(topic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDetailsDTO> TopicDetails(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if(topic.isPresent()) {
            return ResponseEntity.ok(new TopicDetailsDTO(topic.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<TopicDTO> update(@PathVariable Long id, @RequestBody @Valid TopicFormUpdate topicFormUpdate) {
        Optional<Topic> optional = topicRepository.findById(id);

        if(optional.isPresent()) {
            Topic topic = topicFormUpdate.update(id, topicRepository);
            return ResponseEntity.ok(new TopicDTO(topic));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listTopics", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Topic> topic = topicRepository.findById(id);

        if(topic.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
