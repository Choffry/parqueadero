package controladoresTest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.parqueadero.controllers.ControladorEntradas;


public class ControladorEntradasTest {
	

	@Test
	public void testVerificarPlaca() {
		ControladorEntradas controlador = new ControladorEntradas("Carro", "AAA123");
        String resultado = controlador.verificarPlaca("Lunes");
        String resultado1 = controlador.verificarPlaca("Domingo");
        String resultado2 = controlador.verificarPlaca("Miercoles");
        assertEquals("Bienvenido", resultado);
        assertEquals("Bienvenido", resultado1);
        assertEquals("Acceso denegado", resultado2);

        ControladorEntradas controlador1 = new ControladorEntradas("Carro", "BAA123");
        resultado = controlador1.verificarPlaca("Lunes");
        resultado1 = controlador1.verificarPlaca("Domingo");
        resultado2 = controlador1.verificarPlaca("Miercoles");
        assertEquals("Bienvenido", resultado);
        assertEquals("Bienvenido", resultado1);
        assertEquals("Bienvenido", resultado2);
	}

	@Test
	public void testVerificarDisponibilidad() {
		ControladorEntradas controlador = new ControladorEntradas("Carro", "AAA123");
		ControladorEntradas controlador1 = new ControladorEntradas("Moto", "AAA123");
		for(int i=0; i<20; i++) {
			controlador.agregarvehiculo("Carro");
		}
		controlador1.agregarvehiculo("Moto");
        //String resultado = controlador.verificarDisponibilidad();
        String resultado1 = controlador1.verificarDisponibilidad();
        //assertEquals("No hay cupos para carros", resultado);
        assertEquals("Puede ingresar el vehiculo", resultado1);
	}
	
	@Test
	public void testAgregarCarro() {
		ControladorEntradas controlador = new ControladorEntradas("Carro", "AAA123");
		controlador.agregarvehiculo("Carro");
		controlador.agregarvehiculo("Carro");
		controlador.agregarvehiculo("Carro");
		int resultado = controlador.cantidadCupos("Carro");
		assertEquals(17, resultado);
	}
	
	@Test
	public void testSacarVehiculo() {
		ControladorEntradas controlador = new ControladorEntradas("Carro", "AAA123");
		controlador.agregarvehiculo("Carro");
		controlador.agregarvehiculo("Carro");
		controlador.agregarvehiculo("Carro");
		controlador.sacarVehiculo("Carro");
		int resultado = controlador.cantidadCupos("Carro");
		assertEquals(18, resultado);
	}

}
