package com.rezende.learn.dtos;

import com.rezende.learn.entities.Category;
import com.rezende.learn.entities.Course;

public class CourseDTO {

    private Long id;
    private String name;
    private String synopsis;
    private String thumbnailUrl;

    public CourseDTO() {}

    public CourseDTO(String name, String synopsis, String thumbnailUrl, Boolean featured, Category category) {
        this.name = name;
        this.synopsis = synopsis;
        this.thumbnailUrl = thumbnailUrl;
    }

    public CourseDTO(Course entity) {
        setId(entity.getId());
        setName(entity.getName());
        setSynopsis(entity.getSynopsis());
        setThumbnailUrl(entity.getThumbnailUrl());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
