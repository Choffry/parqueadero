package com.parqueadero.models;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.parqueadero.converters.CarroConverter;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.repositories.CarroRepository;

public class CarroModel extends VehiculoModel{

	@Autowired
	@Qualifier("carroRepository")
	CarroRepository carroRepository;
	
	@Autowired
	@Qualifier("carroConverter")
	CarroConverter carroConverter;

	private static final String CARRO = "Carro";
	private static final Log LOG = LogFactory.getLog(CarroModel.class);
	
	public CarroModel(String placa) {
		super(placa);
	}
	
	public CarroModel(int idVehiculo, String placa, boolean estado) {
		super(idVehiculo, placa, estado);
	}

	@Override
	public String getTipoVehiculo() {
		return CARRO;
	}

	@Override
	public void parquearVehiculo(ParqueaderoEntity parqueadero, VehiculoModel vehiculoModel) {
		
		LOG.info("METHOD: parquearVehiculo() inicia");
		parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()-1);
		LOG.info("METHOD: Se baja el numero de celdas en el parqueadero");
		carroRepository.save(carroConverter.model2entity(vehiculoModel));
		LOG.info("METHOD: Se guarda el carro");
	}

	@Override
	public int getCilindraje() {
		return 0;
	}	
}
