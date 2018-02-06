package com.parqueadero.models;

public class CarroModel {
	
	private int idCarro;
	private String placa;
	private boolean estado;
	
	public CarroModel() {
		
	}
	
	public CarroModel(int idCarro, String placa, boolean estado) {
		super();
		this.idCarro = idCarro;
		this.placa = placa;
		this.estado = estado;
	}

	public int getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Carro [idCarro=" + idCarro + ", placa=" + placa + ", estado=" + estado + "]";
	}
}
