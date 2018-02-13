package com.parqueadero.models;


public class VehiculoModel {
	
	private int idVehiculo;
	private String placa;
	private String tipoVehiculo;
    private boolean estado;
    private int cilindraje;
    
    public VehiculoModel() {
		super();
	}

	public VehiculoModel(int idVehiculo, String placa, String tipoVehiculo, boolean estado, int cilindraje) {
		super();
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.estado = estado;
		this.cilindraje = cilindraje;
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
	
	
	//public abstract int getCilindraje();
	
	//public abstract void parquearVehiculo(ParqueaderoEntity parqueadero, VehiculoModel vehiculoModel);
}
