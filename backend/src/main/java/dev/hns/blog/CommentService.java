package dev.hns.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Comment createComment(String commentBody, String postId) {
        Comment comment = repository.insert(new Comment(commentBody, postId, LocalDateTime.now()));

        mongoTemplate.update(BlogPost.class)
            .apply(new Update().push("comments").value(comment))
            .first();

        return comment;
    }
}
