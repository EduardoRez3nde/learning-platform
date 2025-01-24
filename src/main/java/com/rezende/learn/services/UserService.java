package com.rezende.learn.services;

import com.rezende.learn.dtos.UserRoleDTO;
import com.rezende.learn.dtos.UserDTO;
import com.rezende.learn.dtos.UserWithPasswordDTO;
import com.rezende.learn.entities.Role;
import com.rezende.learn.entities.User;
import com.rezende.learn.repositories.RoleRepository;
import com.rezende.learn.repositories.UserRepository;
import com.rezende.learn.services.exceptions.DatabaseException;
import com.rezende.learn.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDTO finById(Long id) {
        try {
            User result = userRepository.getReferenceById(id);
            return new UserDTO(result);
        }
        catch(NoSuchElementException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> result = userRepository.findAll(pageable);
        return result.map(UserDTO::new);
    }

    @Transactional
    public UserDTO insert(UserWithPasswordDTO dto) {
        User user = new User();
        copyToEntity(user, dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserRoleDTO dto) {
        try {
            User user = userRepository.getReferenceById(id);
            copyToEntity(user, dto);
            user = userRepository.save(user);
            return new UserDTO(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource with id %d not found", id);
        }
    }

    @Transactional
    public void deleteById(Long id) {

        if (!userRepository.existsById(id))
            throw new ResourceNotFoundException("Resource with id %d not Found", id);
        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation Exception");
        }
    }

    public void copyToEntity(User entity, UserDTO dto) {
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setBirthDate(dto.getBirthDate());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        entity.getRoles().clear();
        dto.getRoles().forEach(role -> {
            Role roles = roleRepository.getReferenceById(role.getId());
            entity.getRoles().add(roles);
        });
    }
}
