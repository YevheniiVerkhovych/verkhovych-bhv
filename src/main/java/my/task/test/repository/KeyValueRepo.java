package my.task.test.repository;

public interface KeyValueRepo {

	 String getData(String key);

	 String saveData(String key, String value);

	 String updateData(String key, String value);

	 void deleteData(String key);

}
