package com.rezende.learn.dtos;

public class UserLikeCourseDTO {

    private Long id;
    private String firstName;

    private LikeDTO like;

    public UserLikeCourseDTO() {}

    public UserLikeCourseDTO(Long id, String firstName, LikeDTO like) {
        this.id = id;
        this.firstName = firstName;
        this.like = like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LikeDTO getLike() {
        return like;
    }

    public void setLike(LikeDTO like) {
        this.like = like;
    }
}
