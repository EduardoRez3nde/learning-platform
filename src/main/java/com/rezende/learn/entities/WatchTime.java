package com.rezende.learn.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "tb_watch_time")
@EntityListeners(AuditingEntityListener.class)
public class WatchTime {

    @EmbeddedId
    private WatchTimePK id = new WatchTimePK();

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @Column(nullable = false)
    private Long seconds;

    public WatchTime() {}

    public WatchTime(User user, Episode episode) {
        id.setEpisode(episode);
        id.setUser(user);
    }

    public WatchTime(Long seconds, User user, Episode episode) {
        id.setEpisode(episode);
        id.setUser(user);
        this.seconds = seconds;
    }

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        id.setUser(user);
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public Episode getEpisode() {
        return id.getEpisode();
    }

    public void setEpisode(Episode episode) {
        id.setEpisode(episode);
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
