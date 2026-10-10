package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteVehiculo {

    private static final Logger log = LogManager.getLogger(DeleteVehiculo.class);

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.getConexion();

            String sql = "DELETE FROM vehiculos WHERE placa = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "ABC-1234");

            int filas = ps.executeUpdate();
            log.info("Vehiculo eliminado. Filas afectadas: ", filas);

        } catch (SQLException e) {
            log.error("Error al eliminar vehiculo: ", e.getMessage());
        } finally {
            try {
                if (ps  != null) ps.close();
                if (con != null) con.close();
                log.info("Conexion cerrada correctamente.");
            } catch (SQLException e) {
                log.error("Error al cerrar conexion: ", e.getMessage());
            }
        }
    }
}