package com.parqueadero.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.validators.entrada.CeldasDisponibles;
import com.parqueadero.validators.entrada.PlacaIniciaPorA;
import com.parqueadero.validators.entrada.ValidacionesEntrada;
import com.parqueadero.validators.entrada.VehiculoYaIngresado;

@Configuration	
public class Validaciones {
	
	@Bean
	public List<ValidacionesEntrada> listaValidacionesEntrada(ParqueaderoRepository parqueaderoRepository, FacturaReposiory facturaReposiory){
		List<ValidacionesEntrada> validaciones = new ArrayList<>();
		validaciones.add(new CeldasDisponibles(parqueaderoRepository));
		validaciones.add(new PlacaIniciaPorA());
		validaciones.add(new VehiculoYaIngresado(facturaReposiory));
		return validaciones;
	}
	
}
