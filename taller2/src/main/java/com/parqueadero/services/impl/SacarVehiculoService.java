package com.parqueadero.services.impl;

import com.parqueadero.entities.CarroEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.CarroModel;
import com.parqueadero.services.VigilanteService;

public class SacarVehiculoService implements VigilanteService{

	@Override
	public CarroModel addCarro(CarroModel carroModel) {
		return null;
	}

	@Override
	public CarroEntity findCarroById(int idCarro) {
		return null;
	}

	@Override
	public CarroModel findContactByIdModel(int idCarro) {
		return null;
	}

	@Override
	public boolean verificarPlaca(CarroModel carroModel) {
		return false;
	}

	@Override
	public boolean verificarDisponibilidad(ParqueaderoEntity parqueaderoEntity) {
		return false;
	}
	
}
