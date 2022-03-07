package com.blabla.posts.api.controllers;

import com.blabla.posts.api.application.commands.createPost.CreatePostCommand;
import com.blabla.posts.api.application.dtos.PostCreatedDTO;
import com.blabla.posts.api.application.dtos.PostDetailDTO;
import com.blabla.posts.api.application.dtos.PostPreviewDTO;
import com.blabla.posts.api.application.infrastructure.commandbus.CommandBus;
import com.blabla.posts.api.application.queries.PostQueries;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;

@RequestMapping("posts")
@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "Posts", description = "posts rest api group")
public class PostsController {
    private final CommandBus commandBus;
    private final PostQueries postQueries;

    @PostMapping(value = "")
    public ResponseEntity<PostCreatedDTO> createPost(
        @RequestBody @Valid CreatePostCommand command
    ) {
        return ResponseEntity.ok(commandBus.send(command));
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<PostDetailDTO> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postQueries.getPostById(id));
    }

    @GetMapping(value = "by/distance/latitude/{currentLatitude}/longitude/{currentLongitude}")
    public ResponseEntity<List<PostPreviewDTO>> getPostsByDistance(
        @PathVariable BigDecimal currentLatitude,
        @PathVariable BigDecimal currentLongitude,
        @RequestParam(defaultValue = "1.0") double distanceInKm,
        @RequestParam(defaultValue = "10", required = false) Integer pageSize,
        @RequestParam(defaultValue = "0", required = false) Integer pageIndex
    ) {
        return ResponseEntity.ok(postQueries.getPostsByDistance(
            currentLatitude, currentLongitude,
            distanceInKm,
            pageSize != null ? pageSize : 10,
            pageIndex != null ? pageIndex : 0
        ));
    }
}
