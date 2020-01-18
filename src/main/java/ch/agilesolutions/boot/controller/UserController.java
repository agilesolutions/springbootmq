package ch.agilesolutions.boot.controller;

import static net.logstash.logback.argument.StructuredArguments.kv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.agilesolutions.boot.model.User;
import ch.agilesolutions.boot.repository.UserRepository;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author u24279
 *
 */
@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private UserRepository repository;

	@GetMapping("send")
	@ApiOperation(value = "Send MQ message", notes = "Sending message over MQ")
	String send() {
		try {
			logger.info("Sending message", kv("type", "ATL"));

			jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
			return "OK";
		} catch (JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}

	@GetMapping("recv")
	@ApiOperation(value = "Read MQ message", notes = "Read message over MQ")
	String recv() {
		try {
			logger.info("Reading message", kv("type", "ATL"));
			return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
		} catch (JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}

	// Single item

	@GetMapping("/cars/{id}")
	@ApiOperation(value = "Get user from DB", notes = "Fetch user from Orcacle DB")
	public ResponseEntity<User> fetchUser(@PathVariable Integer id) {

		logger.info("fetching vehicle with id {}", id, kv("type", "ATL"));

		try {
			return new ResponseEntity<User>(repository.findById(id).get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Exceptin while fetching vehicle with id {}", id, kv("type", "SAL"));
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
