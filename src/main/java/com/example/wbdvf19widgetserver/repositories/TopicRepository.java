package com.example.wbdvf19widgetserver.repositories;

import com.example.wbdvf19widgetserver.models.Module;
import com.example.wbdvf19widgetserver.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("select topic from Topic topic where topic.lesson.id=:lessonId")
    List<Topic> findAllTopicsForLesson(@Param("lessonId") Integer id);

    @Query("select topic from Topic topic where topic.id = :id")
    public Topic findTopicById(@Param("id") Integer id);

}