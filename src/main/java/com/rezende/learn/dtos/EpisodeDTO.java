package com.rezende.learn.dtos;

import com.rezende.learn.entities.Episode;

public class EpisodeDTO {

    private Long id;
    private String name;
    private String synopsis;
    private String videoUrl;
    private Integer order;
    private Long secondsLong;

    public EpisodeDTO() {}

    public EpisodeDTO(Long id, String name, String synopsis, String videoUrl, Integer order, Long secondsLong) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.videoUrl = videoUrl;
        this.order = order;
        this.secondsLong = secondsLong;
    }

    public EpisodeDTO(Episode entity) {
        setId(entity.getId());
        setName(entity.getName());
        setOrder(entity.getOrder());
        setSynopsis(entity.getSynopsis());
        setVideoUrl(entity.getVideoUrl());
        setSecondsLong(entity.getSecondsLong());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamed() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Long getSecondsLong() {
        return secondsLong;
    }

    public void setSecondsLong(Long secondsLong) {
        this.secondsLong = secondsLong;
    }
}
