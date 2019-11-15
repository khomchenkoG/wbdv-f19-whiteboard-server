package com.example.wbdvf19widgetserver.controllers;

import com.example.wbdvf19widgetserver.models.Course;
import com.example.wbdvf19widgetserver.models.Lesson;
import com.example.wbdvf19widgetserver.models.Module;
import com.example.wbdvf19widgetserver.repositories.LessonRepository;

import com.example.wbdvf19widgetserver.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class LessonController {
    @Autowired
    LessonRepository repository;
    @Autowired
    ModuleRepository moduleRepository;
    @GetMapping("/api/modules/{mid}/lessons")
    public List<Lesson> findLessonsForModule(@PathVariable("mid") Integer mid) {
        return this.repository.findAllLessonsForModule(mid);
    }

    @PostMapping("/api/modules/{mid}/lessons")
    public List<Lesson> addModuleToCourse(
            @PathVariable("mid") Integer moduleId,
            @RequestBody Lesson newLesson
    ) {
        Module module = moduleRepository.findModuleById(moduleId);
        newLesson.setModule(module);
        repository.save(newLesson);
        return repository.findAllLessonsForModule(moduleId);
    }

    @DeleteMapping("/api/modules/{mid}/lessons/{lid}")
    public List<Lesson> findAllModulesForCourse(
            @PathVariable("mid") Integer moduleId,
            @PathVariable("lid") Integer lessonId) {
        repository.deleteById(lessonId);
        return repository.findAllLessonsForModule(moduleId);
    }
}