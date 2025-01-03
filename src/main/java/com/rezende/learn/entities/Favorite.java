package com.rezende.learn.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_favorite")
public class Favorite {

    @EmbeddedId
    private FavoritePK id = new FavoritePK();

    public Favorite() {}

    public Favorite(Course course, User user) {
        id.setUser(user);
        id.setCourse(course);
    }

    public void setCourse(Course course) {
        id.setCourse(course);
    }

    public Course getCourse() {
        return id.getCourse();
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public User getUser() {
        return id.getUser();
    }
}
