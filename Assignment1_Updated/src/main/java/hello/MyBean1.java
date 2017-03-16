package hello;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.Value;

@Component
public class MyBean1 {
	
	@Value("${name}")
    private  String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}