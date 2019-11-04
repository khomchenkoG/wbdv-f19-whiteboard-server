package com.example.wbdvf19widgetserver.controllers;

import com.example.wbdvf19widgetserver.models.*;
import com.example.wbdvf19widgetserver.models.Module;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")

public class CourseController {

    ObjectMapper mapper = new ObjectMapper();

    List<Course> courses = new ArrayList<>();

    {

        Course course1 = new Course("CS3500: Object Oriented design", "3245674", new ArrayList<>());
        Module module1 = new Module("Module 1", "567199", new ArrayList<>());
        Lesson lesson1 = new Lesson("Lesson 1", "5678943", new ArrayList<>());
        Topic topic1 = new Topic("Topic 1", "34454");

        lesson1.addTopic(topic1);
        module1.addLesson(lesson1);
        course1.addModule(module1);
        courses.add(course1);
    }

    public CourseController() throws JsonProcessingException {
    }

    @GetMapping("/api/courses")
    public List<Course> findAllCourses() {
        return courses;
    }

    @GetMapping("/api/courses/{courseId}")
    public Course findCourseById( @PathVariable("courseId") String courseId) {
        for (Course c: courses){
            if (c.getId().equals(courseId)){
                return c;
            }
        }
        return null;
    }

    @PostMapping("/api/courses")
    public List<Course> createCourse(
            @RequestBody Course course) {
        course.setId(UUID.randomUUID().toString());
        courses.add(course);
        return courses;
    }

    @DeleteMapping("/api/courses/{courseId}")
    public List<Course> deleteCourse(
            @PathVariable("courseId") String courseId) {
        int index = -1;
        for(int i=0; i<courses.size(); i++) {
            if(courses.get(i).getId().equals(courseId)){
                index = i;
            }
        }
        courses.remove(index);
        return courses;
    }

    @PutMapping("/api/courses/{courseId}")
    public List<Course> updateCourse(
            @PathVariable("courseId") String courseId,
            @RequestBody Course course) {
        int index = -1;
        for(int i=0; i<courses.size(); i++) {
            if(courses.get(i).getId().equals(courseId)){
                index = i;
            }
        }
        courses.set(index, course);
        return courses;
    }

}
