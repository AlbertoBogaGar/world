package service;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.List;

import model.City;
import repository.CityRepository;
import util.DBConnection;

public class CityService {
	
//SELECT
	public List<City> select(){
		return CityRepository.getInstance().select();
		
	}
//SELECT BY ID
	public City selectById(long id){
		return CityRepository.getInstance().selectById(id);
		
	}
//INSERT INTO
	public boolean insert(City city){
		return CityRepository.getInstance().insert(city);
		
	}
//UPDATE
	public boolean update(City city){
		return CityRepository.getInstance().update(city);
		
	}
//DELETE
	public boolean delete(long id){
		return CityRepository.getInstance().delete(id);
		
	}
}
