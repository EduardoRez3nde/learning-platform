package com.rezende.learn.dtos;

import com.rezende.learn.entities.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseWithEpisodesDTO {

    private Long id;
    private String name;
    private String synopsis;
    private String thumbnailUrl;
    private final List<EpisodeDTO> episodes = new ArrayList<>();

    public CourseWithEpisodesDTO(Long id, String name, String synopsis, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.thumbnailUrl = thumbnailUrl;
    }

    public CourseWithEpisodesDTO(Course entity) {
        setId(entity.getId());
        setName(entity.getName());
        setSynopsis(entity.getSynopsis());
        setThumbnailUrl(entity.getThumbnailUrl());
        entity.getEpisodes().forEach(episode -> episodes.add(new EpisodeDTO(episode)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public List<EpisodeDTO> getEpisodes() {
        return episodes;
    }
}
