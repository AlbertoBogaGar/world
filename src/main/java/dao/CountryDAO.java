package dao;

import java.util.List;

import model.Country;

public interface CountryDAO {

	public List<Country> select();
	public Country selectById(String code);
	public boolean insert(Country country);
	public boolean update(Country country);
	public boolean delete(String code);
}
