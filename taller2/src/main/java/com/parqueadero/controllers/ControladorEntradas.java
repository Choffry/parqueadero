package com.parqueadero.controllers;

import com.parqueadero.entities.ParqueaderoEntity;

public class ControladorEntradas {
	private String vehiculo;
    private String placa;
    private ParqueaderoEntity parqueadero;
    private String carro = "Carro";
    private String moto = "Moto";
    

    public ControladorEntradas(String vehiculo, String placa) {
    	parqueadero = new ParqueaderoEntity(1, 20, 10, 1000, 500, 8000, 4000);
        this.vehiculo = vehiculo;
        this.placa = placa;
    }

    public String verificarPlaca(String dia){
        char letra = placa.charAt(0);
        if (letra == 'A' && !("Lunes".equals(dia) || "Domingo".equals(dia))){
            return "Acceso denegado";
        }else{
            return "Bienvenido";
        }
    }

    public String verificarDisponibilidad(){
        if (vehiculo.equals(carro) && parqueadero.getNumCeldasCarro()==0){
            return "No hay cupos para carros";
        }else if (vehiculo.equals(moto) && parqueadero.getNumCeldasMoto() == 0){
            return "No hay cupos para motos";
        }else {
            return "Puede ingresar el vehiculo";
        }
    }
    
    public void agregarvehiculo(String tipoVehiculo){
        if (tipoVehiculo.equals(carro)){
            parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()-1);
        }else{
            parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()-1);
        }
    }

    public void sacarVehiculo(String tipoVehiculo){
        if (tipoVehiculo.equals(carro)){
            parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()+1);
        }else{
            parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()+1);
        }
    }
    
    public int cantidadCupos(String tipoVehiculo) {
    	if (tipoVehiculo.equals(carro)) {
    		return parqueadero.getNumCeldasCarro();
    	}else {
    		return parqueadero.getNumCeldasMoto();
    	}
    }
}
