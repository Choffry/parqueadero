package com.parqueadero.services.impl;

import com.parqueadero.models.CarroModel;
import com.parqueadero.services.VigilanteService;

public class SacarVehiculoService implements VigilanteService{

	@Override
	public CarroModel addCarro(CarroModel carroModel) {
		return null;
	}

	@Override
	public boolean verificarPlaca(CarroModel carroModel, int dia) {
		return false;
	}

	@Override
	public boolean verificarDisponibilidad() {
		return false;
	}
}
