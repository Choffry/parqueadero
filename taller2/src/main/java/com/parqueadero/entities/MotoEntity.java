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
	private int cilindraje;
	
	public MotoEntity() {
		
	}
	
	public MotoEntity(int idMoto, String placa, int cilindraje) {
		super();
		this.idMoto = idMoto;
		this.placa = placa;
		this.cilindraje = cilindraje;
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
}
