package com.parqueadero.converters;

import org.springframework.stereotype.Component;

import com.parqueadero.entities.CarroEntity;
import com.parqueadero.models.CarroModel;

@Component("carroConverter")
public class CarroConverter {
	
	public CarroEntity model2entity(CarroModel carroModel) {
		CarroEntity carroEntity = new CarroEntity();
		carroEntity.setIdCarro(carroModel.getIdCarro());
		carroEntity.setPlaca(carroModel.getPlaca());
		carroEntity.setEstado(carroModel.isEstado());
		return carroEntity;
	}
	
	public CarroModel entity2model(CarroEntity carroEntity) {
		CarroModel carroModel = new CarroModel();
		carroModel.setIdCarro(carroEntity.getIdCarro());
		carroModel.setPlaca(carroEntity.getPlaca());
		carroModel.setEstado(carroEntity.isEstado());
		return carroModel;
	}
}
