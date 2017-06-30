package com.springbootex.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
@EnableMongoRepositories(basePackages = "org.springframework.cloud.servicebroker.mongodb.repository")
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "pcfdb";
	}

	@Value("${mongodb.host:localhost}")
	private String host;

	@Value("${mongodb.port:27017}")
	private int port;

	@Bean
	public MongoClient mongoClient() throws UnknownHostException {
		return new MongoClient(new ServerAddress(host, port));
	}

	@Override
	public Mongo mongo() throws Exception {
		return mongoClient();
	}
	/*
	@Bean
	public MongoTemplate getMongoTemplate(Mongo mongo) {
		return new MongoTemplate(mongo, getDatabaseName());
		//return new MongoTemplate(mongo, "testdb");
	}*/

}