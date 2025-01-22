package com.rezende.learn.services;

import com.rezende.learn.dtos.CourseDTO;
import com.rezende.learn.dtos.FavoriteDTO;
import com.rezende.learn.dtos.UserAndFavoriteDTO;
import com.rezende.learn.entities.Course;
import com.rezende.learn.entities.Favorite;
import com.rezende.learn.entities.FavoritePK;
import com.rezende.learn.entities.User;
import com.rezende.learn.repositories.CourseRepository;
import com.rezende.learn.repositories.FavoriteRepository;
import com.rezende.learn.repositories.UserRepository;
import com.rezende.learn.services.exceptions.DatabaseException;
import com.rezende.learn.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FavoriteService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Transactional
    public FavoriteDTO insert(Long userId, Long courseId) {
        User user = userRepository.getReferenceById(userId);
        Course course = courseRepository.getReferenceById(courseId);
        FavoritePK id = new FavoritePK(course, user);

        Favorite favorite = new Favorite(id);
        favorite = favoriteRepository.save(favorite);
        return new FavoriteDTO(favorite);
    }

    @Transactional(readOnly = true)
    public UserAndFavoriteDTO findByUserId(Long userId) {
        try {
            User user = userRepository.getReferenceById(userId);
            return new UserAndFavoriteDTO(user);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Resource Not Found!");
        }
    }

    @Transactional
    public void removeCourseFavorite(Long userId, Long courseId) {
        User user = userRepository.getReferenceById(userId);
        Course course = courseRepository.getReferenceById(courseId);
        FavoritePK id = new FavoritePK(course, user);

        if (!favoriteRepository.existsById(id))
            throw new ResourceNotFoundException("Resource with id %d not Found", id);
        try {
            favoriteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation Exception");
        }
    }

    @Transactional
    public boolean isFavorite(Long userId, Long courseId) {
        User user = userRepository.getReferenceById(userId);
        Course course = courseRepository.getReferenceById(courseId);
        FavoritePK id = new FavoritePK(course, user);
        return (id != null) ? true : false;
    }
}
