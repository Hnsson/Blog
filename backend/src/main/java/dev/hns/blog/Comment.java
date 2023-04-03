package dev.hns.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private ObjectId id;
    private String postId;
    private String body;
    private LocalDateTime created;

    public Comment(String body, String postId, LocalDateTime created) {
        this.body = body;
        this.postId = postId;
        this.created = created;
    }
}
