package my.task.test.controller;

import my.task.test.exceptions.DataAlreadyExistException;
import my.task.test.exceptions.DataNotFoundException;
import my.task.test.service.DataService;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class DataRestController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private DataService dataService;

	@GetMapping("/test")
	public String test() {

		dataService.saveData("A", "Aaaaaaaaaaa");

		return dataService.getData("A");
	}

	@GetMapping("/data")
	public String getData(@RequestParam() String key) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		String value = dataService.getData(key);

		if (value.contentEquals(null))
			throw new DataNotFoundException("Data key not found - " + toUTF(key));

		logger.info("Key: " + toUTF(key) + " for value: " + toUTF(value));
		return value;
	}

	@PostMapping("/data")
	public String addData(@RequestParam() String key, @RequestParam() String value) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		if (dataService.getData(key) != null) {
			throw new DataAlreadyExistException("Data key already exist - " + toUTF(key));
		}

		dataService.saveData(toUTF(key), toUTF(value));

		logger.info("Key: " + toUTF(key) + " for value: " + toUTF(value));
		return value;
	}

	@PutMapping("/data")
	public void updateData(@RequestParam() String key, @RequestParam() String value) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		if (dataService.getData(key) == null) {
			throw new DataNotFoundException("Data key not found - " + toUTF(key));
		}

		dataService.updateData(toUTF(key), toUTF(value));

	}

	@DeleteMapping("/data")
	public void deleteData(@RequestParam() String key) {

		if (key.equals(null))
			throw new RuntimeException("Key is Null");

		if (dataService.getData(key) == null) {
			throw new DataNotFoundException("Data key not found - " + toUTF(key));
		}

		dataService.deleteData(toUTF(key));

	}

	private String toUTF(String notUtf8String) {

		byte[] bytes = StringUtils.getBytesUtf8(notUtf8String);

		return StringUtils.newStringUtf8(bytes);

	}
}
