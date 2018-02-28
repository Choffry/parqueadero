package com.parqueadero.validators.salida;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.parqueadero.exception.ExceptionValidaciones;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.services.impl.VigilanteServiceImpl;

public class VehiculoNoExiste implements ValidacionesSalida{
	
	@Autowired
	@Qualifier("facturaReposiory")
	FacturaReposiory facturaReposiory;
	
	private static final Log LOG = LogFactory.getLog(VigilanteServiceImpl.class);

	public VehiculoNoExiste(FacturaReposiory facturaReposiory) {
		LOG.info("Constructor");
		this.facturaReposiory = facturaReposiory;
	}

	@Override
	public void validar(VehiculoModel vehiculoModel) {
		LOG.info("Comenzando a verificar");
		if(!vehiculoSinIngrersar(vehiculoModel.getPlaca())) {
			LOG.info("esta entrando");
			throw new ExceptionValidaciones("El vehiculo no existe");
		}
		
	}
	
	public boolean vehiculoSinIngrersar(String placa) {
		LOG.info("vehiculos sin ingresar");
		return (facturaReposiory.findByPlacaAndEstado(placa, true) != null);
	}
}
