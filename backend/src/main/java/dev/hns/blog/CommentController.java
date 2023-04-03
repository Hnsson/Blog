package dev.hns.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "*")
public class CommentController {
    @Autowired
    private CommentService service;

    @PostMapping()
    public ResponseEntity<Comment> createComment(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Comment>(service.createComment(payload.get("commentBody"), payload.get("postId")), HttpStatus.OK);
    }
}
