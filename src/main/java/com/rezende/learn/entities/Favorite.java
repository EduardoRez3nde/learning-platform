package com.rezende.learn.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_favorite")
@EntityListeners(AuditingEntityListener.class)
public class Favorite {

    @EmbeddedId
    private FavoritePK id = new FavoritePK();

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    public Favorite() {}

    public Favorite(FavoritePK id) {
        this.id = id;
    }

    public Favorite(Course course, User user) {
        id.setUser(user);
        id.setCourse(course);
    }

    public FavoritePK getId() {
        return id;
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

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
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
