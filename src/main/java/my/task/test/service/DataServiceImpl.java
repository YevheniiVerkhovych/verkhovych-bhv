package my.task.test.service;

import my.task.test.exceptions.DataAlreadyExistException;
import my.task.test.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.task.test.repository.KeyValueRepo;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private KeyValueRepo keyValueRepo;

	@Override
	public String getData(String key) {
		valueNull(key);
		return keyValueRepo.getData(key);
	}

	@Override
	public String saveData(String key, String value) {
		valueNotNull(key);
		keyValueRepo.saveData(key, value);
		return keyValueRepo.getData(key);
	}

	@Override
	public String updateData(String key, String value) {
		valueNull(key);
		keyValueRepo.updateData(key, value);
		return keyValueRepo.getData(key);
	}

	@Override
	public void deleteData(String key) {
		valueNull(key);
		keyValueRepo.deleteData(key);
	}

	private void valueNull(String key){
		if (keyValueRepo.getData(key) == null) {
			throw new DataNotFoundException("Data key not found - " + key);
		}
	}

	private void valueNotNull(String key){
		if (keyValueRepo.getData(key) != null) {
			throw new DataAlreadyExistException("Data key already exist - " + key);
		}
	}
}
