package com.parqueadero.services;



import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.VehiculoModel;

public interface VigilanteService {
	
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia);
	void addVehiculo(VehiculoModel vehiculoModel, int idParqueadero);
	boolean verificarDisponibilidad(String tipoVehiculo);
	int precioTotal(VehiculoModel vehiculoModel, ParqueaderoEntity parqueaderoEntity);
	void comenzarFactura(VehiculoModel vehiculoModel);	
}
