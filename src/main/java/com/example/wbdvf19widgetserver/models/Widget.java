package com.example.wbdvf19widgetserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="widgets")

public class Widget implements Comparable {
    private Type type;
    private Integer idx;
    private String link_title;
    private Integer heading_size;
    private String heading_data;
    private String list_data;
    private String paragraph_data;
    private String image_url;
    private String link_data;
    private Boolean ordered;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Topic topic;

    public Widget(Type type, int index, String link_title, int heading_size, String heading_data,
                  String list_data, String paragraph_data, String image_url,
                  String link_data, boolean ordered, Integer id) {
        this.type = type;
        this.idx = index;
        this.link_title = link_title;
        this.heading_size = heading_size;
        this.heading_data = heading_data;
        this.list_data = list_data;
        this.paragraph_data = paragraph_data;
        this.image_url = image_url;
        this.link_data = link_data;
        this.ordered = ordered;
        this.id = id;
    }

    public Widget() {

    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getLink_title() {
        return link_title;
    }

    public void setLink_title(String link_title) {
        this.link_title = link_title;
    }

    public Integer getHeading_size() {
        return heading_size;
    }

    public void setHeading_size(Integer heading_size) {
        this.heading_size = heading_size;
    }

    public String getHeading_data() {
        return heading_data;
    }

    public void setHeading_data(String heading_data) {
        this.heading_data = heading_data;
    }

    public String getList_data() {
        return list_data;
    }

    public void setList_data(String list_data) {
        this.list_data = list_data;
    }

    public String getParagraph_data() {
        return paragraph_data;
    }

    public void setParagraph_data(String paragraph_data) {
        this.paragraph_data = paragraph_data;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLink_data() {
        return link_data;
    }

    public void setLink_data(String link_data) {
        this.link_data = link_data;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public int compareTo(Object o) {
        Widget other = (Widget)o;
        return this.getIdx() - other.getIdx();
    }

    public enum Type{
    HEADING, PARAGRAPH, LINK, LIST, IMAGE
}}
