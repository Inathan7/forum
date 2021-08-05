package com.forum.repositories;

import com.forum.models.Course;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findByNameTest() {
        String courseName = "HTML 5";
        Course course = courseRepository.findByName(courseName);
        Assert.assertNotNull(courseName);
        Assert.assertEquals(courseName, course.getName());
    }

    @Test
    public void notFindByNameTest() {
        String courseName = "JPA";
        Course course = courseRepository.findByName(courseName);
        Assert.assertNull(course);
    }

}
