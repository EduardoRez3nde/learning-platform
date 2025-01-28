package com.rezende.learn.services.validation;

import com.rezende.learn.dtos.UserRoleDTO;
import com.rezende.learn.dtos.UserWithPasswordDTO;
import com.rezende.learn.dtos.exceptions.FieldMessageDTO;
import com.rezende.learn.entities.User;
import com.rezende.learn.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserRoleDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRoleDTO dto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> uri = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uri.get("id"));

        List<FieldMessageDTO> errors = new ArrayList<>();
        User user = userRepository.findByEmail(dto.getEmail());

        if (user != null && userId != user.getId())
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
