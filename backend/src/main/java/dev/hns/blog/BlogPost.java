package dev.hns.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPost {
    @Id
    private ObjectId id;
    private String postId;
    private String title;
    private String author;
    private LocalDateTime date;
    private String content;
    private String image;
    private List<String> categories;
    @DocumentReference
    private List<Comment> comments;

    public BlogPost(String title, String author, LocalDateTime date, String content, String image, List<String> categories) {
        this.id = ObjectId.get();
        this.postId = this.id.toHexString();
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
        this.image = image;
        this.categories = categories;

    }
}
