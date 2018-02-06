package com.parqueadero.models;

public class MotoModel {
	
	private int idMoto;
	private String placa;
    private int cilindraje;
    private boolean estado;
    
    public MotoModel() {
	
	}
    
	public MotoModel(int idMoto, String placa, int cilindraje, boolean estado) {
		super();
		this.idMoto = idMoto;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.estado = estado;
	}
	
	public int getIdMoto() {
		return idMoto;
	}

	public void setIdMoto(int idMoto) {
		this.idMoto = idMoto;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Moto [idMoto=" + idMoto + ", placa=" + placa + ", cilindraje=" + cilindraje + ", estado=" + estado
				+ "]";
	}	
}
