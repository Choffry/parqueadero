package com.example.demo.servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.Taller2Application;
import com.parqueadero.entities.FacturaEntity;
import com.parqueadero.entities.ParqueaderoEntity;
import com.parqueadero.models.VehiculoModel;
import com.parqueadero.models.VehiculosAdentro;
import com.parqueadero.repositories.FacturaReposiory;
import com.parqueadero.repositories.ParqueaderoRepository;
import com.parqueadero.services.VigilanteService;
import com.parqueadero.services.impl.VigilanteServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=Taller2Application.class)
public class MeterVehiculoTest {
	
	@Autowired
	VigilanteServiceImpl vigilante;
	
	@Autowired
	FacturaReposiory facturaReposiory;
	
	@Autowired
	ParqueaderoRepository parqueaderoRepository;
	
	private static final Log LOG = LogFactory.getLog(VigilanteServiceImpl.class);
	private static final String CARRO = "Carro";
	private static final String MOTO = "Moto";
	private static final int ID_PARQUEADERO = 1;

	@Test
	public void testVerificarPlacaQueEmpiezaPorAyEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("AAA111");
		
		boolean placaIniciaPorA = vigilante.verificarPlaca(vehiculoModel, Calendar.MONDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueEmpiezaPorAyNoEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("AAA111");
		
		boolean placaIniciaPorA = vigilante.verificarPlaca(vehiculoModel, Calendar.WEDNESDAY);
		
		assertFalse(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueNoEmpiezaPorAYEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("BAA111");
		
		boolean placaIniciaPorA = vigilante.verificarPlaca(vehiculoModel, Calendar.MONDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarPlacaQueNoEmpiezaPorAYNoEsunDiaHabil() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("BAA111");
		
		boolean placaIniciaPorA = vigilante.verificarPlaca(vehiculoModel, Calendar.WEDNESDAY);
		
		assertTrue(placaIniciaPorA);
	}
	
	@Test
	public void testVerificarCeldasDisponiblesParaEnCarro() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("PPP456");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		int respuesta = vigilante.numCeldasParqueadero(ID_PARQUEADERO, vehiculoModel.getTipoVehiculo());
		
		assertEquals(20,  respuesta);
	}
	
	@Test
	public void testVerificarCeldasDisponiblesEnMoto() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("PPP456");
		vehiculoModel.setCilindraje(300);
		vehiculoModel.setTipoVehiculo(MOTO);
		
		int respuesta =  vigilante.numCeldasParqueadero(ID_PARQUEADERO, vehiculoModel.getTipoVehiculo());
		
		assertEquals(10, respuesta);
	}
	
	@Test
	public void testVerificarIngresoDeFacturaABaseDeDatos() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("IAP588");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		vigilante.comenzarFactura(vehiculoModel);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("IAP588", true);	
		assertTrue(null != factura);
		
		facturaReposiory.delete(factura);
	}
	
	@Test
	public void testVerificarIngresoDeCarroAlParqueadero() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("IAP588");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("IAP588", true);
		assertEquals(CARRO, factura.getTipoVehiculo());
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(ID_PARQUEADERO);
		parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()+1);
		parqueaderoRepository.save(parqueadero);
		facturaReposiory.delete(factura);
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
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(ID_PARQUEADERO);
		parqueadero.setNumCeldasMoto(parqueadero.getNumCeldasMoto()+1);
		parqueaderoRepository.save(parqueadero);
		facturaReposiory.delete(factura);
	}
	
	@Test
	public void testPasarDeDateAHorasConMinuosAdicionales() {
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2018, 9, 6, 3, 5);
		Long answer = (long) 17082489;
		
		Long resultado = vigilante.date2hours(fecha);
		
		assertEquals(answer, resultado);
	}
	
	@Test
	public void testPasarDeDateAHorasSinMinuosAdicionales() {
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2018, 9, 6, 3, 0);
		Long answer = (long) 17082488;
		
		Long resultado = vigilante.date2hours(fecha);
		
		assertEquals(answer, resultado);
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
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("WSW04D");
		vehiculoModel.setCilindraje(500);
		vehiculoModel.setTipoVehiculo(MOTO);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(3000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaMotoConUnDiaExactoYCilindrajeMenorA500() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("WSW04D");
		vehiculoModel.setCilindraje(200);
		vehiculoModel.setTipoVehiculo(MOTO);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(4000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaMotoConUnDiaExactoYCilindrajeMayorA500() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("WSW04D");
		vehiculoModel.setCilindraje(600);
		vehiculoModel.setTipoVehiculo(MOTO);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(6000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaCarroConUnDiaExacto() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 7, 3, 0);
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("WSW04D");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(8000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaMotoConNueveHoras() {
		Date fechaEntrada = new Date(2018, 9, 6, 3, 0);
		Date fechaSalida = new Date(2018, 9, 6, 12, 0);
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("WSW04D");
		vehiculoModel.setCilindraje(200);
		vehiculoModel.setTipoVehiculo(MOTO);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
		assertEquals(4000, resultado);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCostoParaCarro() {
		Date fechaEntrada = new Date(2018, 9, 6, 2, 0);
		Date fechaSalida = new Date(2018, 9, 6, 3, 5);
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("WSW04D");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(1);
		
		int resultado = vigilante.precioTotal(fechaEntrada, fechaSalida, vehiculoModel, parqueadero);
		
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
		
		boolean resultado = factura.isEstado();
		
		assertFalse(resultado);
		
		facturaReposiory.delete(factura);
	}
	
	@Test
	public void testListAllVehiculos() {
		
		VehiculoModel vehiculoModel = new VehiculoModel();
		vehiculoModel.setPlaca("IAP588");
		vehiculoModel.setTipoVehiculo(CARRO);
		
		VehiculoModel vehiculoModel2 = new VehiculoModel();
		vehiculoModel2.setPlaca("IAP587");
		vehiculoModel2.setTipoVehiculo(CARRO);
		
		vigilante.agregarVehiculo(vehiculoModel, ID_PARQUEADERO);
		vigilante.agregarVehiculo(vehiculoModel2, ID_PARQUEADERO);
		
		List<VehiculosAdentro> vehiculos = vigilante.listarTodosLosVehiculos();
		int resultado =  vehiculos.size();
		
		assertEquals(2, resultado);
		
		FacturaEntity factura = facturaReposiory.findByPlacaAndEstado("IAP588", true);
		facturaReposiory.delete(factura);
		
		factura = facturaReposiory.findByPlacaAndEstado("IAP587", true);
		facturaReposiory.delete(factura);
		
		ParqueaderoEntity parqueadero = parqueaderoRepository.findByIdParqueadero(ID_PARQUEADERO);
		
		parqueadero.setNumCeldasCarro(parqueadero.getNumCeldasCarro()+2);
		
		parqueaderoRepository.save(parqueadero);
	}
	
//ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity(1, 20, 10, 8000, 4000, 1000, 500);
//ParqueaderoRepository parqueadero = Mockito.mock(ParqueaderoRepository.class);
//Mockito.when(parqueadero.findByIdParqueadero(1)).thenReturn(parqueaderoEntity);
}
