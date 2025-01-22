package com.rezende.learn.controllers;

import com.rezende.learn.dtos.CourseDTO;
import com.rezende.learn.dtos.FavoriteDTO;
import com.rezende.learn.dtos.UserAndFavoriteDTO;
import com.rezende.learn.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<FavoriteDTO> insert(
            @RequestParam(name = "userId", required = false) Long userId,
            @RequestParam(name = "courseId", required = false) Long courseId) {
        FavoriteDTO dto = favoriteService.insert(userId, courseId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userId}/{courseId}")
                .buildAndExpand(dto.getUser().getId(), dto.getCourse().getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserAndFavoriteDTO> findByUserId(@PathVariable Long userId) {
        UserAndFavoriteDTO result = favoriteService.findByUserId(userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{useId}/{courseId}")
    public ResponseEntity<Void> removeCourseFavorite(@PathVariable Long userId, @PathVariable Long courseId) {
        removeCourseFavorite(userId, courseId);
        return ResponseEntity.noContent().build();
    }
}
