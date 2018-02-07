package com.example.demo.servicios;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.parqueadero.models.Calendario;
import com.parqueadero.models.CarroModel;
import com.parqueadero.services.impl.MeterVehiculoService;

public class MeterVehiculoTest {
	
	MeterVehiculoService meterVehiculo;
	CarroModel carroModel;
	
	@Before
	public void setUp() {
		meterVehiculo = new MeterVehiculoService();
		carroModel = new CarroModel(1, "ABC123", true);
	}
	
	@Test
	public void testVerificarPlaca() {
		Calendario calendario = Mockito.mock(Calendario.class);
		Mockito.when(calendario.getActualDay()).thenReturn(Calendar.TUESDAY);
		boolean respuesta = meterVehiculo.verificarPlaca(carroModel);
		assertEquals(true, respuesta);
	}
}
