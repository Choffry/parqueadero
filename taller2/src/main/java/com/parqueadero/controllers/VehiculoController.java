package com.parqueadero.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.models.Calendario;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.services.VigilanteService;

@RestController
@RequestMapping("/carro")
public class VehiculoController {
	
	Calendario calendario = new Calendario();
	int dia = calendario.getActualDay();
	private static final int PARQUEADERO = 1;
	
	private static final Log LOG = LogFactory.getLog(VehiculoController.class);
	
	@Autowired
	@Qualifier("vigilanteService")
	private VigilanteService vigilante;
	
	@PostMapping("/addVehiculo")
	public void addVehiculo(@RequestBody VehiculoModel vehiculoModel) {
		
		vigilante.agregarVehiculo(vehiculoModel, PARQUEADERO);
		
		/*LOG.info("METHOD: addContact() -- PARAMS: " + vehiculoModel.toString());
		if(vigilanteEntrada.verificarPlaca(vehiculoModel, dia) && vigilanteEntrada.verificarDisponibilidad(CARRO)) {		
			vigilanteEntrada.addVehiculo(vehiculoModel, PARQUEADERO);
			//LOG.info("Carro ingresado");
		}else {
			//LOG.info("Acceso denegado");
		}*/
	}
	
	@PostMapping("/outVehiculo")
	public void outVehiculo(@RequestBody VehiculoModel vehiculoModel) {
		LOG.info("METHOD: outVehiculo() inicializado");
		//return vigilanteSalida.precioTotal(vehiculoModel);
	}
	
	@GetMapping("/listVehiculo")
	public String listVehiculo() {
		LOG.info("METHOD: listVehiculo() inicializado");
		
		return null;
	}
}
