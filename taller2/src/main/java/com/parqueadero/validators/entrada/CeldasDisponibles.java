package com.parqueadero.validators.entrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.exception.ExceptionValidaciones;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.repositories.ParqueaderoRepository;

public class CeldasDisponibles implements ValidacionesEntrada{
	
	@Autowired
	@Qualifier("parqueaderoRepository")
	ParqueaderoRepository parqueaderoRepository;
	
	private static final String CARRO = "Carro";
	
	public CeldasDisponibles(ParqueaderoRepository parqueaderoRepository) {
		super();
		this.parqueaderoRepository = parqueaderoRepository;
	}

	@Override
	public void validar(VehiculoModel vehiculoModel) {
		
		String tipoVehiculo = vehiculoModel.getTipoVehiculo();

		if(numCeldasParqueadero(1, tipoVehiculo) <= 0) {
			throw new ExceptionValidaciones("Cupos de " + vehiculoModel.getTipoVehiculo() + " lleno");
		}
	}
	
	public int numCeldasParqueadero(int idParqueadero, String tipoVehiculo) {		
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		
		if(CARRO.equals(tipoVehiculo)) {
			return parqueadero.getNumCeldasCarro();			
		}else {
			return parqueadero.getNumCeldasMoto();
		}
	}
}

