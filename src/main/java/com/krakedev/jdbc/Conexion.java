package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {
	//Parte6 Clase unitaria de conexion
	private static final Logger log = LogManager.getLogger(Conexion.class);
	private static final String URL ="jdbc:postgresql://localhost:5432/postgres";
	private static final String USUARIO ="postgres";
	private static final String PASSWORD ="admin1810";
	
	public static Connection getConexion() {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            log.info("Conexion exitosa.\n");
            return con;
            
        } catch (SQLException e) {
            log.error("Error de conexion: {}", e.getMessage());
            throw new RuntimeException("No se pudo conectar", e);
        }
    }
}