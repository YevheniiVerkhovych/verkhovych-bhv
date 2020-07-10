package my.task.test.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("dev")
@Repository
public class KeyValueRepoImpl implements KeyValueRepo {
	Map<String, String> myDataSource = new HashMap<>();

	@Override
	public String getData(String key) {
		return myDataSource.get(key);
	}

	@Override
	public String saveData(String key, String value) {
		myDataSource.put(key, value);
		return value;
	}

	@Override
	public String updateData(String key, String value) {
		myDataSource.replace(key, value);
		return value;
	}

	@Override
	public void deleteData(String key) {
		myDataSource.remove(key);
	}

}
