package com.parqueadero.services;

import java.util.List;

import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.FacturaModel;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;

public interface VigilanteService {
	
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia);
	void agregarVehiculo(VehiculoModel vehiculoModel, int idParqueadero);
	boolean verificarDisponibilidad(String tipoVehiculo);
	void comenzarFactura(VehiculoModel vehiculoModel);
	FacturaModel sacarVehiculo(VehiculoModel vehiculoModel, int idParqueadero);
	List<VehiculosAdentro> listarTodosLosVehiculos();
	int precioTotal(FacturaEntity factura, ParqueaderoEntity parqueaderoEntity);
}
