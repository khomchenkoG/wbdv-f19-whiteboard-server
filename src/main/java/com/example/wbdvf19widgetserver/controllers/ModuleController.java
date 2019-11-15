package com.example.wbdvf19widgetserver.controllers;

import com.example.wbdvf19widgetserver.models.Course;
import com.example.wbdvf19widgetserver.models.Module;
import com.example.wbdvf19widgetserver.repositories.CourseRepository;
import com.example.wbdvf19widgetserver.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ModuleController {

    @Autowired
    ModuleRepository repository;

    @Autowired
    CourseRepository courseRepository;

    // CRUD
    // CREATE   - POST /api/courses/{cid}/modules
    // READ ALL - GET  /api/courses/{cid}/modules - retrieve all courses
    // READ ONE - GET  /api/modules/{mid} - retrieve by id
    // UPDATE   - PUT  /api/modules/{mid} - update course
    // DELETE   - DELETE /api/modules/{mid}

    @GetMapping("/api/modules")
    public List<Module> findAllModules() {
        return repository.findAllModules();
    }

    @GetMapping("/api/courses/{cid}/modules")
    public List<Module> findAllModulesForCourse(
            @PathVariable("cid") Integer courseId) {
        return repository.findAllModulesForCourse(courseId);
    }

    @DeleteMapping("/api/courses/{cid}/modules/{mid}")
    public List<Module> findAllModulesForCourse(
            @PathVariable("cid") Integer courseId,
            @PathVariable("mid") Integer moduleId) {
        repository.deleteById(moduleId);
        return repository.findAllModulesForCourse(courseId);
    }


    @PostMapping("/api/courses/{cid}/modules")
    public List<Module> addModuleToCourse(
            @PathVariable("cid") Integer courseId,
            @RequestBody Module newModule
    ) {
        Course course = courseRepository.findCourseById(courseId);
        newModule.setCourse(course);
        repository.save(newModule);
        return repository.findAllModulesForCourse(courseId);
    }

    @GetMapping("/api/modules/{mid}")
    public Module findModuleById(@PathVariable("mid") Integer id) {
        return repository.findModuleById(id);
    }
}
