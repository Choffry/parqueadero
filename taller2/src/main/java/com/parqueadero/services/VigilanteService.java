package com.parqueadero.services;

import com.parqueadero.entities.CarroEntity;
import com.parqueadero.models.CarroModel;

public interface VigilanteService {
	
	public boolean verificarPlaca(CarroModel carroModel, int dia);
	public boolean verificarDisponibilidad();
	public CarroModel addCarro(CarroModel carroModel);
	CarroEntity findCarroById(int idCarro);
	CarroModel findContactByIdModel(int idCarro);
}
