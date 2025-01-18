package com.rezende.learn.controllers;

import com.rezende.learn.dtos.CourseDTO;
import com.rezende.learn.dtos.CourseWithEpisodesDTO;
import com.rezende.learn.services.CourseService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Page<CourseDTO>> findAll(@RequestParam(value = "name", required = false)
       String name, Pageable pageable) {
        Page<CourseDTO> result;
        if (name != null && !name.isEmpty())
            result = courseService.findByName(name, pageable);
        else
            result = courseService.findAll(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/featured")
    public ResponseEntity<List<CourseDTO>> findRandomFeaturedCourses() {
        List<CourseDTO> result = courseService.findRandomFeaturedCourses();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/recent")
    public ResponseEntity<List<CourseDTO>> findNewestCourses() {
        List<CourseDTO> result = courseService.findNewestCourse();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/tops")
    public ResponseEntity<List<CourseDTO>> findTopByCourses() {
        List<CourseDTO> result = courseService.findTopByLikes();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> insert(@Valid @RequestBody CourseDTO dto) {
        dto = courseService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CourseDTO> update(@Valid @PathVariable Long id, @RequestBody CourseDTO dto) {
        CourseDTO result = courseService.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
