package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.demo.controladores.ControladorFacturacion;

public class ControladorFacturacionTest {

	@Test
	public void testGenerarPago() {
		ControladorFacturacion controladorPagos = new ControladorFacturacion("AAA123");
        int resultado = controladorPagos.generarPago("Carro", 3, 1, 0);
        int resultado1 = controladorPagos.generarPago("Moto", 0,1, 650);
        assertEquals(11000, resultado);
        assertEquals(6000, resultado1);
	}

	@Test
	public void testNumeroDias() {
		ControladorFacturacion pagos = new ControladorFacturacion("AAA123");
        int resultado = pagos.numeroDias(51);
        assertEquals(2, resultado);
        resultado = pagos.numeroDias(10);
        assertEquals(1, resultado);
	}

	@Test
	public void testNumeroHoras() {
		ControladorFacturacion pagos = new ControladorFacturacion("AAA123");
        int resultado = pagos.numeroHoras(35);
        assertEquals(0, resultado);
	}

}
