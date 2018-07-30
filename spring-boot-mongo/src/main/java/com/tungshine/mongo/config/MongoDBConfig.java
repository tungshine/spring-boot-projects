package com.tungshine.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoDBConfig {

    public @Bean
    MongoClient mongoClient() {
        return new MongoClient("localhost", 27017);
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "test_mongo");
    }

    @Bean
    public GridFS gridFS() {
        return new GridFS(mongo().getDB("test_mongo"));
    }

    @Bean
    public Mongo mongo() {
        return new MongoClient("localhost", 27017);
    }
}
