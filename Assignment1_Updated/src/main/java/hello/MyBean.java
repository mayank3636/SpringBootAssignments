package hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;


@Component
public class MyBean {
	
    @Autowired
    public MyBean(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
     // System.out.println("------------------------------------------------------------");
       // System.out.println(files.get(0));
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }

}