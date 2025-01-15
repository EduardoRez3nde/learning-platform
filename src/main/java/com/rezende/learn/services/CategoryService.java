package com.rezende.learn.services;

import com.rezende.learn.dtos.CategoryCourseDTO;
import com.rezende.learn.dtos.CategoryDTO;
import com.rezende.learn.entities.Category;
import com.rezende.learn.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryCourseDTO finById(Long id) {
        Category result = categoryRepository.getReferenceById(id);
        return new CategoryCourseDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> result = categoryRepository.findAll(pageable);
        return result.map(CategoryDTO::new);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category();
        copyToEntity(category, dto);
        category = categoryRepository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        Category category = categoryRepository.getReferenceById(id);
        copyToEntity(category, dto);
        category = categoryRepository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public void deleteById(Long id) {

        if (!categoryRepository.existsById(id))
            throw new RuntimeException("Resource not Found");
        try {
            categoryRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity Violation Exception");
        }
    }

    public static void copyToEntity(Category entity, CategoryDTO dto) {
        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition());
    }
}
