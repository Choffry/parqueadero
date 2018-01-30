package com.example.demo;

public class Vehiculo {
	private String placa;
    private int cilindraje;

    public Vehiculo(String placa, int cilindraje) {
        this.placa = placa;
        this.cilindraje = cilindraje;
    }

    public String getPlaca() {
        return placa;
    }

    public int getCilindraje() {
        return cilindraje;
    }
}
