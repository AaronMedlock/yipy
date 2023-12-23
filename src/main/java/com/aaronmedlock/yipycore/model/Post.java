package com.aaronmedlock.yipycore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "testingPosts")
public class Post {

    // Metadata
    @Id
    private String postId;
    @Field
    private String parentPostId;
    @Field
    private String postOwner;
    @Field
    private List<String> likedBy;

    // Post Body
    @Field
    private Instant messageCreateDate;
    @Field
    private String messageText;
    @Field
    private String messageImageUrl1;
    @Field
    private String messageImageUrl2;
    @Field
    private String messageImageUrl3;
    @Field
    private String messageImageUrl4;

    public Post(){}

}
