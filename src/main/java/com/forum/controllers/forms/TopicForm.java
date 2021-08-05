package com.forum.controllers.forms;

import com.forum.models.Course;
import com.forum.models.Topic;
import com.forum.repositories.CourseRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class TopicForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String message;

    @NotNull
    @NotEmpty
    private String nameCourse;

    public Topic toConvert(CourseRepository courseRepository) {
        Course course = courseRepository.findByName(nameCourse);
        return new Topic(title, message, course);
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getNameCourse() {
        return nameCourse;
    }
}
