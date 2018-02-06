package com.parqueadero.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="moto")
public class MotoEntity {
 
	@Id
	@GeneratedValue
	@Column(name="idMoto")
	private int idMoto;
	@Column(name="placa")
	private String placa;
	@Column(name="cilindraje")
	private String cilindraje;
	@Column(name="estado")
	private boolean estado;
	
	public MotoEntity() {
		
	}
	
	public MotoEntity(int idMoto, String placa, String cilindraje, boolean estado) {
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

	public String getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
