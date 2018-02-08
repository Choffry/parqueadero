package com.example.demo.servicios;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.parqueadero.models.Calendario;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.services.impl.MeterVehiculoService;

public class MeterVehiculoTest {
	
	MeterVehiculoService meterVehiculo;	
	VehiculoModel vehiculoModel;
	
	@Before
	public void setUp() {
		meterVehiculo = new MeterVehiculoService();
		vehiculoModel = new VehiculoModel(1, "ABC123", 300);
	}
	
	@Test
	public void testVerificarPlaca() {
		Calendario calendario = Mockito.mock(Calendario.class);
		Mockito.when(calendario.getActualDay()).thenReturn(Calendar.TUESDAY);
		boolean respuesta = meterVehiculo.verificarPlaca(vehiculoModel, calendario.getActualDay());
		assertEquals(false, respuesta);
		
		Mockito.when(calendario.getActualDay()).thenReturn(Calendar.MONDAY);
		respuesta = meterVehiculo.verificarPlaca(vehiculoModel, calendario.getActualDay());
		assertEquals(true, respuesta);
	}
	
	/*@Test
	public void testVerificarDisponibilidad() {
		meterVehiculo = new MeterVehiculoService();
		boolean respuesta = meterVehiculo.verificarDisponibilidad();
		assertEquals(true, respuesta);
	}*/
}
