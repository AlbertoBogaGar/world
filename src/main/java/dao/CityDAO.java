package dao;

import java.util.List;

import model.City;

public interface CityDAO {

	public List<City> select();
	public City selectById(long id);
	public boolean insert(City city);
	public boolean update(City city);
	public boolean delete(long id);

}
