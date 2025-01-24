package com.rezende.learn.services.validation;

import com.rezende.learn.dtos.UserWithPasswordDTO;
import com.rezende.learn.dtos.exceptions.FieldMessageDTO;
import com.rezende.learn.entities.User;
import com.rezende.learn.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserWithPasswordDTO> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserWithPasswordDTO dto, ConstraintValidatorContext context) {

        List<FieldMessageDTO> errors = new ArrayList<>();
        User user = userRepository.findByEmail(dto.getEmail());

        if (user != null)
            errors.add(new FieldMessageDTO("email", "Email already exists"));

        // Inserir na lista do bean validation
        for (FieldMessageDTO e : errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }
}
