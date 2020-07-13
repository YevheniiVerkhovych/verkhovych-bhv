package my.task.test.service;

public interface KeyValueService {

	 String saveData(String key, String value);

	 String getData(String key);

	 void deleteData(String key);
	
	 String updateData(String key, String value);
	
}
