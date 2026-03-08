package edu.iudigital;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        CeldaDAO celdaDAO = new CeldaDAO();
        RegistroDAO registroDAO = new RegistroDAO();

        int opcion;

        do {
            System.out.println("\n=== PARQUEADERO AUTOS COLOMBIA ===");
            System.out.println("1. Registrar entrada del vehículo");
            System.out.println("2. Registrar salida del vehículo");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Placa: ");
                    String placa = sc.nextLine().toUpperCase();

                    if (!vehiculoDAO.existeVehiculo(placa)) {
                        System.out.print("Tipo: ");
                        String tipo = sc.nextLine();

                        System.out.print("Marca: ");
                        String marca = sc.nextLine();

                        System.out.print("Color: ");
                        String color = sc.nextLine();

                        Vehiculo vehiculo = new Vehiculo(placa, tipo, marca, color);
                        vehiculoDAO.guardarVehiculo(vehiculo);
                    }

                    if (registroDAO.tieneIngresoActivo(placa)) {
                        System.out.println("El vehículo ya está dentro del parqueadero");
                        break;
                    }

                    Integer celdaLibre = celdaDAO.buscarCeldaLibre();

                    if (celdaLibre == null) {
                        System.out.println("No hay celdas disponibles");
                    } else {
                        registroDAO.registrarEntrada(placa, celdaLibre, LocalDateTime.now());
                        celdaDAO.ocuparCelda(celdaLibre);
                        System.out.println("Vehículo ingresado en la celda " + celdaLibre);
                    }
                    break;

                case 2:
                    System.out.print("Placa: ");
                    String placaSalida = sc.nextLine().toUpperCase();

                    Integer celdaOcupada = registroDAO.obtenerCeldaVehiculoActivo(placaSalida);

                    if (celdaOcupada == null) {
                        System.out.println("El vehículo no tiene ingreso activo");
                    } else {
                        registroDAO.registrarSalida(placaSalida, LocalDateTime.now());
                        celdaDAO.liberarCelda(celdaOcupada);
                        System.out.println("Salida registrada. Celda liberada: " + celdaOcupada);
                    }
                    break;

                case 3:
                    System.out.println("Programa finalizado");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 3);

        sc.close();
    }
}