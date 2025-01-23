package com.rezende.learn.controllers;

import com.rezende.learn.dtos.UserLikeCourseDTO;
import com.rezende.learn.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/like")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(value = "/{userId}/{courseId}")
    public ResponseEntity<UserLikeCourseDTO> insert(@PathVariable Long userId, @PathVariable Long courseId) {
        UserLikeCourseDTO dto = likeService.insert(userId, courseId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userId}/{courseId}")
                .buildAndExpand(dto.getId(), dto.getLike().getCourse().getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{userId}/{courseId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long courseId) {
        likeService.delete(userId, courseId);
        return ResponseEntity.noContent().build();
    }
}
