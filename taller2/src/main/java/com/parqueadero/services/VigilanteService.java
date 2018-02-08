package com.parqueadero.services;

import com.parqueadero.models.CarroModel;
import com.parqueadero.models.MotoModel;

public interface VigilanteService {
	
	public boolean verificarPlaca(CarroModel carroModel, int dia);
	public boolean verificarDisponibilidad();
	public void addCarro(CarroModel carroModel);
	public void addMoto(MotoModel motoModel);
}
