package com.parqueadero.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.parqueadero.converters.CarroConverter;
import com.parqueadero.converters.MotoConverter;
import com.parqueadero.entities.CarroEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.Calendario;
import com.parqueadero.models.CarroModel;
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
	public CarroModel addCarro(CarroModel carroModel) {
		CarroEntity carro = carroRepository.save(carroConverter.model2entity(carroModel));
		return carroConverter.entity2model(carro);
	}
	
	@Override
	public boolean verificarPlaca(CarroModel carroModel){
		Calendario calendario = new Calendario();
		int dia = calendario.getActualDay();
		String placa = carroModel.getPlaca();
        char primeraLetra = placa.charAt(0);
        if (primeraLetra == 'A') { 
        	if (1 == dia || 0 == dia){
        		return false;
        	}
        	return true;
        }
        return true;
    }
	
	@Override
	public boolean verificarDisponibilidad(ParqueaderoEntity parqueaderoEntity) {
		return false;
	}
	
	@Override
	public CarroEntity findCarroById(int idCarro) {
		return carroRepository.findByIdCarro(idCarro);
	}
	
	@Override
	public CarroModel findContactByIdModel(int idCarro) {
		return carroConverter.entity2model(findCarroById(idCarro));
	}	
}
