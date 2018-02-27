package com.parqueadero.services;

import java.util.List;

import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.FacturaModel;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;

public interface VigilanteService {
	
	void agregarVehiculo(VehiculoModel vehiculoModel, int idParqueadero);
	void comenzarFactura(VehiculoModel vehiculoModel);
	FacturaModel sacarVehiculo(VehiculoModel vehiculoModel, int idParqueadero);
	List<VehiculosAdentro> listarTodosLosVehiculos();
	int precioTotal(FacturaEntity factura, ParqueaderoEntity parqueaderoEntity);
}
