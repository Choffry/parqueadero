package com.parqueadero.models;

public class CarroModel {
	
	private int idCarro;
	private String placa;
	
	public CarroModel() {
		
	}
	
	public CarroModel(int idCarro, String placa) {
		super();
		this.idCarro = idCarro;
		this.placa = placa;
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

	@Override
	public String toString() {
		return "Carro [idCarro=" + idCarro + ", placa=" + placa + "]";
	}
}
