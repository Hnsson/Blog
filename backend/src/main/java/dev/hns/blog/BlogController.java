package dev.hns.blog;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*")
public class BlogController {
    @Autowired
    private BlogService service;

    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        return new ResponseEntity<List<BlogPost>>(service.AllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Optional<BlogPost>> getSinglePost(@PathVariable String title) {
        return new ResponseEntity<Optional<BlogPost>>(service.getPostByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Optional<BlogPost>> getPostById(@PathVariable ObjectId postId) {
        return new ResponseEntity<Optional<BlogPost>>(service.getPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/createpost/test")
    public void creatPost() {
        List<String> categories = new ArrayList<String>();
        categories.add("tech");

        service.createBlogPost("Second Blog Post!", "Emil Hansson", "This is my second blog post!", "", categories);
    }
}
