package com.rezende.learn.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
    private Date birthDate;

    @Column(nullable = false)
    private String email;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private LocalDateTime updatedAt;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
