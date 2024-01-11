import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.Partida;

class PartidaTest {
	
	private static Partida partida;
    private static final String JUGADOR = "test";
    private static final int TIEMPO = 45;
    private static final int CLICKS = 23;
	
	@BeforeAll
	static void init() {
		partida = new Partida(JUGADOR,TIEMPO,CLICKS);
		System.out.println(partida);
	}

	@Test
	void testGetJugador() {
		assert(partida.getJugador().equals(JUGADOR));		
		 
	}
	
	@Test
	void testGetTiempo() {
		assertEquals(TIEMPO, partida.getTiempo());
		
	}
	
	@Test
	void testGetClicks() {
		assertEquals(CLICKS, partida.getClicks());	
	}
	@Test
	void testSetJugador() {
		String nuevoJug = "test2";
		partida.setJugador(nuevoJug);
		assert(partida.getJugador().equals(nuevoJug));
		partida.setJugador(JUGADOR);
		
	}

	@Test
	void testSetTiempo() {
		int nuevoTiempo = 60;
        partida.setTiempo(nuevoTiempo);
        assertEquals(nuevoTiempo, partida.getTiempo());
        partida.setTiempo(TIEMPO);
	}

	@Test
	void testSetClicks() {
		 int nuevoClicks = 14;
		 partida.setClicks(nuevoClicks);
		 assertEquals(nuevoClicks,partida.getClicks());
		 partida.setClicks(CLICKS);
	}
}
