package com.example.demo;

public class Moto {
	private String placa;
    private int cilindraje;

    public Moto(String placa, int cilindraje) {
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
