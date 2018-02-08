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
@RequestMapping("/moto")
public class MotoController {
	
	Calendario calendario = new Calendario();
	int dia = calendario.getActualDay();
	private static final String MOTO = "Moto";
	
	private static final Log LOG = LogFactory.getLog(MotoController.class);
	
	@Autowired
	@Qualifier("meterVehiculoService")
	private VigilanteService vigilante;
	
	@PostMapping("/addVehiculo")
	public void addVehiculo(@RequestBody VehiculoModel vehiculoModel) {
		LOG.info("METHOD: addContact() -- PARAMS: " + vehiculoModel.toString());
		if(vigilante.verificarPlaca(vehiculoModel, dia) && vigilante.verificarDisponibilidad(MOTO)) {		
			vigilante.addVehiculo(vehiculoModel, MOTO, 1);
			LOG.info("Moto ingresada");
		}else {
			LOG.info("Acceso denegado");
		}
	}
	
	@PostMapping("/numCeldas")
	public boolean numCeldas() {
		return vigilante.verificarDisponibilidad(MOTO);
	}
}
