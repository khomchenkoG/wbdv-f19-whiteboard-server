package com.example.wbdvf19widgetserver.controllers;
import com.example.wbdvf19widgetserver.models.Lesson;
import com.example.wbdvf19widgetserver.models.Topic;
import com.example.wbdvf19widgetserver.repositories.LessonRepository;
import com.example.wbdvf19widgetserver.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TopicController {
    @Autowired
    TopicRepository repository;
    @Autowired
    LessonRepository lessonRepository;
    @GetMapping("/api/lessons/{lid}/topics")
    public List<Topic> findAllTopicsForLesson(@PathVariable("lid") Integer lid) {
        return this.repository.findAllTopicsForLesson(lid);
    }

    @PostMapping("/api/lessons/{lid}/topics")
    public List<Topic> addModuleToCourse(
            @PathVariable("lid") Integer lessonId,
            @RequestBody Topic newTopic
    ) {
        Lesson lesson = lessonRepository.findLessonById(lessonId);
        newTopic.setLesson(lesson);
        repository.save(newTopic);
        return repository.findAllTopicsForLesson(lessonId);
    }

    @DeleteMapping("/api/lessons/{lid}/topics/{tid}")
    public List<Topic> findAllModulesForCourse(
            @PathVariable("lid") Integer lessonId,
            @PathVariable("tid") Integer topicId) {
        repository.deleteById(topicId);
        return repository.findAllTopicsForLesson(lessonId);
    }
}