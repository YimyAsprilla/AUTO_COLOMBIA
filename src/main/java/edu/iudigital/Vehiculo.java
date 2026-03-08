package edu.iudigital;


// Se crea la clase vehiculo
public class Vehiculo {

    private String placa;
    private String tipo;
    private String marca;
    private String color;

    // Constructor clase vehiculo
    public Vehiculo(String placa, String tipo, String marca, String color) {
        this.placa = placa;
        this.tipo = tipo;
        this.marca = marca;
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getColor() {
        return color;
    }
}
