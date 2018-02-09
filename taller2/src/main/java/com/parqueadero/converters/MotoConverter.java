package com.parqueadero.converters;

import org.springframework.stereotype.Component;

import com.parqueadero.entities.MotoEntity;
import com.parqueadero.models.MotoModel;
import com.parqueadero.models.VehiculoModel;

@Component("motoConverter")
public class MotoConverter {
	public MotoEntity model2entity(VehiculoModel vehiculoModel) {
		MotoEntity motoEntity = new MotoEntity();
		motoEntity.setIdMoto(vehiculoModel.getIdVehiculo());
		motoEntity.setPlaca(vehiculoModel.getPlaca());
		motoEntity.setCilindraje(vehiculoModel.getCilindraje());
		return motoEntity;
	}
	
	public VehiculoModel entity2model(MotoEntity motoEntity) {
		VehiculoModel vehiculoModel = new MotoModel(motoEntity.getPlaca(), motoEntity.getCilindraje());
		vehiculoModel.setIdVehiculo(motoEntity.getIdMoto());
		return vehiculoModel;
	}
}
