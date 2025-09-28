package com.example.demo.resources;

import java.net.URI;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.resources.util.URL;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> findByUser(@PathVariable String userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(user.getPosts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Post>> findByText(@RequestParam(defaultValue = "") String text) {
        text = URL.decodeParam(text);
        return ResponseEntity.ok(postService.findByText(text));
    }

    @GetMapping("/datesearch")
    public ResponseEntity<List<Post>> findByDateRange(
            @RequestParam(defaultValue = "") String start,
            @RequestParam(defaultValue = "") String end) {

        Date startDate = URL.convertDate(start, new Date(0));
        Date endDate = URL.convertDate(end, new Date());

        return ResponseEntity.ok(postService.findByDateRange(startDate, endDate));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Post obj) {
        obj = postService.insert(obj);
        URI uri = ServletUriComponentsBuilder
                    .fromPath("/posts/{id}")
                    .buildAndExpand(obj.getId())
                    .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Post obj, @PathVariable String id) {
        obj.setId(id);
        postService.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(defaultValue = "") String text,
            @RequestParam(defaultValue = "") String start,
            @RequestParam(defaultValue = "") String end) {

        text = URL.decodeParam(text);
        Date startDate = URL.convertDate(start, new Date(0));
        Date endDate = URL.convertDate(end, new Date());
        startDate = URL.startOfDay(startDate);
        endDate = URL.endOfDay(endDate);

        return ResponseEntity.ok(postService.fullSearch(text, startDate, endDate));
    }

}
