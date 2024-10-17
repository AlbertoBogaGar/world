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
import model.Country;
import util.DBConnection;

public class CountryDAOMySQL implements CountryDAO {
	private final String SELECT = "SELECT code, name, continent, region, population FROM country";
	private final String SELECT_ID = "SELECT code, name, continent, region, population FROM country WHERE code=?";
	private final String INSERT = "INSERT INTO country( code,name, continent, region, population) VALUES(?,?,?,?,?) ";
	private final String UPDATE="UPDATE country SET name=?, continent=?, region=?, population=? WHERE code=?";
	private final String DELETE="DELETE FROM country WHERE code=?";
	@Override
	public List<Country> select() {
		List<Country> countries = new ArrayList<>();
		System.out.println("Obteniendo los paises...");

		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(SELECT);) {

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Country c = new Country(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5));
					countries.add(c);
				}

			} catch (SQLException e) {
				System.out.println("Error al obtener los paises");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha podido obtener la conexion");
		}

		System.out.println("Paises obtenidas con exito!!");
		return countries;
	}

	@Override
	public Country selectById(String code) {
		Country countries = null;
		System.out.println("Obteniendo el pais...");

		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(SELECT_ID);) {
				stmt.setString(1, code);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					countries = new Country(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5));
				}

			} catch (SQLException e) {
				System.out.println("Error al obtener el pais");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha podido obtener la conexion");
		}

		System.out.println("Pais obtenido con exito!!");
		return countries;
	}

	@Override
	public boolean insert(Country country) {
		boolean insert = false;
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(INSERT);) {
				stmt.setString(1, country.getCode());
				stmt.setString(2, country.getName());
				stmt.setString(3, country.getContinent());
				stmt.setString(4, country.getRegion());
				stmt.setLong(5, country.getPopulation());
				int affected = stmt.executeUpdate();
				if(affected==1) {
					System.out.println("Pais insertado con exito");
					insert=true;
				}else {
					throw new ExcInsert();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExcInsert e) {
				System.err.println("Error al insertar el pais");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha poddo obtener la conexion");
		}
		return insert;
	}

	@Override
	public boolean update(Country country) {
		boolean upd = false;
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
				stmt.setString(1, country.getName());
				stmt.setString(2, country.getContinent());
				stmt.setString(3, country.getRegion());
				stmt.setLong(4, country.getPopulation());
				stmt.setString(5, country.getCode());
				int affected = stmt.executeUpdate();
				if(affected==1) {
					System.out.println("Pais actualizado con exito");
					upd=true;
				}else {
					throw new ExcUpd();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExcUpd e) {
				System.err.println("Error al actualizar el pais");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha poddo obtener la conexion");
		}
		return upd;
	}

	@Override
	public boolean delete(String code) {
		boolean del = false;
		Connection conn = DBConnection.getInstance().getConnection();
		if (conn != null) {
			try (PreparedStatement stmt = conn.prepareStatement(DELETE);) {
				
				stmt.setString(1, code);
				int affected = stmt.executeUpdate();
				if(affected==1) {
					System.out.println("Pais borrado con exito");
					del=true;
				}else {
					throw new Exception();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Error al borrar el pais");
				e.printStackTrace();
			}
		} else {
			System.err.println("No se ha poddo obtener la conexion");
		}
		return del;
	}

}
