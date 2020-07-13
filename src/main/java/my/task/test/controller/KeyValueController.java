package my.task.test.controller;

import my.task.test.service.KeyValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class KeyValueController {

	private final Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private KeyValueService keyValueService;

	@GetMapping("/data")
	public String getData(@RequestParam String key) {
		notNull(key);
		String value = keyValueService.getData(toUTF(key));
		logger.info("Returned key: " + toUTF(key) + " for value: " + value);
		return value;
	}

	@PostMapping("/data")
	public String addData(@RequestParam String key, @RequestParam String value) {
		notNull(key);
		logger.info("Returned key: " + toUTF(key) + " for value: " + toUTF(value));
		return keyValueService.saveData(toUTF(key), toUTF(value));
	}

	@PutMapping("/data")
	public String updateData(@RequestParam String key, @RequestParam String value) {
		notNull(key);
		return keyValueService.updateData(toUTF(key), toUTF(value));
	}

	@DeleteMapping("/data")
	public void deleteData(@RequestParam String key) {
		notNull(key);
		keyValueService.deleteData(toUTF(key));
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
