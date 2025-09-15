package com.example.demo.controller;

import com.example.demo.model.Post;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final Map<Long, Post> posts = new HashMap<>();

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        post.setId((long) (posts.size() + 1));
        posts.put(post.getId(), post);
        return post;
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return posts.get(id);
    }

    @GetMapping
    public Collection<Post> getAllPosts() {
        return posts.values();
    }
}
