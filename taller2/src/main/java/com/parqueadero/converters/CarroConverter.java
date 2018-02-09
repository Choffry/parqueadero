package com.parqueadero.converters;

import org.springframework.stereotype.Component;

import com.parqueadero.entities.CarroEntity;
import com.parqueadero.models.CarroModel;
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
		VehiculoModel vehiculoModel = new CarroModel(carroEntity.getPlaca());
		vehiculoModel.setIdVehiculo(carroEntity.getIdCarro());
		return vehiculoModel;
	}
}
