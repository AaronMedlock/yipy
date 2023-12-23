package com.aaronmedlock.yipycore.repository;

import com.aaronmedlock.yipycore.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostDao extends MongoRepository<Post, String> {

    Post findPostsByUsername(String username);


}
