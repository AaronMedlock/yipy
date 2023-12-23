package com.aaronmedlock.yipycore.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.retry.annotation.EnableRetry;

@Slf4j
@Configuration
@EnableRetry
@EnableMongoRepositories
public class Config {

    @Value("${mongoCollection}")
    private String dbName;

    @Value("${mongoUri}")
    private String mongoUri;


    @Bean
    public MongoClient mongo(){
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString( new ConnectionString(mongoUri) )
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongo(), dbName);
    }

    @Bean
    public MongoDatabase mongoDb(){
        return mongo().getDatabase(dbName);
    }
}