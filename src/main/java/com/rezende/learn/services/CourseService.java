package com.rezende.learn.services;

import com.rezende.learn.dtos.CategoryDTO;
import com.rezende.learn.dtos.CourseDTO;
import com.rezende.learn.dtos.CourseWithEpisodesDTO;
import com.rezende.learn.entities.Category;
import com.rezende.learn.entities.Course;
import com.rezende.learn.repositories.CategoryRepository;
import com.rezende.learn.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CourseWithEpisodesDTO findById(Long id) {
        Course result = courseRepository.findById(id).orElseThrow();
        return new CourseWithEpisodesDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<CourseDTO> findAll(Pageable pageable) {
        Page<Course> result = courseRepository.findAll(pageable);
        return result.map(CourseDTO::new);
    }

    @Transactional(readOnly = true)
    public List<CourseDTO> findRandomFeaturedCourses() {
        List<Course> courses = courseRepository.findFeaturedCourses();
        Collections.shuffle(courses);
        return courses.stream().map(CourseDTO::new).limit(3).toList();
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

    @Transactional
    public void deleteById(Long id) {

        if (!courseRepository.existsById(id))
            throw new RuntimeException("Resource not Found");
        try {
            courseRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity Violation Exception");
        }
    }
}
