package com.example.demo.servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.parqueadero.ParqueaderoApplication;
import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.exception.ExceptionValidaciones;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.services.impl.VigilanteServiceImpl;
import com.parqueadero.validators.entrada.PlacaIniciaPorA;
import com.parqueadero.validators.entrada.ValidacionesEntrada;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ParqueaderoApplication.class)
@Transactional
public class MeterVehiculoTest {
	
	@Autowired
	@Qualifier("vigilanteService")
	private VigilanteServiceImpl vigilante;
		
	@Autowired
	@Qualifier("facturaReposiory")
	private FacturaReposiory facturaReposiory;
	
	@Autowired
	@Qualifier("parqueaderoRepository")
	private ParqueaderoRepository parqueaderoRepository;

	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";
	private static final int ID_PARQUEADERO = 1;

	@After
	public void clean() {
		
		facturaReposiory.deleteAll();		
		
	    ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
	    parqueadero.setNumCeldasCarro(20);
	    parqueadero.setNumCeldasMoto(10);
	    parqueaderoRepository.save(parqueadero);
	}
	
	
	@Test
	public void testVerificarPlacaQueEmpiezaPorAyEsunDiaHabil() {
		
		ValidacionesEntrada validacionInicaA = new PlacaIniciaPorA();
		PlacaIniciaPorA validacion = (PlacaIniciaPorA) validacionInicaA;
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("AAA111");
		
		boolean placaIniciaPorA = validacion.verificarPlaca(vehiculoModel, Calendar.MONDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueEmpiezaPorAyNoEsunDiaHabil() {
		
		ValidacionesEntrada validacionInicaA = new PlacaIniciaPorA();
		PlacaIniciaPorA validacion = (PlacaIniciaPorA) validacionInicaA;
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("AAA111");
		
		boolean placaIniciaPorA = validacion.verificarPlaca(vehiculoModel, Calendar.WEDNESDAY);
		
		assertFalse(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueNoEmpiezaPorAYEsunDiaHabil() {
		
		ValidacionesEntrada validacionInicaA = new PlacaIniciaPorA();
		PlacaIniciaPorA validacion = (PlacaIniciaPorA) validacionInicaA;
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("BAA111");
		
		boolean placaIniciaPorA = validacion.verificarPlaca(vehiculoModel, Calendar.MONDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueNoEmpiezaPorAYNoEsunDiaHabil() {
		
		ValidacionesEntrada validacionInicaA = new PlacaIniciaPorA();
		PlacaIniciaPorA validacion = (PlacaIniciaPorA) validacionInicaA;
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("BAA111");
		
		boolean placaIniciaPorA = validacion.verificarPlaca(vehiculoModel, Calendar.WEDNESDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testCarroYaIngresado() {
		
		VehiculoModel vehiculoModel = new VehiculoModel("PAA123", CARRO, true, 0);
		
		vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
		
		try {
			vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
			fail();
		} catch (ExceptionValidaciones e) {
			assertEquals("El vehiculo ya se encuentra adentro", e.getMessage());
		}
								
	}
	
	
	@Test(expected = ExceptionValidaciones.class)
	public void testVerificarCeldasDisponiblesParaEnCarro() {
		
		for(int i = 0; i<11; i++) {
			
			VehiculoModel vehiculoModel = new VehiculoModel("AAA12"+i, CARRO, true, 0);
			VehiculoModel vehiculoModel2 = new VehiculoModel("AAA13"+i, CARRO, true, 0);
			
			vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
			vigilante.agregarVehiculo(vehiculoModel2, ID_PARQUEADERO);
		}
		
		facturaReposiory.deleteAllInBatch();	
		
	}
	
	@Test(expected = ExceptionValidaciones.class)
	public void testVerificarCeldasDisponiblesEnMoto() {
		
		for(int i = 0; i<10; i++) {
			
			VehiculoModel vehiculoModel = new VehiculoModel("AAA12"+i, MOTO, true, 300);
			VehiculoModel vehiculoModel2 = new VehiculoModel("AAA13"+i, MOTO, true, 300);
			
			vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
			vigilante.agregarVehiculo(vehiculoModel2, ID_PARQUEADERO);
		}
	}
	
	@Test
	public void testVerificarIngresoDeFacturaABaseDeDatos() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("IAP588");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		vigilante.comenzarFactura(vehiculoModel);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("IAP588", true);
		assertEquals(vehiculoModel.getPlaca(), factura.getPlaca());
		assertEquals(vehiculoModel.getTipoVehiculo(),factura.getTipoVehiculo());
		//assertTrue(null != factura);
		
	}
	
	@Test
	public void testVerificarIngresoDeCarroAlParqueadero() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("MMM588");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("MMM588", true);
		assertEquals(CARRO, factura.getTipoVehiculo());
		
	}
	
	@Test
	public void testVerificarIngresoDeMotoAlParqueadero() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("PPP456");
		vehiculoModel.setCilindraje(300);
		vehiculoModel.setTipoVehiculo(MOTO);
		
		vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("PPP456", true);
		assertEquals(MOTO, factura.getTipoVehiculo());
		
	}
	
	
	@Test
	public void testTiempoDeEstadiaEnElParqueaderoConDiferenciaDeMinutos() {
		
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		
		int resultado = vigilante.horasDeEstadia(fechaEntrada, fechaSalida);
		
		assertEquals(1, resultado);
	}
	
	@Test
	public void testTiempoDeEstadiaEnElParqueaderoConDiferenciaDeDiasYMinutos() {
		
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 9, 7, 3, 5);
		
		int resultado = vigilante.horasDeEstadia(fechaEntrada, fechaSalida);
		
		assertEquals(25, resultado);
	}
	
	@Test
	public void testTiempoDeEstadiaEnElParqueaderoConDiferenciaDeMesesYDias() {
		
		@SuppressWarnings("deprecation")
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		@SuppressWarnings("deprecation")
		Date fechaSalida = new Date(2018, 10, 7, 3, 0);
		
		int resultado = vigilante.horasDeEstadia(fechaEntrada, fechaSalida);
		
		assertEquals((32*24), resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaMotoConCilindrajeMayorA500() {
		Date fechaEntrada = new Date(2018, 9, 6, 2, 0);
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setPlaca("WSW04D");
		facturaEntity.setCilindraje(500);
		facturaEntity.setTipoVehiculo(MOTO);
		facturaEntity.setHoraIngreso(fechaEntrada);
		facturaEntity.setHoraSalida(fechaSalida);
		facturaEntity.setEstado(true);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(facturaEntity, parqueadero);
		
		assertEquals(3000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaMotoConUnDiaExactoYCilindrajeMenorA500() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setPlaca("WSW04D");
		facturaEntity.setCilindraje(200);
		facturaEntity.setTipoVehiculo(MOTO);
		facturaEntity.setHoraIngreso(fechaEntrada);
		facturaEntity.setHoraSalida(fechaSalida);
		facturaEntity.setEstado(true);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(facturaEntity, parqueadero);
		
		assertEquals(4000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaMotoConUnDiaExactoYCilindrajeMayorA500() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setPlaca("WSW04D");
		facturaEntity.setCilindraje(600);
		facturaEntity.setTipoVehiculo(MOTO);
		facturaEntity.setHoraIngreso(fechaEntrada);
		facturaEntity.setHoraSalida(fechaSalida);
		facturaEntity.setEstado(true);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(facturaEntity, parqueadero);
		
		assertEquals(6000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaCarroConUnDiaExacto() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setPlaca("WSW04D");
		facturaEntity.setTipoVehiculo(CARRO);
		facturaEntity.setHoraIngreso(fechaEntrada);
		facturaEntity.setHoraSalida(fechaSalida);
		facturaEntity.setEstado(true);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(facturaEntity, parqueadero);
		
		assertEquals(8000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaMotoConNueveHoras() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 6, 12, 0);
		
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setPlaca("WSW04D");
		facturaEntity.setCilindraje(200);
		facturaEntity.setTipoVehiculo(MOTO);
		facturaEntity.setHoraIngreso(fechaEntrada);
		facturaEntity.setHoraSalida(fechaSalida);
		facturaEntity.setEstado(true);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(facturaEntity, parqueadero);
		
		assertEquals(4000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaCarroQueEstuvoUnMinuto() {
		Date fechaEntrada = new Date(2018, 9, 6, 2, 0);
		Date fechaSalida = new Date(2018, 9, 6, 3, 2);
		
		FacturaEntity facturaEntity = new FacturaEntity();
		facturaEntity.setPlaca("WSW04D");
		facturaEntity.setTipoVehiculo(CARRO);
		facturaEntity.setHoraIngreso(fechaEntrada);
		facturaEntity.setHoraSalida(fechaSalida);
		facturaEntity.setEstado(true);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(facturaEntity, parqueadero);
		
		assertEquals(2000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testActualizarfactura() {
		Date fechaEntrada = new Date(2018, 9, 6, 2, 0);
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		
		FacturaEntity factura =  new FacturaEntity();
		factura.setEstado(true);
		factura.setHoraIngreso(fechaEntrada);
		
		vigilante.actualizarFactura(factura, fechaSalida, 5000);
		
		Date resultado = factura.getHoraSalida();
		
		assertNotNull(resultado);
		
	}
	
	@Test
	public void testListAllVehiculos() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("FHN064");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		VehiculoModel vehiculoModel2 = new VehiculoModel();
		vehiculoModel2.setPlaca("FHN063");
		vehiculoModel2.setTipoVehiculo(CARRO);
		
		vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
		vigilante.agregarVehiculo(vehiculoModel2, ID_PARQUEADERO);
		
		List<VehiculosAdentro> vehiculos = vigilante.listarTodosLosVehiculos();
		int resultado =  vehiculos.size();
		
		assertEquals(3, resultado);			
		
	}
	
//ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity(1, 20, 10, 8000, 4000, 1000, 500);
//ParqueaderoRepository parqueadero = Mockito.mock(ParqueaderoRepository.class);
//Mockito.when(parqueadero.findByIdParqueadero(1)).thenReturn(parqueaderoEntity);
}
