package com.parqueadero.models;

import com.parqueadero.entities.ParqueaderoEntity;

public abstract class VehiculoModel {
	
	private int idVehiculo;
	private String placa;
    private boolean estado; 
    
    public VehiculoModel(String placa) {
		super();
		this.placa = placa;
	}

	public VehiculoModel(int idVehiculo, String placa, boolean estado) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.estado = estado;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
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
	
	public abstract String getTipoVehiculo();
	
	public abstract int getCilindraje();
	
	public abstract void parquearVehiculo(ParqueaderoEntity parqueadero, VehiculoModel vehiculoModel);
}
