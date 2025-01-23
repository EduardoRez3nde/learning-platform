package com.rezende.learn.services;

import com.rezende.learn.dtos.LikeDTO;
import com.rezende.learn.dtos.UserLikeCourseDTO;
import com.rezende.learn.entities.Course;
import com.rezende.learn.entities.Like;
import com.rezende.learn.entities.LikePK;
import com.rezende.learn.entities.User;
import com.rezende.learn.repositories.CourseRepository;
import com.rezende.learn.repositories.LikeRepository;
import com.rezende.learn.repositories.UserRepository;
import com.rezende.learn.services.exceptions.DatabaseException;
import com.rezende.learn.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public UserLikeCourseDTO insert(Long userId, Long courseId) {
        User user = userRepository.getReferenceById(userId);
        Course course = courseRepository.getReferenceById(courseId);
        Like likeId = new Like(user, course);

        likeId = likeRepository.save(likeId);
        return new UserLikeCourseDTO(likeId.getUser().getId(), likeId.getUser().getFirstname(), new LikeDTO(likeId));
    }

    @Transactional
    public void delete(Long userId, Long courseId) {
        User user = userRepository.getReferenceById(userId);
        Course course = courseRepository.getReferenceById(courseId);
        LikePK id = new LikePK(user, course);

        if (!likeRepository.existsById(id))
            throw new ResourceNotFoundException("Resource with id %d not Found", id);
        try {
            likeRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation Exception");
        }
    }

    @Transactional
    public boolean isLike(Long userId, Long courseId) {
        User user = userRepository.getReferenceById(userId);
        Course course = courseRepository.getReferenceById(courseId);
        LikePK id = new LikePK(user, course);
        Like like = likeRepository.getReferenceById(id);
        return (like != null) ? true : false;
    }
}
