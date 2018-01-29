package com.example.demo.controladores;

import com.example.demo.Parqueadero;

public class ControladorFacturacion {
	 private String placa;
    private int maxCilindraje;
    private int adicionCilindraje;

    public ControladorFacturacion(String placa) {
        this.placa = placa;
        this.maxCilindraje = 500;
        this.adicionCilindraje = 2000;
    }

    public int generarPago(String tipo, int horas, int dias, int cilindraje){
        Parqueadero parqueadero = new Parqueadero();
        int pagoTotal = 0;
        if (tipo.equals("Carro")){
            pagoTotal += parqueadero.getPrecioHoraCarro()*horas;
            pagoTotal += parqueadero.getPrecioDiaCarro()*dias;
            return pagoTotal;
        }else{
            if (cilindraje >= maxCilindraje){
                pagoTotal+=adicionCilindraje;
            }
            pagoTotal += parqueadero.getPrecioHoraMoto()*horas;
            pagoTotal += parqueadero.getPrecioDiaMoto()*dias;
            return pagoTotal;
        }
    }

    public int numeroDias(int horas){
        int aux = 0;
        if (horas%24 > 9){
            aux = 1;
        }
        return (horas/24) + aux;
    }

    public int numeroHoras(int horas){
        if (horas%24 < 9){
            return horas %24;
        }else{
            return 0;
        }
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
