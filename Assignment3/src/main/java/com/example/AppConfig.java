package com.example;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    public TwitterScrap twitterScrapBean(){
    	return new TwitterScrap();
    }
@Bean
@Scope("prototype")
public TwitterEntity twitterEntityBean(){
	return new TwitterEntity();
}
@Bean 
public ArrayList<TwitterEntity> twitterEntityListBean(){
	return new ArrayList<TwitterEntity>();
}
}