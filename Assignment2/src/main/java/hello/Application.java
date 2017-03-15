package hello;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
// @Autowired
// Greeting wired;
// 
// @Bean 
// public
// 
//@Bean 
//public CommandLineRunner clr(MessageDAO dao){
//	return (args) -> {
//	dao.save(wired);
//	};
//}
}
