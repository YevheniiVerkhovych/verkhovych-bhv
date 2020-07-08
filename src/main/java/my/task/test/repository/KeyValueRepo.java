package my.task.test.repository;

public interface KeyValueRepo {

	public void saveData(String key, String value);

	public String getData(String key);

	public void deleteData(String key);
	
	public void updateData(String key, String value);
	
}
