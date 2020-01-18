package ch.agilesolutions.boot.controller;
import static net.logstash.logback.argument.StructuredArguments.kv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author u24279
 *
 */
@RestController
@RequestMapping("/api")
public class CarController {


	private static final Logger logger = LoggerFactory.getLogger(CarController.class);
	
	   @Autowired
	     private JmsTemplate jmsTemplate;

	@GetMapping("send")
	String send(){
	    try{
	    	logger.info("Sending message",  kv("type", "ATL"));
	    	
	        jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
	        return "OK";
	    }catch(JmsException ex){
	        ex.printStackTrace();
	        return "FAIL";
	    }
	}
	
	@GetMapping("recv")
	String recv(){
	    try{
	    	logger.info("Reading message",  kv("type", "ATL"));
	        return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
	    }catch(JmsException ex){
	        ex.printStackTrace();
	        return "FAIL";
	    }
	}
	
}
