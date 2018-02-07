package com.parqueadero.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="factura")
public class FacturaEntity {
	
	@Id
	@GeneratedValue
	@Column(name="idFactura")
	private int idFactura;
	@Column(name="tipoVehiculo")
	private String tipoVehiculo;
	@Column(name="placa")
	private String placa;
	@Column(name="cilindraje")
	private int cilindraje;
	@Column(name="horaIngreso")
	private int horaIngreso;
	@Column(name="horaSalida")
	private int horaSalida;
	@Column(name="estado")
	private boolean estado;
	@Column(name="pagoTotal")
	private int pagoTotal;
	
	public FacturaEntity() {
		
	}
	
	public FacturaEntity(int idFactura, String tipoVehiculo, String placa, int cilindraje, int horaIngreso,
			int horaSalida, boolean estado, int pagoTotal) {
		super();
		this.idFactura = idFactura;
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.horaIngreso = horaIngreso;
		this.horaSalida = horaSalida;
		this.estado = estado;
		this.pagoTotal = pagoTotal;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idRegistro) {
		this.idFactura = idRegistro;
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

	public int getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(int horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public int getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(int horaSalida) {
		this.horaSalida = horaSalida;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(int pagoTotal) {
		this.pagoTotal = pagoTotal;
	}
}
