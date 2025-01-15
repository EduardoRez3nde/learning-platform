package com.rezende.learn.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String synopsis;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private Boolean featured;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "course")
    @OrderBy("episode_order ASC")
    private final List<Episode> episodes = new ArrayList<>();

    @OneToMany(mappedBy = "id.course")
    private final Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "id.course")
    private final Set<Favorite> favorites = new HashSet<>();

    public Course() {}

    public Course(Long id, String name, String synopsis, String thumbnailUrl, Boolean featured, Category category) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.thumbnailUrl = thumbnailUrl;
        this.featured = featured;
        this.category = category;
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

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }
}
