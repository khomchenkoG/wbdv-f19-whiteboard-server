package com.example.wbdvf19widgetserver.models;

import java.util.List;

public class Course {

    private String title;
    private String id;
    private List<Module> modules;

    public Course(String title, String id, List<Module> modules) {
        this.title = title;
        this.id = id;
        this.modules = modules;
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

    public List<Module> getModules() {
        return modules;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }
}
