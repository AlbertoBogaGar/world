package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.ExcInsert;
import exception.ExcUpd;
import model.City;
import util.DBConnection;

public class CityDAOMySQL implements CityDAO {
	private final String SELECT = "SELECT id, name, countryCode, district, population FROM city";
	private final String SELECT_ID = "SELECT id, name, countryCode, district, population FROM city WHERE id=?";
	private final String INSERT = "INSERT INTO city( name, countryCode, district, population) VALUES(?,?,?,?) ";
	private final String UPDATE="UPDATE city SET name=?, countryCode=?, district=?, population=? WHERE id=?";
	private final String DELETE="DELETE FROM city WHERE id=?";

	@Override
	public List<City> select() {
		List<City> cities = new ArrayList<>();
		System.out.println("Obteniendo las ciudades...");

		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(SELECT);) {

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					City c = new City(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5));
					cities.add(c);
				}

			} catch (SQLException e) {
				System.out.println("Error al obtener la ciudades");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha podido obtener la conexion");
		}

		System.out.println("Ciudades obtenidas con exito!!");
		return cities;
	}

	@Override
	public City selectById(long id) {
		City c = null;
		System.out.println("Obteniendo la ciudad...");

		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(SELECT_ID);) {
				stmt.setLong(1, id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					c = new City(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5));

				}

			} catch (SQLException e) {
				System.out.println("Error al obtener la ciudad");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha podido obtener la conexion");
		}

		System.out.println("Ciudad obtenidas con exito!!");
		return c;
	}

	@Override
	public boolean insert(City city) {
		boolean insert = false;
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(INSERT);) {
				stmt.setString(1, city.getName());
				stmt.setString(2, city.getCountryCode());
				stmt.setString(3, city.getDistrict());
				stmt.setLong(4, city.getPopulation());
				int affected = stmt.executeUpdate();
				if(affected==1) {
					System.out.println("Ciudad insertada con exito");
					insert=true;
				}else {
					throw new ExcInsert();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExcInsert e) {
				System.err.println("Error al insertar la ciudad");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha poddo obtener la conexion");
		}
		return insert;
	}

	@Override
	public boolean update(City city) {
		boolean upd = false;
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
				stmt.setString(1, city.getName());
				stmt.setString(2, city.getCountryCode());
				stmt.setString(3, city.getDistrict());
				stmt.setLong(4, city.getPopulation());
				stmt.setLong(5, city.getId());

				int affected = stmt.executeUpdate();
				if(affected==1) {
					System.out.println("Ciudad actualizada con exito");
					upd=true;
				}else {
					throw new ExcUpd();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExcUpd e) {
				System.err.println("Error al actualizar la ciudad");				
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha poddo obtener la conexion");
		}
		return upd;		
	}

	@Override
	public boolean delete(long id) {
		boolean del = false;
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(DELETE);) {
				
				stmt.setLong(1, id);

				int affected = stmt.executeUpdate();
				if(affected==1) {
					System.out.println("Ciudad borrada con exito");
					del=true;
				}else {
					throw new ExcUpd();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExcUpd e) {
				System.err.println("Error al borrar la ciudad");				
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha podido obtener la conexion");
		}
		return del;	
	}

}
