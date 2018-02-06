package com.parqueadero.entities;

import java.util.Date;

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
	@Column(name="idRegistro")
	private int idRegistro;
	@Column(name="tipoVehiculo")
	private String tipoVehiculo;
	@Column(name="placa")
	private String placa;
	@Column(name="cilindraje")
	private int cilindraje;
	@Column(name="horaIngreso")
	private Date horaIngreso;
	@Column(name="horaSalida")
	private Date horaSalida;
	@Column(name="estado")
	private boolean estado;
	@Column(name="pagoTotal")
	private int pagoTotal;
	
	public FacturaEntity() {
		
	}
	
	public FacturaEntity(int idRegistro, String tipoVehiculo, String placa, int cilindraje, Date horaIngreso,
			Date horaSalida, boolean estado, int pagoTotal) {
		super();
		this.idRegistro = idRegistro;
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.horaIngreso = horaIngreso;
		this.horaSalida = horaSalida;
		this.estado = estado;
		this.pagoTotal = pagoTotal;
	}

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
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

	public int getPagoTotal() {
		return pagoTotal;
	}

	public void setPagoTotal(int pagoTotal) {
		this.pagoTotal = pagoTotal;
	}
}
