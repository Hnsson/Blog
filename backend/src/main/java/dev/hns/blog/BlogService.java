package dev.hns.blog;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository repository;

    public List<BlogPost> AllPosts() {
        return repository.findAll();
    }

    public Optional<BlogPost> getPostByTitle(String title) {
        return repository.findPostByTitle(title);
    }

    public Optional<BlogPost> getPostById(ObjectId postId) {
        return repository.findById(postId);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public BlogPost createBlogPost(String title, String author, String content, String image, List<String> categories) {
        BlogPost post = repository.insert(new BlogPost(title, author, LocalDateTime.now(), content, image, categories));
        
        mongoTemplate.update(BlogPost.class).matching(Criteria.where("title").is(title))
            .apply(new Update().push("posts").value(post))
            .first();

        return post;
    }
}
