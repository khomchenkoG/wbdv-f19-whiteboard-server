package com.example.wbdvf19widgetserver.models;

import java.util.List;

public class Lesson {
    private String title;
    private String id;
    private List<Topic> topics;

    public Lesson(String name, String id, List<Topic> topics) {
        this.title = name;
        this.id = id;
        this.topics = topics;
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }
}
