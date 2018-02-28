package com.parqueadero.validators.entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.parqueadero.exception.ExceptionValidaciones;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.repositories.FacturaReposiory;

public class VehiculoYaIngresado implements ValidacionesEntrada {
	
	@Autowired
	@Qualifier("facturaReposiory")
	FacturaReposiory facturaReposiory;	

	public VehiculoYaIngresado(FacturaReposiory facturaReposiory) {
		this.facturaReposiory = facturaReposiory;
	}

	@Override
	public void validar(VehiculoModel vehiculoModel) {
		if(yaIngresoElVehiculo(vehiculoModel.getPlaca())) {
			throw new ExceptionValidaciones("El vehiculo ya se encuentra adentro");
		}
		
	}
	
	public boolean yaIngresoElVehiculo(String placa) {
		return (facturaReposiory.findByPlacaAndEstado(placa, true) != null);
	}

}
