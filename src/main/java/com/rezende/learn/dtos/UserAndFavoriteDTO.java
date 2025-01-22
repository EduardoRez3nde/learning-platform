package com.rezende.learn.dtos;

import com.rezende.learn.entities.User;

import java.util.HashSet;
import java.util.Set;

public class UserAndFavoriteDTO {

    private Long id;
    private String firstname;

    private final Set<FavoriteCourseDTO> favoritesCourse = new HashSet<>();

    public UserAndFavoriteDTO() {}

    public UserAndFavoriteDTO(Long id, String firstname) {
        this.id = id;
        this.firstname = firstname;
    }

    public UserAndFavoriteDTO(User entity) {
        setId(entity.getId());
        setFirstname(entity.getFirstname());
        entity.getFavorites().forEach(favorite -> favoritesCourse.add(new FavoriteCourseDTO(favorite)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Set<FavoriteCourseDTO> getFavoritesCourse() {
        return favoritesCourse;
    }
}
