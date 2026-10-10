package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.krakedev.entidades.Vehiculo;

public class SelectVehiculo {

    private static final Logger log = LogManager.getLogger(SelectVehiculo.class);

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();

            String sql = "SELECT placa, marca, modelo, anio, precio, color, disponible FROM vehiculos";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            log.info(" VEHICULOS: \n");
            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                    rs.getString ("placa"),
                    rs.getString ("marca"),
                    rs.getString ("modelo"),
                    rs.getInt    ("anio"),
                    rs.getDouble ("precio"),
                    rs.getString ("color"),
                    rs.getBoolean("disponible")
                );
                log.info(v.toString());
            }

        } catch (SQLException e) {
            log.error("Error al listar vehiculos: ", e.getMessage());
        } finally {
            try {
                if (rs  != null) rs.close();
                if (ps  != null) ps.close();
                if (con != null) con.close();
                log.info("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                log.error("Error al cerrar conexión", e.getMessage());
            }
        }
    }
}