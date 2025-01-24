package com.rezende.learn.dtos;

import com.rezende.learn.entities.User;
import com.rezende.learn.services.validation.UserInsertValid;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@UserInsertValid
public class UserWithPasswordDTO extends UserDTO {

    private String password;

    public UserWithPasswordDTO() {}

    public UserWithPasswordDTO(Long id, String firstname, String lastname, String phone, Date birthDate, String email, String password) {
        super(id, firstname, lastname, phone, birthDate, email);
        this.password = password;
    }

    public UserWithPasswordDTO(User entity) {
        super(entity);
        setPassword(entity.getPassword());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
