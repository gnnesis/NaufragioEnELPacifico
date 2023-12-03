package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entidades.Barco;
import entidades.Nivel;

class NivelTest {
	
	static Nivel nivel;
	final static String NOMBRE_NIVEL = "Mi nivel";

	@BeforeAll
	static void init() {
		nivel = new Nivel(NOMBRE_NIVEL,3,3,5);
	}
	
	@Test
	void obtenerNombre()
	{
		assert(nivel.getNombre().equals(NOMBRE_NIVEL));
	}
	
	@Test
	void cambiarNombre()
	{
		String nuevoNombre = "Nivel asdfg";
		nivel.setNombre(nuevoNombre);
		assert(nivel.getNombre().equals(nuevoNombre));
	}
	
	@Test
	void cambiarDimensiones()
	{
		nivel.setColumnas(5);
		nivel.setFilas(7);
		assert(nivel.getFilas() == 7);
		assert(nivel.getColumnas() == 5);
	}
	
	@Test
	void comprobarListaBarcosInicial()
	{
		Barco lista [] = nivel.getBarcos();
		assert(lista.length==5);
	}
	
	@Test
	void comprobarAnadirBarco()
	{
		Barco b = new Barco(3);
		nivel.anadirBarco(b);
		Barco listaBarcos[] = nivel.getBarcos();
		assert(listaBarcos[0] == b);
	}

}
