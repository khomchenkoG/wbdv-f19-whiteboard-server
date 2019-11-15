package com.example.wbdvf19widgetserver.controllers;

import com.example.wbdvf19widgetserver.models.*;
import com.example.wbdvf19widgetserver.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")

public class CourseController {

    List<Course> courses = new ArrayList<>();

    @Autowired
    CourseRepository repository;




    @GetMapping("/api/courses")
    public List<Course> findAllCourses() {
        return repository.findAllCourses();
    }

    @GetMapping("/api/courses/{courseId}")
    public Course findCourseById( @PathVariable("courseId") Integer courseId) {
       return repository.findCourseById(courseId);
    }

    @PostMapping("/api/courses")
    public List<Course> createCourse(
            @RequestBody Course course) {
        repository.save(course);
        return repository.findAllCourses();
    }

    @DeleteMapping("/api/courses/{courseId}")
    public List<Course> deleteCourse(
            @PathVariable("courseId") Integer courseId) {
        repository.deleteById(courseId);
        return repository.findAllCourses();
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
