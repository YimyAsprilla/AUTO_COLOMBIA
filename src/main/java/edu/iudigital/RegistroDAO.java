package edu.iudigital;
import java.sql.Connection;
import java.sql.DriverManager;

public class RegistroDAO {

    public boolean tieneIngresoActivo(String placa) {
        String sql = "SELECT id FROM registro WHERE placa_vehiculo = ? AND salida IS NULL";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al validar ingreso activo: " + e.getMessage());
            return false;
        }
    }

    public void registrarEntrada(String placa, int numeroCelda, LocalDateTime entrada) {
        String sql = "INSERT INTO registro (placa_vehiculo, numero_celda, entrada) VALUES (?, ?, ?)";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ps.setInt(2, numeroCelda);
            ps.setTimestamp(3, Timestamp.valueOf(entrada));

            ps.executeUpdate();
            System.out.println("Entrada registrada correctamente");

        } catch (SQLException e) {
            System.out.println("Error al registrar entrada: " + e.getMessage());
        }
    }

    public Integer obtenerCeldaVehiculoActivo(String placa) {
        String sql = "SELECT numero_celda FROM registro WHERE placa_vehiculo = ? AND salida IS NULL LIMIT 1";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("numero_celda");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener celda activa: " + e.getMessage());
        }

        return null;
    }
    public void registrarSalida(String placa, LocalDateTime salida) {
        String sql = "UPDATE registro SET salida = ? WHERE placa_vehiculo = ? AND salida IS NULL";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(salida));
            ps.setString(2, placa);

            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Salida registrada correctamente");
            } else {
                System.out.println("No existe ingreso activo para ese vehículo");
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar salida: " + e.getMessage());
        }
    }



}
