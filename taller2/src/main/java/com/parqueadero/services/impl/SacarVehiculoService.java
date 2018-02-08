package com.parqueadero.services.impl;

import com.parqueadero.models.VehiculoModel;
import com.parqueadero.services.VigilanteService;

public class SacarVehiculoService implements VigilanteService{

	@Override
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia) {
		return false;
	}


	@Override
	public void addVehiculo(VehiculoModel vehiculoModel, String tipoVehiculo, int idParqueadero) {
		
	}


	@Override
	public boolean verificarDisponibilidad(String tipoVehiculo) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void comenzarFactura(VehiculoModel vehiculoModel, String tipoVehiculo) {
		// TODO Auto-generated method stub
		
	}
}
