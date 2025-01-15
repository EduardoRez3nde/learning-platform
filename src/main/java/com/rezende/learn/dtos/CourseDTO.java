package com.rezende.learn.dtos;

import com.rezende.learn.entities.Category;
import com.rezende.learn.entities.Course;

public class CourseDTO {

    private Long id;
    private String name;
    private String synopsis;
    private String thumbnailUrl;
    private Boolean featured;
    private CategoryDTO category;

    public CourseDTO() {}

    public CourseDTO(String name, String synopsis, String thumbnailUrl, Boolean featured, CategoryDTO category) {
        this.name = name;
        this.synopsis = synopsis;
        this.thumbnailUrl = thumbnailUrl;
        this.featured = featured;
        this.category = category;
    }

    public CourseDTO(Course entity) {
        setId(entity.getId());
        setName(entity.getName());
        setSynopsis(entity.getSynopsis());
        setThumbnailUrl(entity.getThumbnailUrl());
        setFeatured(entity.getFeatured());
        setCategory(new CategoryDTO(entity.getCategory()));
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

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
