package com.rezende.learn.services;

import com.rezende.learn.dtos.CourseDTO;
import com.rezende.learn.dtos.CourseWithEpisodesDTO;
import com.rezende.learn.entities.Category;
import com.rezende.learn.entities.Course;
import com.rezende.learn.repositories.CategoryRepository;
import com.rezende.learn.repositories.CourseRepository;
import com.rezende.learn.services.exceptions.DatabaseException;
import com.rezende.learn.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CourseWithEpisodesDTO findById(Long id) {
        try {
            Course result = courseRepository.findById(id).orElseThrow();
            return new CourseWithEpisodesDTO(result);
        }
        catch(NoSuchElementException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    @Transactional(readOnly = true)
    public Page<CourseDTO> findAll(Pageable pageable) {
        Page<Course> result = courseRepository.findAll(pageable);
        return result.map(CourseDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<CourseDTO> findByName(String name, Pageable pageable) {
        Page<Course> courses = courseRepository.findByName(name, pageable);
        return courses.map(course -> new CourseDTO(course));
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findRandomFeaturedCourses() {
        List<Course> courses = courseRepository.findFeaturedCourses();
        Collections.shuffle(courses);
        return courses.stream().map(CourseDTO::new).limit(3).toList();
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findNewestCourse() {
        List<Course> courses = courseRepository.findRecentCourses();
        return courses.stream().map(course -> new CourseDTO(course)).limit(10).toList();
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findTopByLikes() {
        List<Course> courses = courseRepository.findTopByLikes();
        return courses.stream().map(course -> new CourseDTO(course)).toList();
    }

    @Transactional
    public CourseDTO insert(CourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setFeatured(dto.getFeatured());
        course.setSynopsis(dto.getSynopsis());
        course.setThumbnailUrl(dto.getThumbnailUrl());

        Category category = categoryRepository.getReferenceById(dto.getCategory().getId());
        course.setCategory(category);

        course = courseRepository.save(course);
        return new CourseDTO(course);
    }

    @Transactional
    public CourseDTO update(Long id, CourseDTO dto) {
        try {
            Course course = courseRepository.getReferenceById(id);
            course.setName(dto.getName());
            course.setFeatured(dto.getFeatured());
            course.setSynopsis(dto.getSynopsis());
            course.setThumbnailUrl(dto.getThumbnailUrl());

            Category category = categoryRepository.getReferenceById(dto.getCategory().getId());
            course.setCategory(category);

            course = courseRepository.save(course);
            return new CourseDTO(course);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource with id %d not found", id);
        }
    }

    @Transactional
    public void deleteById(Long id) {

        if (!courseRepository.existsById(id))
            throw new ResourceNotFoundException("Resource with id %d not Found", id);
        try {
            courseRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation Exception");
        }
    }
}
