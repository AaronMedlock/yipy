package com.aaronmedlock.yipycore.controller;

import com.aaronmedlock.yipycore.model.Post;
import com.aaronmedlock.yipycore.model.User;
import com.aaronmedlock.yipycore.repository.PostDao;
import com.aaronmedlock.yipycore.repository.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    UserDao userDao;
//    @Autowired
//    PostDao postDao;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/sqldb")
    @ResponseBody
    public ResponseEntity<List<User>> testSqlInsert(){

        try{
            List<User> userInDb = userDao.findByUsername("TestyMcTester");
            if(userInDb != null){
                log.info("Found test user in DB as: {}",userInDb);
                return ResponseEntity.ok(userInDb);
            } else {
                User testUser = new User();
                testUser.setUsername("TestyMcTester");
                testUser.setPassword("12345");
                testUser.setDisplayName("Testy McTester");
                testUser.setEmail("testuser@aaronmedlock.com");
                testUser.setPreferredPronouns("They/Them");
                testUser.setProfileDescription("A test user inserted for no reason at all other than as a test");
                testUser.setJoinDate(Instant.now());
                userDao.save(testUser);
                userInDb = userDao.findByUsername("TestyMcTester");
                log.info("Saved and found test user in DB as: {}",userInDb);
                return ResponseEntity.accepted().body(userInDb);
            }

        }catch(Exception e){
            log.error("Unable to complete request due to {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nosqldb")
    @ResponseBody
    public ResponseEntity<List<Post>> testNoSqlInsert() {
        try{
            Query query = new Query(new Criteria().andOperator(Criteria.where("postOwner").is("TestyMcTester")));
            Post firstInsert = mongoTemplate.findOne(query, Post.class);
            Post testPost = new Post();
            if(firstInsert != null){

                testPost.setPostOwner("TestyMcTester");
                testPost.setMessageCreateDate(Instant.now());
                testPost.setMessageText("Reply tested on: "+testPost.getMessageCreateDate());
                testPost.setMessageImageUrl1("");
                testPost.setMessageImageUrl2("");
                testPost.setMessageImageUrl3("");
                testPost.setMessageImageUrl4("");
                testPost.setParentPostId(firstInsert.getPostId());
                testPost.setLikedBy(new ArrayList<>(Arrays.asList(firstInsert.getPostOwner())));
            } else {

                testPost.setPostOwner("TestyMcTester");
                testPost.setMessageCreateDate(Instant.now());
                testPost.setMessageText("This is just a test");
                testPost.setMessageImageUrl1("");
                testPost.setMessageImageUrl2("");
                testPost.setMessageImageUrl3("");
                testPost.setMessageImageUrl4("");
                testPost.setLikedBy(new ArrayList<>(Arrays.asList(testPost.getPostOwner())));
            }

            mongoTemplate.insert(testPost);
            return ResponseEntity.status(202).body(new ArrayList<>(Arrays.asList(testPost)));
        }catch(Exception e){
            log.error("Unable to complete request due to {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
