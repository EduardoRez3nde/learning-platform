package com.rezende.learn.dtos;

import com.rezende.learn.entities.Favorite;

public class FavoriteDTO {

    private UserDTO user;
    private CourseDTO course;

    public FavoriteDTO() {}

    public FavoriteDTO(UserDTO user, CourseDTO course) {
        this.user = user;
        this.course = course;
    }

    public FavoriteDTO(Favorite entity) {
        setCourse(new CourseDTO(entity.getCourse()));
        setUser(new UserDTO(entity.getUser()));
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
