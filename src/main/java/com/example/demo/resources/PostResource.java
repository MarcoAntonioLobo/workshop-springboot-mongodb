package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<Post>> findByUser(@PathVariable String userId) {
        User user = userService.findById(userId);
        List<Post> posts = user.getPosts();
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Post obj) {
        obj = postService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Post obj, @PathVariable String id) {
        obj.setId(id);
        obj = postService.update(obj);
        return ResponseEntity.noContent().build();
    }
}
