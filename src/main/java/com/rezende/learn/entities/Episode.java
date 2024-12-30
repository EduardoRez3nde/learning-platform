package com.rezende.learn.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String synopsis;

    @Column(name = "episode_order", nullable = false)
    private Integer order;

    @Column(nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private Long secondsLong;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "id.episode", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<WatchTime> watchTimes = new HashSet<>();

    public Episode(Long id, String name, String synopsis, Integer order, String videoUrl, Long secondsLong, Course course) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.order = order;
        this.videoUrl = videoUrl;
        this.secondsLong = secondsLong;
        this.course = course;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getSecondsLong() {
        return secondsLong;
    }

    public void setSecondsLong(Long secondsLong) {
        this.secondsLong = secondsLong;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<WatchTime> getWatchTimes() {
        return watchTimes;
    }
}
