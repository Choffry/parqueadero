package com.parqueadero.models;


public class VehiculoModel {
	
	private String placa;
	private String tipoVehiculo;
    private boolean estado;
    private int cilindraje;
    
    public VehiculoModel() {
		super();
	}

	public VehiculoModel(String placa, String tipoVehiculo, boolean estado, int cilindraje) {
		super();
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.estado = estado;
		this.cilindraje = cilindraje;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
		
}
