package my.task.test.repository;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("dev")
@Repository
public class KeyValueRepoImpl implements KeyValueRepo {

	@Autowired
	private Map<String, String> myDataSource;

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
