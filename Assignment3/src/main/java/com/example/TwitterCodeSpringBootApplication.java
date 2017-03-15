package com.example;

import java.util.Timer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class TwitterCodeSpringBootApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(TwitterCodeSpringBootApplication.class, args);
	}
	@Bean 
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public CommandLineRunner clr(TwitterScrap scrap,ApplicationContext ctx,DAO d){
		return (args) -> {
			
//			String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
			Timer time = new Timer(); // Instantiate Timer Object
			ScheduledTask st = new ScheduledTask(ctx,d,scrap); // Instantiate SheduledTask class
			time.schedule(st, 0, 20000); 
			
		};
	}

}
