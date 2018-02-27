package com.parqueadero.controllers;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.models.Calendario;
import com.parqueadero.models.FacturaModel;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;
import com.parqueadero.services.VigilanteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoController {
	
	Calendario calendario = new Calendario();
	int dia = calendario.getActualDay();
	private static final int PARQUEADERO = 1;
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";
	
	private static final Log LOG = LogFactory.getLog(ParqueaderoController.class);
	
	@Autowired
	@Qualifier("vigilanteService")
	private VigilanteService vigilante;
	
	@PostMapping("/addCarro")
	public void addCarro(@RequestBody VehiculoModel vehiculoModel) {
		
		vehiculoModel.setTipoVehiculo(CARRO);
		vigilante.agregarVehiculo(vehiculoModel, PARQUEADERO);
	
	}
	
	@PostMapping("/addMoto")
	public void addMoto(@RequestBody VehiculoModel vehiculoModel) {
		
		vehiculoModel.setTipoVehiculo(MOTO);
		vigilante.agregarVehiculo(vehiculoModel, PARQUEADERO);
	
	}
	
	@PutMapping("/outVehiculo")
	public FacturaModel outVehiculo(@RequestBody VehiculoModel vehiculoModel) {
		LOG.info("METHOD: outVehiculo() inicializado");
		return vigilante.sacarVehiculo(vehiculoModel, PARQUEADERO);
	}
	
	@GetMapping("/listVehiculo")
	public List<VehiculosAdentro> listVehiculo() {
		LOG.info("METHOD: listVehiculo() inicializado");		
		return vigilante.listarTodosLosVehiculos();
	}
}
