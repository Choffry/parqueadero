package com.parqueadero.models;

import java.util.Date;

public class Vehiculo {
	
	private int idVehiculo;
	private String tipoVehiculo;
	private String placa;
    private int cilindraje;
    private Date horaIngreso;
    private Date horaSalida;
    private boolean estado;
    
    public Vehiculo() {
	
	}
    
	public Vehiculo(int idVehiculo, String tipoVehiculo, String placa, int cilindraje, Date horaIngreso,
			Date horaSalida, boolean estado) {
		super();
		this.idVehiculo = idVehiculo;
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.horaIngreso = horaIngreso;
		this.horaSalida = horaSalida;
		this.estado = estado;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", tipoVehiculo=" + tipoVehiculo + ", placa=" + placa
				+ ", cilindraje=" + cilindraje + ", horaIngreso=" + horaIngreso + ", horaSalida=" + horaSalida
				+ ", estado=" + estado + "]";
	}
	
}
