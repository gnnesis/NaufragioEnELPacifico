package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entidades.Barco;

class BarcoTest {
	
	public static Barco barco;
	private static final int NUM_BARCOS = 5;

	@BeforeAll
	static void init() {
		barco = new Barco(NUM_BARCOS);
		assert(barco != null);
	}
	
	@Test
	void comprobarNumBarcos()
	{
		int n = barco.getNumCeldas();
		assertTrue(n == NUM_BARCOS);
	}
	
	@Test
	void comprobarSettersyGetters()
	{
		int nuevo = 10;
		barco.setNumCeldas(nuevo);
		assertTrue(nuevo == barco.getNumCeldas());
	}
	
}