package com.example.wbdvf19widgetserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="lessons")
public class Lesson {
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Module module;

    @OneToMany(mappedBy="lesson", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Topic> topics = new ArrayList<>();

    public Lesson() {

    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }



    public Lesson(String name) {
        this.title = name;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopic(List<Topic> topics) {
        this.topics = topics;
    }
}
