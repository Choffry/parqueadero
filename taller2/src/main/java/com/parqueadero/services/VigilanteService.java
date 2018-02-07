package com.parqueadero.services;

import com.parqueadero.entities.CarroEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.CarroModel;

public interface VigilanteService {
	
	public boolean verificarPlaca(CarroModel carroModel);
	public boolean verificarDisponibilidad(ParqueaderoEntity parqueaderoEntity);
	public CarroModel addCarro(CarroModel carroModel);
	CarroEntity findCarroById(int idCarro);
	CarroModel findContactByIdModel(int idCarro);
}
