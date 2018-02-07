package com.parqueadero.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;	
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.models.Calendario;
import com.parqueadero.models.CarroModel;
import com.parqueadero.services.VigilanteService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	Calendario calendario = new Calendario();
	int dia = calendario.getActualDay();
	
	private static final Log LOG = LogFactory.getLog(CarroController.class);
	
	@Autowired
	@Qualifier("meterVehiculoService")
	private VigilanteService vigilante;
	
	@PostMapping("/addCarro")
	public void addCarro(@RequestBody CarroModel carroModel) {
		LOG.info("METHOD: addContact() -- PARAMS: " + carroModel.toString());
		if(vigilante.verificarPlaca(carroModel, dia)) {
			vigilante.addCarro(carroModel);
		}
	}
}
