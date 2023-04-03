package dev.hns.blog;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<BlogPost, ObjectId> {
    Optional<BlogPost> findPostByTitle(String title);
}
