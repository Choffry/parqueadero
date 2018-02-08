package com.parqueadero.services.impl;

import com.parqueadero.models.CarroModel;
import com.parqueadero.models.MotoModel;
import com.parqueadero.services.VigilanteService;

public class SacarVehiculoService implements VigilanteService{

	@Override
	public boolean verificarPlaca(CarroModel carroModel, int dia) {
		return false;
	}

	@Override
	public boolean verificarDisponibilidad() {
		return false;
	}

	@Override
	public void addCarro(CarroModel carroModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMoto(MotoModel motoModel) {
		// TODO Auto-generated method stub
		
	}
}
