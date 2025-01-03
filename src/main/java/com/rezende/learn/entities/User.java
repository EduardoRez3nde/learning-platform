package com.rezende.learn.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String phone;
    private Date birthDate;
    private String email;

    @OneToMany(mappedBy = "id.user")
    private final Set<Like> likes = new HashSet<>();

    @OneToMany(mappedBy = "id.user")
    private final Set<Favorite> favorites = new HashSet<>();

    @OneToMany(mappedBy = "id.user")
    private final Set<WatchTime> watchTimes = new HashSet<>();

    public User() {}

    public User(Long id, String firstname, String lastname, String phone, Date birthDate, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public Set<WatchTime> getWatchTimes() {
        return watchTimes;
    }
}
