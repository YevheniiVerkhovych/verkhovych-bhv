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
	public void saveData(String key, String value) {
		myDataSource.put(key, value);
	}

	@Override
	public String getData(String key) {
		return myDataSource.get(key);
	}

	@Override
	public void deleteData(String key) {
		myDataSource.remove(key);
	}

	@Override
	public void updateData(String key, String value) {
		myDataSource.replace(key, value);
	}

}
