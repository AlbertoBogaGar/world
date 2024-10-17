package repository;

import java.util.List;

import dao.CountryDAO;
import dao.CountryDAOMySQL;
import model.Country;
import model.Country;

public class CountryRepository {
private static CountryRepository instance;
	
	public static synchronized CountryRepository getInstance() {
		if(instance==null) {
			instance = new CountryRepository();
		}
		return instance;	
	}
	private CountryDAO dao;
	private CountryRepository() {
		dao=new CountryDAOMySQL();
	}
		public List<Country> select() {
		return dao.select();
	}
	public Country selectById(String code) {
		return dao.selectById(code);
	}
	public boolean insert(Country city) {

		return dao.insert(city);
	}
	public boolean update(Country country) {

		return dao.update(country);
	}
	public boolean delete(String code) {

		return dao.delete(code);
	}
}
