package com.rezende.learn.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_like")
public class Like {

    @EmbeddedId
    private LikePK id = new LikePK();

    public Like(User user, Course course) {
        id.setCourse(course);
        id.setUser(user);
    }

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public Course getCourse() {
        return id.getCourse();
    }

    public void setCourse(Course course) {
        id.setCourse(course);
    }
}
