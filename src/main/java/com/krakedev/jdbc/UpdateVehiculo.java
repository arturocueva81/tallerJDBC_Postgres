package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 

public class UpdateVehiculo {

    private static final Logger log = LogManager.getLogger(UpdateVehiculo.class);

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.getConexion();

            String sql = "UPDATE vehiculos SET precio = ?, color = ?, disponible = ?, kilometraje = ? WHERE placa = ?";
            ps = con.prepareStatement(sql);
           
            ps.setDouble (1, 22000.00);
            ps.setString (2, "Azul");
            ps.setBoolean(3, false);
     
            ps.setInt(4, 20000); //parte12 actualiza el kilometraje
            
            ps.setString (5, "ABC-1234");

            int filas = ps.executeUpdate();
            log.info("Vehiculo actualizado. Filas afectadas: "+ filas);

        } catch (SQLException e) {
            log.error("Error al actualizar vehiculo: ", e.getMessage());
        } finally {
            try {
                con.close();
                log.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                log.error("Error al cerrar conexión: ", e.getMessage());
            }
        }
    }
}