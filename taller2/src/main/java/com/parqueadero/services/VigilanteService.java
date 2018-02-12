package com.parqueadero.services;



import java.util.Date;
import java.util.List;

import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;

public interface VigilanteService {
	
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia);
	void addVehiculo(VehiculoModel vehiculoModel, int idParqueadero);
	boolean verificarDisponibilidad(String tipoVehiculo);
	void comenzarFactura(VehiculoModel vehiculoModel);
	void sacarVehiculo(VehiculoModel vehiculoModel, int idParqueadero);
	int precioTotal(Date fechaEntrada, Date fechaSalida, VehiculoModel vehiculoModel,
			ParqueaderoEntity parqueaderoEntity);
	List<VehiculosAdentro> listAllVehiculos();
}
