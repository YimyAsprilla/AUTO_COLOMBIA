package edu.iudigital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CeldaDAO {

    public Integer buscarCeldaLibre() {
        String sql = "SELECT numero FROM celda WHERE ocupada = false LIMIT 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("numero");
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar celda libre: " + e.getMessage());
        }

        return null;
    }

    public void ocuparCelda(int numero) {
        String sql = "UPDATE celda SET ocupada = true WHERE numero = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al ocupar celda: " + e.getMessage());
        }
    }

    public void liberarCelda(int numero) {
        String sql = "UPDATE celda SET ocupada = false WHERE numero = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al liberar celda: " + e.getMessage());
        }
    }
}