package com.parqueadero.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.parqueadero.converters.CarroConverter;
import com.parqueadero.converters.MotoConverter;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.repositories.CarroRepository;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.MotoRepository;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.services.VigilanteService;

@Service("sacarVehiculoService")
public class SacarVehiculoService implements VigilanteService{
	
	@Autowired
	@Qualifier("carroRepository")
	private CarroRepository carroRepository;
	
	@Autowired
	@Qualifier("motoRepository")
	private MotoRepository motoRepository;
	
	@Autowired
	@Qualifier("facturaReposiory")
	private FacturaReposiory facturaReposiory;
	
	@Autowired
	@Qualifier("parqueaderoRepository")
	private ParqueaderoRepository parqueaderoRepository;
	
	@Autowired
	@Qualifier("carroConverter")
	private CarroConverter carroConverter;
	
	@Autowired
	@Qualifier("motoConverter")
	private MotoConverter motoConverter;

	@Override
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addVehiculo(VehiculoModel vehiculoModel, int idParqueadero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean verificarDisponibilidad(String tipoVehiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int precioTotal(VehiculoModel vehiculoModel, ParqueaderoEntity parqueaderoEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void comenzarFactura(VehiculoModel vehiculoModel) {
		// TODO Auto-generated method stub
		
	}
	
	
}
