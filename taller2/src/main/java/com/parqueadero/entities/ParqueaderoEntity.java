package com.parqueadero.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parqueadero")
public class ParqueaderoEntity {
	
	@Id
	@GeneratedValue
	@Column(name="idParqueadero")
	private int idParqueadero;
	@Column(name="numCeldasCarro")
	private int numCeldasCarro;
	@Column(name="numCeldasMoto")
	private int numCeldasMoto;
	@Column(name="precioHoraCarro")
	private int precioHoraCarro;
	@Column(name="precioHoraMoto")
	private int precioHoraMoto;
	@Column(name="precioDiaCarro")
	private int precioDiaCarro;
	@Column(name="precioDiaMoto")
	private int precioDiaMoto;
	
	public ParqueaderoEntity() {
		
	}
	
	public ParqueaderoEntity(int idParqueadero, int numCeldasCarro, int numCeldasMoto, int precioHoraCarro,
			int precioHoraMoto, int precioDiaCarro, int precioDiaMoto) {
		super();
		this.idParqueadero = idParqueadero;
		this.numCeldasCarro = numCeldasCarro;
		this.numCeldasMoto = numCeldasMoto;
		this.precioHoraCarro = precioHoraCarro;
		this.precioHoraMoto = precioHoraMoto;
		this.precioDiaCarro = precioDiaCarro;
		this.precioDiaMoto = precioDiaMoto;
	}
	
	public int getIdParqueadero() {
		return idParqueadero;
	}
	
	public void setIdParqueadero(int idParqueadero) {
		this.idParqueadero = idParqueadero;
	}
	
	public int getNumCeldasCarro() {
		return numCeldasCarro;
	}
	
	public void setNumCeldasCarro(int numCeldasCarro) {
		this.numCeldasCarro = numCeldasCarro;
	}
	
	public int getNumCeldasMoto() {
		return numCeldasMoto;
	}
	
	public void setNumCeldasMoto(int numCeldasMoto) {
		this.numCeldasMoto = numCeldasMoto;
	}
	
	public int getPrecioHoraCarro() {
		return precioHoraCarro;
	}
	
	public void setPrecioHoraCarro(int precioHoraCarro) {
		this.precioHoraCarro = precioHoraCarro;
	}
	
	public int getPrecioHoraMoto() {
		return precioHoraMoto;
	}
	
	public void setPrecioHoraMoto(int precioHoraMoto) {
		this.precioHoraMoto = precioHoraMoto;
	}
	
	public int getPrecioDiaCarro() {
		return precioDiaCarro;
	}
	
	public void setPrecioDiaCarro(int precioDiaCarro) {
		this.precioDiaCarro = precioDiaCarro;
	}
	
	public int getPrecioDiaMoto() {
		return precioDiaMoto;
	}
	
	public void setPrecioDiaMoto(int precioDiaMoto) {
		this.precioDiaMoto = precioDiaMoto;
	}
}