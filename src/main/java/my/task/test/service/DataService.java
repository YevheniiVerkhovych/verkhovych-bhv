package my.task.test.service;

public interface DataService {

	public String saveData(String key, String value);

	public String getData(String key);

	public void deleteData(String key);
	
	public String updateData(String key, String value);
	
}
