package com.parqueadero.controllers;

import java.util.Date;

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
public class VehiculoController {
	
	Calendario calendario = new Calendario();
	int dia = calendario.getActualDay();
	private static final String CARRO = "Carro";
	private static final int PARQUEADERO = 1;
	
	private static final Log LOG = LogFactory.getLog(VehiculoController.class);
	
	@Autowired
	@Qualifier("meterVehiculoService")
	private VigilanteService vigilanteEntrada;
	
	@Autowired
	@Qualifier("sacarVehiculoService")
	private VigilanteService vigilanteSalida;
	
	@PostMapping("/addVehiculo")
	public void addVehiculo(@RequestBody VehiculoModel vehiculoModel) {
		LOG.info("METHOD: addContact() -- PARAMS: " + vehiculoModel.toString());
		if(vigilanteEntrada.verificarPlaca(vehiculoModel, dia) && vigilanteEntrada.verificarDisponibilidad(CARRO)) {		
			vigilanteEntrada.addVehiculo(vehiculoModel, PARQUEADERO);
			LOG.info("Carro ingresado");
		}else {
			LOG.info("Acceso denegado");
		}
	}
	
	@PostMapping("/outVehiculo")
	public int outVehiculo(@RequestBody VehiculoModel vehiculoModel) {
		LOG.info("METHOD: outVehiculo() inicializado");
		//return vigilanteSalida.precioTotal(vehiculoModel);
		return 0;
	}
	
	@PostMapping("/numCeldas")
	public boolean numCeldas() {
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2018, 9, 6, 3, 5);
		LOG.info("Fecha en milisegundos: " + fecha.getTime());
		return vigilanteEntrada.verificarDisponibilidad(CARRO);
	}
}
