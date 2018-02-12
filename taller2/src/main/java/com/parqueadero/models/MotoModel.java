package com.parqueadero.models;

import org.springframework.beans.factory.annotation.Autowired;

import com.parqueadero.converters.MotoConverter;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.repositories.MotoRepository;
import com.parqueadero.repositories.ParqueaderoRepository;

public class MotoModel extends VehiculoModel{
	
	@Autowired
	MotoRepository motoRepository;
	
	@Autowired
	MotoConverter motoConverter;
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;

	private static final String MOTO = "Moto";
	private int cilindraje;
	
	public MotoModel(String placa, int cilingraje) {
		super(placa);
		this.cilindraje=cilingraje;
	}
	
	public MotoModel(int idVehiculo, String placa, boolean estado, int cilingraje) {
		super(idVehiculo, placa, estado);
		this.cilindraje=cilingraje;
	}

	@Override
	public String getTipoVehiculo() {
		return MOTO;
	}
	
	@Override
	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	@Override
	public void parquearVehiculo(ParqueaderoEntity parqueadero, VehiculoModel vehiculoModel) {
		
		parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()-1);
		motoRepository.save(motoConverter.model2entity(vehiculoModel));
		parqueaderoRepository.save(parqueadero);
		
	}
}
