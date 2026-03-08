package edu.iudigital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO {

    // Validamos si el vehiculo existe en la base de datos
    public boolean existeVehiculo(String placa) {
        String sql = "SELECT placa FROM vehiculo WHERE placa = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al consultar vehículo: " + e.getMessage());
            return false;
        }
    }

    // Guardamos el vehiculo en la base de datos
    public void guardarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculo (placa, tipo, marca, color) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, vehiculo.getPlaca());
            ps.setString(2, vehiculo.getTipo());
            ps.setString(3, vehiculo.getMarca());
            ps.setString(4, vehiculo.getColor());

            ps.executeUpdate();
            System.out.println("Vehículo registrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al guardar vehículo: " + e.getMessage());
        }
    }
}
