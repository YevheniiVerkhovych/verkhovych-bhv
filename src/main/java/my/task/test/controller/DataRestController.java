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
public class DataRestController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private DataService dataService;

	@GetMapping("/data")
	public String getData(@RequestParam() String key) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		if (dataService.getData(toUTF(key)) == null)
			throw new DataNotFoundException("Data key not found - " + toUTF(key));

		String value = dataService.getData(toUTF(key));

		logger.info("Returned key: " + toUTF(key) + " for value: " + toUTF(value));
		return toUTF(value);
	}

	@PostMapping("/data")
	public String addData(@RequestParam() String key, @RequestParam() String value) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		if (dataService.getData(toUTF(key)) != null) {
			throw new DataAlreadyExistException("Data key already exist - " + toUTF(key));
		}

		dataService.saveData(toUTF(key), toUTF(value));

		logger.info("Returned key: " + toUTF(key) + " for value: " + toUTF(value));
		return toUTF(value);
	}

	@PutMapping("/data")
	public String updateData(@RequestParam() String key, @RequestParam() String value) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		if (dataService.getData(toUTF(key)) == null) {
			throw new DataNotFoundException("Data key not found - " + toUTF(key));
		}

		dataService.updateData(toUTF(key), toUTF(value));

		return toUTF(value);
	}

	@DeleteMapping("/data")
	public void deleteData(@RequestParam() String key) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		if (dataService.getData(toUTF(key)) == null) {
			throw new DataNotFoundException("Data key not found - " + toUTF(key));
		}

		dataService.deleteData(toUTF(key));

	}

	private String toUTF(String notUtf8String) {

		byte[] ptext = notUtf8String.getBytes(); 
		
		String value = new String(ptext, UTF_8); 
		
		logger.info(value);

		return value;

	}
}
