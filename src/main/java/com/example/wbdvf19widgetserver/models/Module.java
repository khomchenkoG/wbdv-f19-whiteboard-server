package com.example.wbdvf19widgetserver.models;

import java.util.List;

public class Module {
    private String title;
    private String id;
    private List<Lesson> lessons;

    public Module(String name, String id, List<Lesson> lessons) {
        this.title = name;
        this.id = id;
        this.lessons = lessons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }
}
