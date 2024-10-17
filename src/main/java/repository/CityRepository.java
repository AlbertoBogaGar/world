package repository;

import java.util.List;

import dao.CityDAO;
import dao.CityDAOMySQL;
import model.City;

public class CityRepository {
	private static CityRepository instance;
	
	public static synchronized CityRepository getInstance() {
		if(instance==null) {
			instance = new CityRepository();
		}
		return instance;	
	}
	private CityDAO dao;
	private CityRepository() {
		dao=new CityDAOMySQL();
	}
	public List<City> select() {
		return dao.select();
	}
	public City selectById(long id) {
		return dao.selectById(id);
	}
	public boolean insert(City city) {

		return dao.insert(city);
	}
	public boolean update(City city) {

		return dao.update(city);
	}
	public boolean delete(long id) {

		return dao.delete(id);
	}
}
