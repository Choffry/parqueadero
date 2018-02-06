package com.parqueadero.services;

import com.parqueadero.entities.CarroEntity;
import com.parqueadero.models.CarroModel;

public interface VigilanteService {
	public CarroModel addCarro(CarroModel carroModel);

	CarroEntity findCarroById(int idCarro);

	CarroModel findContactByIdModel(int idCarro);
}
