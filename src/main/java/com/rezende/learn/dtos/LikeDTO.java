package com.rezende.learn.dtos;

import com.rezende.learn.entities.Course;
import com.rezende.learn.entities.Like;

public class LikeDTO {

    private CourseDTO course;

    public LikeDTO() {}

    public LikeDTO(CourseDTO course) {
        this.course = course;
    }

    public LikeDTO(Like entity) {
        setCourse(new CourseDTO(entity.getCourse()));
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
