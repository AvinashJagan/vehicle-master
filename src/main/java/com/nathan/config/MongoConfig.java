package com.nathan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoConfig {

	private static final String MONGO_URI = "mongodb+srv://system:PASSWORD@my-cluster-dsjz0.mongodb.net/test?retryWrites=true";
	private static final String MONGO_DB = "test";

	@Bean
	public MongoClient mongoClient() {
		return new MongoClient(new MongoClientURI(MONGO_URI));
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), MONGO_DB);
	}
}