package com.rezende.learn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "The email field cannot be blank. Please fill in this field.")
    @Size(min = 2, max = 50, message = "The name must be between {min} and {max} characters long.")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "The position field cannot be blank. Please fill in this field.")
    @Min(value = 1, message = "The quantity must be at least {value}.")
    private Integer position;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "category")
    private final List<Course> courses = new ArrayList<>();

    public Category() {}

    public Category(Long id, String name, Integer position) {
        this.id = id;
        this.name = name;
        this.position = position;
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
