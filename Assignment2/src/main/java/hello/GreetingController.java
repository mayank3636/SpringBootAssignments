package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;



@Controller
public class GreetingController {

	@Autowired
	MessageDAO dao;

	 @Bean
	 public HelloMessage messageBean(){
	 return new HelloMessage();
	 }

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	@Bean
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000); // simulated delay
		if(message.getName()!=null)
		dao.save(new Greeting("Hello, " + message.getName() + "!") );
		return new Greeting("Hello, " + message.getName() + "!");

	}

}
