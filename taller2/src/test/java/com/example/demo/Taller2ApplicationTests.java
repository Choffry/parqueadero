package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.parqueadero.ParqueaderoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=ParqueaderoApplication.class)
public class Taller2ApplicationTests {

	@Test
	public void sumaTest() {
		assertEquals(0, 0);
	}

}
