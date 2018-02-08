package com.parqueadero.converters;

import org.springframework.stereotype.Component;

import com.parqueadero.entities.CarroEntity;
import com.parqueadero.models.VehiculoModel;

@Component("carroConverter")
public class CarroConverter {
	
	public CarroEntity model2entity(VehiculoModel vehiculoModel) {
		CarroEntity carroEntity = new CarroEntity();
		carroEntity.setIdCarro(vehiculoModel.getIdVehiculo());
		carroEntity.setPlaca(vehiculoModel.getPlaca());
		return carroEntity;
	}
	
	public VehiculoModel entity2model(CarroEntity carroEntity) {
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setIdVehiculo(carroEntity.getIdCarro());
		vehiculoModel.setPlaca(carroEntity.getPlaca());
		return vehiculoModel;
	}
}
