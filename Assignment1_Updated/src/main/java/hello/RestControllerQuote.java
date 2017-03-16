package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerQuote {
 @Autowired
 @Qualifier("quote")
 Quote quote;
	@RequestMapping("/greeting")
	public String ResponseInString(){
		
		System.out.println("In Controller");
	    System.out.println(quote.getType());
	    System.out.println(quote.getValue());
	
		return "";
		
	}
}
