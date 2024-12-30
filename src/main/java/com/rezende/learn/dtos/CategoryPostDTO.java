package com.rezende.learn.dtos;

import com.rezende.learn.entities.Category;

public class CategoryPostDTO {

    private String name;
    private Integer position;

    public CategoryPostDTO() {}

    public CategoryPostDTO(String name, Integer position) {
        this.name = name;
        this.position = position;
    }

    public CategoryPostDTO(Category entity) {
        setName(entity.getName());
        setPosition(entity.getPosition());
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
}
