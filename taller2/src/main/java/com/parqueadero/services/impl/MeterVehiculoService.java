package com.parqueadero.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.parqueadero.converters.CarroConverter;
import com.parqueadero.converters.MotoConverter;
import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.CarroModel;
import com.parqueadero.models.MotoModel;
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
	public void addCarro(CarroModel carroModel) {
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()-1);
		parqueaderoRepository.save(parqueadero);
		comenzarFactura(carroModel);
		carroRepository.save(carroConverter.model2entity(carroModel));
	}
	
	@Override
	public void addMoto(MotoModel motoModel) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean verificarPlaca(CarroModel carroModel, int dia){
		String placa = carroModel.getPlaca();
        char primeraLetra = placa.charAt(0);
        if (primeraLetra == 'A') { 
        	return (1 == dia) || (2 == dia);        	
        }
        return true;
    }
	
	@Override
	public boolean verificarDisponibilidad() {
		return (celdasParqueadero(1)!=0);
	}
	
	public int celdasParqueadero(int idParqueadero) {		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(idParqueadero);
		return parqueadero.getNumCeldasCarro();
	}
	
	public void comenzarFactura(CarroModel carroModel) {
		Date fechaInicio = new Date();
		FacturaEntity factura = new FacturaEntity();
		factura.setEstado(true);
		factura.setPlaca(carroModel.getPlaca());
		factura.setTipoVehiculo("Carro");
		factura.setHoraIngreso(fechaInicio);
		facturaReposiory.save(factura);
	}
}
