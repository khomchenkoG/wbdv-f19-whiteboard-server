package com.example.wbdvf19widgetserver.controllers;

import com.example.wbdvf19widgetserver.models.*;
import com.example.wbdvf19widgetserver.models.Module;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")

public class CourseController {

    List<Course> courses = new ArrayList<>();

    {

        Course course1 = new Course("CS3500: Object Oriented design", "3245674", new ArrayList<>());
        Course course2 = new Course("CS2500: Fundamentals of Computer Science 1", "5559011", new ArrayList<>());
        Module module1 = new Module("Module 1", "5671992", new ArrayList<>());
        Module module2 = new Module("Module 2", "4901640", new ArrayList<>());
        Module module3 = new Module("Module 1.1", "4901640", new ArrayList<>());
        Lesson lesson1 = new Lesson("Lesson 1", "5678943", new ArrayList<>());
        Lesson lesson2 = new Lesson("Lesson 2", "4333301", new ArrayList<>());
        Lesson lesson4 = new Lesson("Lesson 1.1", "2299880", new ArrayList<>());
        Topic topic1 = new Topic("Topic 1", "34454");
        Topic topic2 = new Topic("Topic 2", "37854");
        Topic topic3 = new Topic("Topic 3", "18921");
        Topic topic4 = new Topic("Topic 4", "85941");
        Topic topic5 = new Topic("Topic 5", "09121");
        Topic topic6 = new Topic("Topic 6", "83421");
        Topic topic7 = new Topic("Topic 1.1", "83421");


        lesson1.addTopic(topic1);
        lesson1.addTopic(topic2);
        lesson1.addTopic(topic3);
        lesson2.addTopic(topic4);
        lesson2.addTopic(topic5);
        lesson2.addTopic(topic6);
        module1.addLesson(lesson1);
        module1.addLesson(lesson2);
        course1.addModule(module1);
        course1.addModule(module2);

        lesson4.addTopic(topic7);
        module3.addLesson(lesson4);
        course2.addModule(module3);

        courses.add(course1);
        courses.add(course2);


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
