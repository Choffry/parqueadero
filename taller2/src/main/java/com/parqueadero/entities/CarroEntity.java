package com.parqueadero.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carro")
public class CarroEntity {
	
	@Id
	@GeneratedValue
	@Column(name="idCarro")
	private int idCarro;
	@Column(name="placa")
	private String placa;
	
	public CarroEntity() {
		
	}
	
	public CarroEntity(int idCarro, String placa) {
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
}
