package com.parqueadero.models;

import java.util.Date;

public class FacturaModel {
	
	private int cilindraje;
	private Date horaIngreso;
	private Date horaSalida;
	private int pagoTotal;
	private String placa;
	
	public FacturaModel() {
		
	}
	
	public FacturaModel(int idFactura, int cilindraje, Date horaIngreso, Date horaSalida, int pagoTotal, String placa,
			String tipoVehiculo) {
		super();
		this.cilindraje = cilindraje;
		this.horaIngreso = horaIngreso;
		this.horaSalida = horaSalida;
		this.pagoTotal = pagoTotal;
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Date getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(Date horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	public int getPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(int pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}	
	
}
