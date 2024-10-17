package edu.alonso.daw.world;

import service.CityService;
import service.CountryService;
import util.DBConnection;

public class Init {
	public static void main(String[] args) {
		CityService cityService=new CityService();
		CountryService countryService = new CountryService();
		System.out.println(cityService.select());
		//System.out.println(cityService.selectById(4025));
		//System.out.println(cityService.insert(new City("hola", "AFG", "Large", 3)));
		//System.out.println(cityService.update(new City(4081,"adios", "AFG", "Large", 3)));
		//System.out.println(cityService.delete(4082));
		//System.out.println(countryService.select());
		//System.out.println(countryService.selectById("ABW"));
		//System.out.println(countryService.insert(new Country("AAA", "Alibaba", "Africa", "Eastern Africa", 41)));
		//System.out.println(countryService.update(new Country("AAA", "Alibaba", "Africa", "Eastern Africa", 42)));
		//System.out.println(countryService.delete("AAA"));
		
		DBConnection.getInstance().destroyConnection();
	}
}
