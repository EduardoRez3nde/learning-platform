package com.rezende.learn.dtos;

import com.rezende.learn.entities.Category;

public class CategoryDTO {

    private Long id;
    private String name;
    private Integer position;

    public CategoryDTO() {}

    public CategoryDTO(Long id, String name, Integer position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public CategoryDTO(Category entity) {
        setId(entity.getId());
        setName(entity.getName());
        setPosition(entity.getPosition());
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
}
