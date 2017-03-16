package hello;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownloadController {
	private static final Logger log = LoggerFactory.getLogger(DownloadController.class);
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void getFile(HttpServletResponse response) {
		response.setContentType("application/pdf");      
		response.setHeader("Content-Disposition", "attachment; filename=\"1mb1.pdf\""); 
		response.setHeader("Refresh", "1; url = Sucess.html");
		String fileName="1mb1.pdf";
	    try {
	    	//File f= new File("D:\\spring-MindTree-9-master (2)\\spring-MindTree-9-master\\gs-consuming-rest-master\\complete\\src\\main\\java\\1mb1.pdf");
	    	URL url = getClass().getResource("1mb1.pdf");
	    	File file = new File("src/main/java/1mb1.pdf");
	    	// get your file as InputStream
	      InputStream is = new FileInputStream(file);
	      // copy it to response's OutputStream
	      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
	      response.flushBuffer();
	    } catch (IOException ex) {
	      log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
	      throw new RuntimeException("IOError writing file to output stream");
	    }
		

	}
}
