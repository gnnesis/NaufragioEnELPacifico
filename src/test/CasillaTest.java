package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entidades.Casilla;

class CasillaTest {
	
	private static Casilla c;
	
	@BeforeAll
	static void init()
	{
		c = new Casilla();
		
	}

	@Test
	void testCasillaBasica() {
		assert(!c.isHayBarco());
		assert(!c.isDestapado());
	}

	@Test
	void testComprobarBarco() {
		c.setHayBarco(true);
		assert(c.isHayBarco());

		c.setHayBarco(false);
		assert(!c.isHayBarco());
	}

	@Test
	void testComrpobarDestapado() {
		c.setDestapado(true);
		assert(c.isDestapado());

		c.setDestapado(false);
		assert(!c.isDestapado());
	}


}
