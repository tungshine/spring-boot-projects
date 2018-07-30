package com.tungshine.mongo.config;

import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
public class MongoDBConfig {

    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.port}")
    private int port;
    @Value("${spring.data.mongodb.host}")
    private String host;


    public @Bean
    MongoClient mongoClient() {
        return new MongoClient(host, port);
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), database);
    }

    @Bean
    public GridFS gridFS() {
        return new GridFS(mongo().getDB(database));
    }

    @Bean
    public Mongo mongo() {
        return new MongoClient(host, port);
    }
}
