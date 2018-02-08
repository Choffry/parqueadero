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
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.services.VigilanteService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	Calendario calendario = new Calendario();
	int dia = calendario.getActualDay();
	private static final String CARRO = "Carro";
	
	private static final Log LOG = LogFactory.getLog(CarroController.class);
	
	@Autowired
	@Qualifier("meterVehiculoService")
	private VigilanteService vigilante;
	
	@PostMapping("/addVehiculo")
	public void addCarro(@RequestBody VehiculoModel vehiculoModel) {
		LOG.info("METHOD: addContact() -- PARAMS: " + vehiculoModel.toString());
		if(vigilante.verificarPlaca(vehiculoModel, dia) && vigilante.verificarDisponibilidad(CARRO)) {		
			vigilante.addVehiculo(vehiculoModel, CARRO, 1);
			LOG.info("Carro ingresado");
		}else {
			LOG.info("Acceso denegado");
		}
	}
	
	@PostMapping("/numCeldas")
	public boolean numCeldas() {
		return vigilante.verificarDisponibilidad(CARRO);
	}
}
