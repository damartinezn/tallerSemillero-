package com.clearminds.damn.bdd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.clearminds.damn.excepciones.BDDException;

public class ConexionBDD {

	
	public static String leerPropiedad(String nombrePropiedad){
		Properties p = new Properties();		
		try {
			p.load(new FileReader("./conexion.properties"));
			return p.getProperty(nombrePropiedad);
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			System.out.println("No se encontro el archivo");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problemas al leer el archivo");
			return null;
		}
	}
	
	public static Connection obtenerConexion() throws BDDException{
		String user  = leerPropiedad("usuario");
		String pass = leerPropiedad("password");
		String url = leerPropiedad("urlConexion");		
		Connection conn = null;
		if (user != null && pass != null && url != null) {
			try {
				conn = DriverManager.getConnection(url, user, pass);				
			} catch (SQLException e) {				
				e.printStackTrace();
				throw new BDDException("No se pudo conectar a la base de datos");
			}
		}
		return conn;
	}
	
	
}
