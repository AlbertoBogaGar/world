package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection2 {
	private final String RUTA="ficheros";
	
	
	private String DB_NAME;
	private String URL;
	private String USER;
	private String PASS;
	private String DRIVER;
	private Connection conn;
	
	//Singleton
	private static DBConnection2 instance;
	
	public static synchronized DBConnection2 getInstance() {
		if(instance==null) {
			instance=new DBConnection2();
		}
		return instance;
	}
	
	private DBConnection2() {
		leerFich();
		System.out.println("Conectando con la bd...");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("Error al registrar el nuevo driver");
			e.printStackTrace();
		}
		try {
			final String URL_DBNAME=URL+DB_NAME;
			conn=DriverManager.getConnection(URL_DBNAME, USER, PASS);
			System.out.println("Exito al conectar con la base de datos");
		} catch (SQLException e) {
			System.err.println("Error al conectar con la bd");
			e.printStackTrace();
		}
	}
	
	public void leerFich() {
		Path file = Paths.get(RUTA+"/rutas2.txt");
		try (BufferedReader br = Files.newBufferedReader(file);){
			String linea = null;
			String[] val = null;
			while((linea=br.readLine()) != null) {
				val=parseo(linea);
			}
			DB_NAME=val[0];
			URL=val[1];
			USER=val[2];
			PASS=val[3];
			DRIVER=val[4];
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String[] parseo(String linea) {
		
		String[]val= linea.split(";");
		return val;
		
	}

	public Connection getConnection() {
		return conn;
		
	}
	public void destroyConnection() {
		System.out.println("Cerrando la conexion con la base de datos");
		try {
			if(conn!=null&&!conn.isClosed()) {
				conn.close();
			}
			System.out.println("Conexion cerrada con exito");
		} catch (SQLException e) {
			System.err.println("Error al cerrar la conexion con la bd");
			e.printStackTrace();
		}
	}
}
