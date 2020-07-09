package my.task.test.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryStorage {

	@Bean
	public Map<String, String> myDataSource() {

		Map<String, String> myMap = new HashMap<>();

		myMap.put("A","sdfsdfsfsf66");

		return myMap;
	}

}
