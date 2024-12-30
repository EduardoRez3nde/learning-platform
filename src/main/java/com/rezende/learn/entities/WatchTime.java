package com.rezende.learn.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_watch_time")
public class WatchTime {

    @EmbeddedId
    private WatchTimePK id = new WatchTimePK();

    @Column(nullable = false)
    private Long seconds;

    public WatchTime() {}

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
}
