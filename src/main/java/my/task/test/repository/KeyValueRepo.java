package my.task.test.repository;

public interface KeyValueRepo {

	public String getData(String key);

	public String saveData(String key, String value);

	public String updateData(String key, String value);

	public void deleteData(String key);

}
