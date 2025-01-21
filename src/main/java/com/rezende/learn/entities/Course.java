package com.rezende.learn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "tb_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "The name field cannot be blank. Please fill in this field.")
    @Size(min = 2, max = 50, message = "The name must be between {min} and {max} characters long.")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "The synopsis field cannot be blank. Please fill in this field.")
    @Size(min = 1, max = 100, message = "The name must be between {min} and {max} characters long.")
    private String synopsis;

    @Column(nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private Boolean featured = false;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
