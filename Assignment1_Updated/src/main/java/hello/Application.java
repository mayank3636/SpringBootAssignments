package hello;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		
		SpringApplication.run(Application.class);
System.out.println(MyBean1.class);
MyBean1 mb= new MyBean1();
//Field f=mb.getClass().getFields()[0];
System.out.println(mb.getName());
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

	    Proxy proxy= new Proxy(Type.HTTP, new InetSocketAddress("172.22.218.218", 8085));
	    requestFactory.setProxy(proxy);

	    return new RestTemplate(requestFactory);
	}
	
	@Bean(name="quote")
	public Quote getQuote(){
		return new Quote();
		
	}

	@Bean(name="CLI1")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args-> {
			
			
			Quote quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			Quote q= getQuote();
			q.setType(quote.getType());
			q.setValue(quote.getValue());
			log.info(quote.toString());
			};
		
	}
	
}