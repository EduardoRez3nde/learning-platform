package com.rezende.learn.dtos;

import com.rezende.learn.entities.Favorite;

public class FavoriteCourseDTO {

    private CourseDTO course;

    public FavoriteCourseDTO() {}

    public FavoriteCourseDTO(CourseDTO course) {
        this.course = course;
    }

    public FavoriteCourseDTO(Favorite entity) {
        setCourse(new CourseDTO(entity.getCourse()));
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
