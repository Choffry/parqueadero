package com.parqueadero.components;

import org.springframework.stereotype.Component;

import com.parqueadero.entities.MotoEntity;
import com.parqueadero.models.MotoModel;

@Component("motoConverter")
public class MotoConverter {
	public MotoEntity model2entity(MotoModel motoModel) {
		MotoEntity motoEntity = new MotoEntity();
		motoEntity.setIdMoto(motoModel.getIdMoto());
		motoEntity.setPlaca(motoModel.getPlaca());
		motoEntity.setCilindraje(motoModel.getCilindraje());
		motoEntity.setEstado(motoModel.isEstado());
		return motoEntity;
	}
	
	public MotoModel entity2model(MotoEntity motoEntity) {
		MotoModel motoModel = new MotoModel();
		motoModel.setIdMoto(motoEntity.getIdMoto());
		motoModel.setPlaca(motoEntity.getPlaca());
		motoModel.setCilindraje(motoEntity.getCilindraje());
		motoModel.setEstado(motoEntity.isEstado());
		return motoModel;
	}
}
