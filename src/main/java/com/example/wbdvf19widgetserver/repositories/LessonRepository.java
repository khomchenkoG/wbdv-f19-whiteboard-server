package com.example.wbdvf19widgetserver.repositories;


import com.example.wbdvf19widgetserver.models.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonRepository
        extends CrudRepository<Lesson, Integer> {

    @Query("select lesson from Lesson lesson where lesson.module.id = :mid")
    public List<Lesson> findAllLessonsForModule(
            @Param("mid") Integer moduleId
    );

    @Query("select lesson from Lesson lesson where lesson.id = :id")
    public Lesson findLessonById(@Param("id") Integer id);
}
