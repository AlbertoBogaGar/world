package service;

import java.util.List;

import model.Country;
import repository.CountryRepository;

public class CountryService {
	//SELECT
		public List<Country> select(){
			return CountryRepository.getInstance().select();
			
		}
	//SELECT BY ID
		public Country selectById(String code){
			return CountryRepository.getInstance().selectById(code);
			
		}
	//INSERT INTO
		public boolean insert(Country city){
			return CountryRepository.getInstance().insert(city);
			
		}
	//UPDATE
		public boolean update(Country country){
			return CountryRepository.getInstance().update(country);
			
		}
	//DELETE
		public boolean delete(String code){
			return CountryRepository.getInstance().delete(code);
			
		}
}
