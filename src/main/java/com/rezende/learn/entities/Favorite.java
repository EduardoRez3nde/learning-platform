package com.rezende.learn.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_favorite")
public class Favorite {

    @EmbeddedId
    private FavoritePK id = new FavoritePK();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(id, favorite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
