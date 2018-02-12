package com.parqueadero.models;

import java.util.Date;

public class VehiculosAdentro {
	
	private String placa;
	private String tipo;
	private Date fechaIngreso;
	
	public VehiculosAdentro(String placa, String tipo, Date fechaIngreso) {
		super();
		this.placa = placa;
		this.tipo = tipo;
		this.fechaIngreso = fechaIngreso;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
}
