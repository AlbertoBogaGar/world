
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private final static String RUTA = "ficheros";
	Path ruta = Paths.get(RUTA + "/rutas.txt");
	
	private String DB_NAME;
	private String URL;
	private String USER;
	private String PASS;
	private String DRIVER ;
	private Connection conn;


	// Singleton
	private static DBConnection instance;

	public static synchronized DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	private DBConnection() {
		leerFichero();
		System.out.println("Conectando con la BD...");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Error al registrar el nuevo driver");
			e.printStackTrace();
		}
		try {
			String URL_DB_NAME=URL+DB_NAME;
			conn = DriverManager.getConnection(URL_DB_NAME, USER, PASS);
			System.out.println("Conexion con exito");
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos");
			e.printStackTrace();
		}
	}
	public void leerFichero() {
		try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8);) {
			String linea = null;
			while ((linea = br.readLine()) != null) {
				parseo(linea);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseo(String linea) {
		
		String[] keyval = linea.split("=");
		String key = keyval[0].trim();
		
		String val = keyval.length > 1 ? keyval[1].trim() : "";
		
		switch (key) {
		case "DBName":
			DB_NAME = val;
			break;
		case "URL":
			URL = val;
			break;
		case "USER":
			USER = val;
			break;
		case "PASS":
			PASS = val;
			break;
		case "Driver":
			DRIVER = val;
			break;
		}
		
	}


	public Connection getConnection() {
		return conn;
	}

	public void destroyConnection() {
		System.out.println("Cerrando la conexion con la BD...");
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			System.out.println("Conexion cerrada con exito");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			instance = null;
		}
	}

}
