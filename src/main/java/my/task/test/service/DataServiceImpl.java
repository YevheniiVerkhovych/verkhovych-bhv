package my.task.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.task.test.repository.KeyValueRepo;

@Service
public class DataServiceImpl implements DataService {

	@Autowired
	private KeyValueRepo dataDAO;

	@Override
	public String getData(String key) {

		return dataDAO.getData(key);
	}

	@Override
	public void saveData(String key, String value) {

		dataDAO.saveData(key, value);
	}

	@Override
	public void updateData(String key, String value) {

		dataDAO.updateData(key, value);
	}

	@Override
	public void deleteData(String key) {

		dataDAO.deleteData(key);

	}
}
