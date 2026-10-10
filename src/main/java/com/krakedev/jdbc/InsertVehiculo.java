package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.entidades.Vehiculo;

public class InsertVehiculo {
	//parte 7 CRUD JDBC Manual
	private static final Logger log = LogManager.getLogger(InsertVehiculo.class);

	public static void main(String[] args) {
		Vehiculo v = new Vehiculo("ABC-1234", "Toyota", "Corolla", 2022, 25000.00, "Rojo", true);
		Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = Conexion.getConexion();

            String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, precio, color, disponible) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setInt   (4, v.getAnio());
            ps.setDouble(5, v.getPrecio());
            ps.setString(6, v.getColor());
            ps.setBoolean(7, v.isDisponible());

            int filas = ps.executeUpdate();
            log.info("Vehiculo insertado. Filas afectadas: ", filas);

        } catch (SQLException e) {
            log.error("Error al insertar vehiculo: ", e.getMessage());
        } finally {
            try {
                if (ps  != null) ps.close();
                if (con != null) con.close();
                log.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                log.error("Error al cerrar conexión: ", e.getMessage());
            }
        }
    }

}
