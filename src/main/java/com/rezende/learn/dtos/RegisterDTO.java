package com.rezende.learn.dtos;

import com.rezende.learn.entities.User;

import java.util.Date;

public class RegisterDTO extends UserDTO {

    private String password;

    public RegisterDTO() { }

    public RegisterDTO(User entity) {
        super(entity);
        this.password = entity.getPassword();
    }

    public RegisterDTO(Long id, String firstname, String lastname, String phone, Date birthDate, String email, String password) {
        super(id, firstname, lastname, phone, birthDate, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
