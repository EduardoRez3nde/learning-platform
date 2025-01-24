package com.rezende.learn.dtos;

import com.rezende.learn.entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private Date birthDate;
    private String email;

    private final Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {}

    public UserDTO(Long id, String firstname, String lastname, String phone, Date birthDate, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
    }

    public UserDTO(User entity) {
        setId(entity.getId());
        setFirstname(entity.getFirstname());
        setLastname(entity.getLastname());
        setPhone(entity.getPhone());
        setBirthDate(entity.getBirthDate());
        setEmail(entity.getEmail());
        entity.getRoles().forEach(role -> roles.add(new RoleDTO(role)));
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

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
