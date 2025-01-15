package com.rezende.learn.controllers;

import com.rezende.learn.dtos.CourseDTO;
import com.rezende.learn.dtos.CourseWithEpisodesDTO;
import com.rezende.learn.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseWithEpisodesDTO> findById(@PathVariable Long id) {
        CourseWithEpisodesDTO result = courseService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> findAll(Pageable pageable) {
        Page<CourseDTO> result = courseService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/featured")
    public ResponseEntity<List<CourseDTO>> findRandomFeaturedCourses() {
        List<CourseDTO> result = courseService.findRandomFeaturedCourses();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> insert(@RequestBody CourseDTO dto) {
        dto = courseService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody CourseDTO dto) {
        CourseDTO result = courseService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
