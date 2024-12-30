package com.rezende.learn.dtos;

import com.rezende.learn.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryCourseDTO {

    private Long id;
    private String name;

    private final List<CourseDTO> courses = new ArrayList<>();

    public CategoryCourseDTO() {}

    public CategoryCourseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryCourseDTO(Category entity) {
        setId(entity.getId());
        setName(entity.getName());
        entity.getCourses().forEach(course -> courses.add(new CourseDTO(course)));
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

    public List<CourseDTO> getCourses() {
        return courses;
    }
}
