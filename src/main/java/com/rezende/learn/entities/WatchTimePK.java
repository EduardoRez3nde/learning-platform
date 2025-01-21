package com.rezende.learn.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class WatchTimePK {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false)
    private Episode episode;

    public WatchTimePK() {}

    public WatchTimePK(User user, Episode episode) {
        this.user = user;
        this.episode = episode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WatchTimePK that = (WatchTimePK) o;
        return Objects.equals(user, that.user) && Objects.equals(episode, that.episode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, episode);
    }
}
