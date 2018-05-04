/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.configdb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableMongoRepositories("com.jmduran.footballwithfriends.server.repositories")
public class DatabaseConfiguration extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private Integer port;
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.password}")
    private String password;
    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
    
    @Override
    public String getDatabaseName() {
        return database;
    }
    @Override
    @Bean
    public MongoClient mongoClient() {
        
        // Heroku Config Vars
        String DB_username = System.getenv("FWF_DB_USERNAME");
        String DB_password = System.getenv("FWF_DB_PASSWORD");
        String DB_database = System.getenv("FWF_DB_DATABASE");
        
        // Mongodb local
        // MongoClient mongoClient = new MongoClient(host + ":" + port);

        // Mongodb Atlas (Cloud)
        // String uri = "mongodb+srv://" + username + ":" + password + "@fwf-cluster-9z6h8.mongodb.net/" + database;
        String uri = "mongodb+srv://" + DB_username + ":" + DB_password + "@fwf-cluster-9z6h8.mongodb.net/" + DB_database;
        MongoClientURI mongoClientUri = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientUri);
        
        
        return mongoClient;
    }
}
