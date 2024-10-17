package model;

import java.util.Objects;

public class Country {
	private String code;
	private String name;
	private String continent;
	private String region;
	private long population;
	public Country(String code, String name, String continent, String region, long population) {
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.region = region;
		this.population = population;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	}
	@Override
	public int hashCode() {
		return Objects.hash(code, continent, name, population, region);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(code, other.code) && Objects.equals(continent, other.continent)
				&& Objects.equals(name, other.name) && population == other.population
				&& Objects.equals(region, other.region);
	}
	@Override
	public String toString() {
		return "Country code=" + code + ", name=" + name + ", continent=" + continent + ", region=" + region
				+ ", population=" + population + "\n";
	}
	
	
	
}
