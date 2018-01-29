package com.example.demo.controladores;

import com.example.demo.Parqueadero;

public class ControladorEntradas {
	private String vehiculo;
    private String placa;
    private Parqueadero parqueadero = new Parqueadero();
    private String carro;

    public ControladorEntradas(String vehiculo, String placa) {
        this.vehiculo = vehiculo;
        this.placa = placa;
    }

    public String verificarPlaca(String dia){
        char letra = placa.charAt(0);
        if (letra == 'A' && !(dia.equals("Lunes") || dia.equals("Domingo"))){
            return "Acceso denegado";
        }else{
            return "Bienvenido";
        }
    }

    public String verificarDisponibilidad(){
        if (vehiculo.equals(carro) && parqueadero.getCeldasCarro()==0){
            return "No hay cupos para carros";
        }else if (vehiculo.equals("Moto") && parqueadero.getCeldasMoto() == 0){
            return "No hay cupos para motos";
        }else {
            return "Puede ingresar el vehiculo";
        }
    }
    
    public void agregarvehiculo(String tipoVehiculo){
        if (tipoVehiculo.equals(carro)){
            parqueadero.setCeldasCarro(parqueadero.getCeldasCarro()-1);
        }else{
            parqueadero.setCeldasMoto(parqueadero.getCeldasMoto()-1);
        }
    }

    public void sacarVehiculo(String tipoVehiculo){
        if (tipoVehiculo.equals(carro)){
            parqueadero.setCeldasCarro(parqueadero.getCeldasCarro()+1);
        }else{
            parqueadero.setCeldasMoto(parqueadero.getCeldasMoto()+1);
        }
    }
    
    public int cantidadCupos(String tipoVehiculo) {
    	if (tipoVehiculo.equals(carro)) {
    		return parqueadero.getCeldasCarro();
    	}else {
    		return parqueadero.getCeldasMoto();
    	}
    }

}
