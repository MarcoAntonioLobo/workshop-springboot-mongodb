package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> findAll() {
        return repo.findAll();
    }

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Post not found! Id: " + id));
    }

    public Post insert(Post obj) {
        return repo.save(obj);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public Post update(Post obj) {
        Post newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Post newObj, Post obj) {
        newObj.setTitle(obj.getTitle());
        newObj.setBody(obj.getBody());
        newObj.setDate(obj.getDate());
    }

    public List<Post> findByText(String text) {
        List<Post> list = repo.findByText(text);
        if (list.isEmpty()) {
            throw new ObjectNotFoundException("No posts found with text: " + text);
        }
        return list;
    }

    public List<Post> findByAuthorId(String authorId) {
        List<Post> list = repo.findByAuthorId(authorId);
        if (list.isEmpty()) {
            throw new ObjectNotFoundException("No posts found for authorId: " + authorId);
        }
        return list;
    }

    public List<Post> findByDateRange(Date start, Date end) {
        List<Post> list = repo.findByDateBetween(start, end);
        if (list.isEmpty()) {
            throw new ObjectNotFoundException("No posts found between dates: " + start + " and " + end);
        }
        return list;
    }
}
