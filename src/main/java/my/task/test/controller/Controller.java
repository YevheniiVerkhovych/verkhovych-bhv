package my.task.test.controller;

import my.task.test.exceptions.DataAlreadyExistException;
import my.task.test.exceptions.DataNotFoundException;
import my.task.test.service.DataService;

import static java.nio.charset.StandardCharsets.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class Controller {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private DataService dataService;

	@GetMapping("/data")
	public String getData(@RequestParam String key) {
		notNull(key);
		String value = dataService.getData(toUTF(key));
		logger.info("Returned key: " + toUTF(key) + " for value: " + value);
		return value;
	}

	@PostMapping("/data")
	public String addData(@RequestParam String key, @RequestParam String value) {
		notNull(key);
		String valueAdded = dataService.saveData(toUTF(key), toUTF(value));
		logger.info("Returned key: " + toUTF(key) + " for value: " + toUTF(value));
		return valueAdded;
	}

	@PutMapping("/data")
	public String updateData(@RequestParam String key, @RequestParam String value) {
		notNull(key);
		String valueUpdated = dataService.updateData(toUTF(key), toUTF(value));
		return valueUpdated;
	}

	@DeleteMapping("/data")
	public void deleteData(@RequestParam String key) {
		notNull(key);
		dataService.deleteData(toUTF(key));
	}

	private void notNull(String key){
		if(key == null) {
			throw new RuntimeException("String key can't be Null");
		}
	}

	private String toUTF(String notUtf8String) {
		byte[] text = notUtf8String.getBytes();
		String value = new String(text, UTF_8);
		logger.info(value);
		return value;
	}
}
