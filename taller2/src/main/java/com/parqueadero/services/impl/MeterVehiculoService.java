package com.parqueadero.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.parqueadero.converters.CarroConverter;
import com.parqueadero.converters.MotoConverter;
import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.repositories.CarroRepository;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.MotoRepository;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.services.VigilanteService;

@Service("meterVehiculoService")
public class MeterVehiculoService implements VigilanteService{
	
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
	public void addVehiculo(VehiculoModel vehiculoModel, String tipoVehiculo, int idParqueadero) {
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		if("Carro".equals(tipoVehiculo)) {
			parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()-1);
		}else {
			parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()-1);
		}	
		parqueaderoRepository.save(parqueadero);
		comenzarFactura(vehiculoModel);
		carroRepository.save(carroConverter.model2entity(vehiculoModel));
	}
	
	@Override
	public boolean verificarPlaca(VehiculoModel vehiculoModel, int dia){
		String placa = vehiculoModel.getPlaca();
        char primeraLetra = placa.charAt(0);
        if (primeraLetra == 'A') { 
        	return (1 == dia) || (2 == dia);        	
        }
        return true;
    }
	
	@Override
	public boolean verificarDisponibilidad(String tipoVehiculo) {
		return (celdasParqueadero(1, tipoVehiculo)!=0);
	}
	
	public int celdasParqueadero(int idParqueadero, String tipoVehiculo) {		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		if("Carro".equals(tipoVehiculo)) {
			return parqueadero.getNumCeldasCarro();
		}else {
			return parqueadero.getNumCeldasMoto();
		}
		
	}
	
	@Override
	public void comenzarFactura(VehiculoModel vehiculoModel) {
		Date fechaInicio = new Date();
		FacturaEntity factura = new FacturaEntity();
		factura.setEstado(true);
		factura.setPlaca(vehiculoModel.getPlaca());
		factura.setTipoVehiculo("Carro");
		factura.setHoraIngreso(fechaInicio);
		facturaReposiory.save(factura);
	}
}
