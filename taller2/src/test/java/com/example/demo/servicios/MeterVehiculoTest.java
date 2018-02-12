package com.example.demo.servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.Taller2Application;
import com.parqueadero.entities.CarroEntity;
import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.MotoEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.CarroModel;
import com.parqueadero.models.MotoModel;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;
import com.parqueadero.repositories.CarroRepository;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.MotoRepository;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.services.VigilanteService;
import com.parqueadero.services.impl.MeterVehiculoService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=Taller2Application.class)
public class MeterVehiculoTest {
	
	@Autowired
	MeterVehiculoService meterVehiculo;
	
	@Autowired
	FacturaReposiory facturaReposiory;
	
	@Autowired
	CarroRepository carroRepository;
	
	@Autowired
	MotoRepository motoRepository;
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;

	@Test
	public void testVerificarPlacaQueEmpiezaPorAyEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new CarroModel("AAA111");
		
		boolean placaIniciaPorA = meterVehiculo.verificarPlaca(vehiculoModel, Calendar.MONDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueEmpiezaPorAyNoEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new CarroModel("AAA111");
		
		boolean placaIniciaPorA = meterVehiculo.verificarPlaca(vehiculoModel, Calendar.WEDNESDAY);
		
		assertFalse(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueNoEmpiezaPorAYEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new CarroModel("BAA111");
		
		boolean placaIniciaPorA = meterVehiculo.verificarPlaca(vehiculoModel, Calendar.MONDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueNoEmpiezaPorAYNoEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new CarroModel("BAA111");
		
		boolean placaIniciaPorA = meterVehiculo.verificarPlaca(vehiculoModel, Calendar.WEDNESDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarCeldasDisponiblesParaEnCarro() {
		
		VehiculoModel vehiculoModel = new CarroModel("PPP456");
		
		int respuesta = meterVehiculo.celdasParqueadero(1, vehiculoModel.getTipoVehiculo());
		
		assertEquals(20,  respuesta);
	}
	
	@Test
	public void testVerificarCeldasDisponiblesEnMoto() {
		
		VehiculoModel vehiculoModel = new MotoModel("PPP456", 300);
		
		int respuesta =  meterVehiculo.celdasParqueadero(1, vehiculoModel.getTipoVehiculo());
		
		assertEquals(10, respuesta);
	}
	
	@Test
	public void testVerificarIngresoDeFacturaABaseDeDatos() {
		
		VehiculoModel vehiculoModel = new CarroModel("IAP588");
		
		meterVehiculo.comenzarFactura(vehiculoModel);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("IAP588", true);	
		assertTrue(null != factura);
		
		facturaReposiory.delete(factura);
	}
	
	@Test
	public void testVerificarIngresoDeCarroAlParqueadero() {
		
		VehiculoModel carroModel = new CarroModel("IAP588");
		
		meterVehiculo.addVehiculo(carroModel, 1);
		
		CarroEntity carro = carroRepository.findByPlaca("IAP588");
		assertTrue(null != carro);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("IAP588", true);
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()+1);
		carroRepository.delete(carro);
		facturaReposiory.delete(factura);
		parqueaderoRepository.save(parqueadero);
	}
	
	@Test
	public void testVerificarIngresoDeMotoAlParqueadero() {
		
		VehiculoModel vehiculoModel = new MotoModel("WSW04D", 300);
		
		meterVehiculo.addVehiculo(vehiculoModel, 1);
		
		MotoEntity moto = motoRepository.findByPlaca("WSW04D");
		assertTrue(null != moto);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("WSW04D", true);
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()+1);
		motoRepository.delete(moto);
		facturaReposiory.delete(factura);
		parqueaderoRepository.save(parqueadero);
	}
	
	@Test
	public void testPasarDeDateAHorasConMinuosAdicionales() {
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2018, 9, 6, 3, 5);
		Long answer = (long) 17082489;
		
		Long resultado = meterVehiculo.date2hours(fecha);
		
		assertEquals(answer, resultado);
	}
	
	@Test
	public void testPasarDeDateAHorasSinMinuosAdicionales() {
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2018, 9, 6, 3, 0);
		Long answer = (long) 17082488;
		
		Long resultado = meterVehiculo.date2hours(fecha);
		
		assertEquals(answer, resultado);
	}
	
	@Test
	public void testTiempoDeEstadiaEnElParqueaderoConDiferenciaDeMinutos() {
		
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		
		int resultado = meterVehiculo.horasDeEstadia(fechaEntrada, fechaSalida);
		
		assertEquals(1, resultado);
	}
	
	@Test
	public void testTiempoDeEstadiaEnElParqueaderoConDiferenciaDeDiasYMinutos() {
		
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 7, 3, 5);
		
		int resultado = meterVehiculo.horasDeEstadia(fechaEntrada, fechaSalida);
		
		assertEquals(25, resultado);
	}
	
	@Test
	public void testTiempoDeEstadiaEnElParqueaderoConDiferenciaDeMesesYDias() {
		
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 10, 7, 3, 0);
		
		int resultado = meterVehiculo.horasDeEstadia(fechaEntrada, fechaSalida);
		
		assertEquals((32*24), resultado);
	}
	
	@Test
	public void testCostoParaMotoConCilindrajeMayorA500() {
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 2, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		VehiculoModel vehiculoModel = new MotoModel("WSW04D", 500);
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = meterVehiculo.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(3000, resultado);
	}
	
	@Test
	public void testCostoParaMotoConUnDiaExactoYCilindrajeMenorA500() {
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		VehiculoModel vehiculoModel = new MotoModel("WSW04D", 200);
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = meterVehiculo.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(4000, resultado);
	}
	
	@Test
	public void testCostoParaMotoConUnDiaExactoYCilindrajeMayorA500() {
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		VehiculoModel vehiculoModel = new MotoModel("WSW04D", 600);
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = meterVehiculo.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(6000, resultado);
	}
	
	@Test
	public void testCostoParaCarroConUnDiaExacto() {
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		VehiculoModel vehiculoModel = new CarroModel("WSW04D");
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = meterVehiculo.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(8000, resultado);
	}
	
	@Test
	public void testCostoParaMotoConNueveHoras() {
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 6, 12, 0);
		VehiculoModel vehiculoModel = new MotoModel("WSW04D", 200);
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = meterVehiculo.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(4000, resultado);
	}
	
	@Test
	public void testCostoParaCarro() {
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 2, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		VehiculoModel vehiculoModel = new CarroModel("WSW04D");
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = meterVehiculo.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(2000, resultado);
	}
	
	@Test
	public void testActualizarfactura() {
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 2, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		FacturaEntity factura =  new FacturaEntity();
		factura.setEstado(true);
		factura.setHoraIngreso(fechaEntrada);
		
		meterVehiculo.actualizarFactura(factura, fechaSalida, 5000);
		
		boolean resultado = factura.isEstado();
		
		assertFalse(resultado);
		
		facturaReposiory.delete(factura);
	}
	
	@Test
	public void testListAllVehiculos() {
		
		VehiculoModel carroModel1 = new CarroModel("IAP588");
		VehiculoModel carroModel2 = new CarroModel("IAP587");
		VehiculoModel carroModel3 = new CarroModel("IAP586");
		meterVehiculo.addVehiculo(carroModel1, 1);
		meterVehiculo.addVehiculo(carroModel2, 1);
		meterVehiculo.addVehiculo(carroModel3, 1);
		
		List<VehiculosAdentro> vehiculos = meterVehiculo.listAllVehiculos();
		int resultado =  vehiculos.size();
		
		assertEquals(3, resultado);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("IAP588", true);
		CarroEntity carro = carroRepository.findByPlaca("IAP588");
		FacturaEntity factura2 = facturaReposiory.findByPlacaAndEstado("IAP587", true);
		CarroEntity carro2 = carroRepository.findByPlaca("IAP587");
		FacturaEntity factura3 = facturaReposiory.findByPlacaAndEstado("IAP586", true);
		CarroEntity carro3 = carroRepository.findByPlaca("IAP586");
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		carroRepository.delete(carro);
		carroRepository.delete(carro2);
		carroRepository.delete(carro3);
		facturaReposiory.delete(factura);
		facturaReposiory.delete(factura2);
		facturaReposiory.delete(factura3);
		
		parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()+3);
		
		parqueaderoRepository.save(parqueadero);
	}
	
//ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity(1, 20, 10, 8000, 4000, 1000, 500);
//ParqueaderoRepository parqueadero = Mockito.mock(ParqueaderoRepository.class);
//Mockito.when(parqueadero.findByIdParqueadero(1)).thenReturn(parqueaderoEntity);
}
