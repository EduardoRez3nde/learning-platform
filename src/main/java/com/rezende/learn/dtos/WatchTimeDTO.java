package com.rezende.learn.dtos;

import com.rezende.learn.entities.WatchTime;

public class WatchTimeDTO {

    private UserDTO user;
    private EpisodeDTO episode;
    private Long secondsLong;

    public WatchTimeDTO() {}

    public WatchTimeDTO(UserDTO user, EpisodeDTO episode) {
        this.user = user;
        this.episode = episode;
    }

    public WatchTimeDTO(WatchTime id) {
        setUser(new UserDTO(id.getUser()));
        setEpisode(new EpisodeDTO(id.getEpisode()));
        this.secondsLong = id.getSeconds();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EpisodeDTO getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeDTO episode) {
        this.episode = episode;
    }

    public Long getSecondsLong() {
        return secondsLong;
    }

    public void setSecondsLong(Long secondsLong) {
        this.secondsLong = secondsLong;
    }
}
