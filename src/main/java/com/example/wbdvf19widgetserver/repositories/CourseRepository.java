package com.example.wbdvf19widgetserver.repositories;

import com.example.wbdvf19widgetserver.models.Course;
import com.example.wbdvf19widgetserver.models.Widget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository
        extends CrudRepository<Course, Integer> {

    @Query("select course from Course course where course.id=:courseId")
    public Course findCourseById(@Param("courseId") Integer id);

    @Query("select course from Course course")
    public List<Course> findAllCourses();
}