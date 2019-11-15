package com.example.wbdvf19widgetserver.controllers;

import com.example.wbdvf19widgetserver.models.Lesson;
import com.example.wbdvf19widgetserver.models.Topic;
import com.example.wbdvf19widgetserver.models.Widget;
import com.example.wbdvf19widgetserver.repositories.TopicRepository;
import com.example.wbdvf19widgetserver.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {


    @Autowired
    WidgetRepository repository;

    @Autowired
    TopicRepository topicRepository;


    @PostMapping("/api/topics/{topicId}/widgets")
    public List<Widget> createWidget(
            @PathVariable("topicId") Integer topicId,
            @RequestBody Widget newWidget) {

        //TODO FIND TOPIC AND SET IT FOR A WIDGET
        Topic topic = topicRepository.findTopicById(topicId);
        Integer lastIdx = repository.findAllWidgetsForTopic(topicId).size();
        newWidget.setIdx(lastIdx+1);
        newWidget.setTopic(topic);
        repository.save(newWidget);

        return repository.findAllWidgetsForTopic(topicId);
    }

    @PutMapping("/api/topics/{topicId}/widgets/{widgetId}")
    public List<Widget> updateWidget(
            @PathVariable("widgetId") Integer widgetId,
            @PathVariable("topicId") Integer topicId,
            @RequestBody Widget newWidget
    ) {
        Widget toUpdate = repository.findWidgetById(widgetId);
        Topic topic = toUpdate.getTopic();
        newWidget.setTopic(topic);

        repository.save(newWidget);

        List<Widget> widgets = repository.findAllWidgetsForTopic(topicId);
         Collections.sort(widgets);
        return widgets;
    }

    @PutMapping("/api/topics/{topicId}/widgets")
    public List<Widget> saveAllWidgets(
            @PathVariable("topicId") Integer topicId,
            @RequestBody List<Widget> allWidgets
    ) {
        for (Widget newWidget: allWidgets){
            Widget toUpdate = repository.findWidgetById(newWidget.getId());
            Topic topic = toUpdate.getTopic();
            newWidget.setTopic(topic);

            repository.save(newWidget);
        }
        return repository.findAllWidgetsForTopic(topicId);
        }



    @DeleteMapping("/api/topics/{topicId}/widgets/{widgetId}")
    public List<Widget> deleteWidget(
            @PathVariable("widgetId") Integer widgetId,
            @PathVariable("topicId") Integer topicId) {

        List<Widget> allWidgets = repository.findAllWidgetsForTopic(topicId);
        Integer shiftFrom = repository.findWidgetById(widgetId).getIdx();
        for (int ii = shiftFrom; ii < allWidgets.size(); ii++){
            Widget nextWidget = allWidgets.get(ii);
            nextWidget.setIdx(nextWidget.getIdx() - 1);
            repository.save(nextWidget);
        }

        repository.deleteById(widgetId);
        return repository.findAllWidgetsForTopic(topicId);
    }

    @GetMapping("/api/topics/{topicId}/widgets")
    public List<Widget> findAllWidgets(@PathVariable("topicId") Integer topicId) {
        return repository.findAllWidgetsForTopic(topicId);
    }

    @GetMapping("/api/topics/{topicId}/widgets/{widgetId}")
    public Widget findWidgetById(
            @PathVariable("widgetId") Integer widgetId,
            @PathVariable("topicId") Integer topicId) {
        return repository.findWidgetById(widgetId);
    }


}