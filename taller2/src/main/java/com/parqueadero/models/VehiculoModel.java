package com.parqueadero.models;

public class VehiculoModel {
	
	private int idVehiculo;
	private String placa;
    private int cilindraje;
    
    public VehiculoModel() {
	
	}
    
	public VehiculoModel(int idVehiculo, String placa, int cilindraje) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	@Override
	public String toString() {
		return "Moto [idVehiculo=" + idVehiculo + ", placa=" + placa + ", cilindraje=" + cilindraje + "]";
	}	
}
