import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.BBDD;
import domain.Partida;

class BBDDTest {

	private BBDD bd = new BBDD("resources/db/Database_test.db");
	private final Partida p = new Partida("test", 100, 200);
	
	@BeforeEach
	void setUp() throws Exception {
		bd.limpiarBaseDeDatos();
		bd.insertarPartida(p);
	}

	@Test
	void testRanking() {
		Partida mejor = new Partida("test",1,1);
		bd.insertarPartida(mejor);
		List<Partida> partidas = bd.obtenerRanking();
		assert(partidas.get(0).getClicks()==1);
	}
	
	@Test
	void testObtenerTodas()
	{
		Partida p = new Partida("test",1,1);
		bd.insertarPartida(p);
		List<Partida> partidas = bd.obtenerTodasLasPartidas();
		assert(partidas.size() == 2);
	}
	
	@Test
	void testObtenerTodasUsuarioEspecifico()
	{
		Partida p = new Partida("test",1,1);
		bd.insertarPartida(p);
		List<Partida> partidas = bd.obtenerTodasLasPartidas("test");
		
		assert(partidas.size() == 2);
	}
	
	@Test
	void testObtenerTodasUsuarios()
	{
		Partida p = new Partida("test",1,1);
		bd.insertarPartida(p);
		Map<String, List<Partida>> partidas = bd.obtenerPartidasPorUsuario();
		
		assert(partidas.get("test").size() == 2);
	}
	
	@Test
   	public void testCheckEstado() throws InterruptedException, ExecutionException {
        BBDD bd = new BBDD();

        CompletableFuture<Boolean> future = bd.checkEstado();

        assert(!future.isDone());

        Boolean resultado = future.get();
        
        assert(future.isDone());
        assert(resultado);
    }
}