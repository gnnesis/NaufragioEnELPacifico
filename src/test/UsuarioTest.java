package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entidades.Usuario;

class UsuarioTest {
	
	private static Usuario u;
	private static final String NICK = "test";
	private static final String PASS = "test";
	private static final String CODED_PASS = "098f6bcd4621d373cade4e832627b4f6";
	private static final String FILE_PATH = "ruta/del/archivo.txt";
	
	@BeforeAll
	static void init()
	{
		u = new Usuario(NICK, CODED_PASS);
	}

	@Test
	void testUsuario() {
		 assertNotNull(u);
		 assertEquals(NICK, u.getNickname());
		 assertEquals(PASS, u.getContrasena());
	}
	
	@Test
	void testComprobarContrasena() {
		assertFalse(u.comprobarContrasena(PASS));
	}

	@Test
	void testGetNickname() {
		assert(u.getNickname().equals(NICK));
	}

	@Test
	void testSetNickname() {
		u.setNickname("prueba");
		assert(u.getNickname().equals("prueba"));
		u.setNickname(NICK);
	}

	@Test
	void testGetContrasena() {
		assert(u.getContrasena().equals(CODED_PASS));
	}

	@Test
	void testSetContrasena() {
		String new_pass = "mi nueva pass";
		u.setContrasena(new_pass);
		assert(u.getContrasena().equals(new_pass));
		u.setContrasena(PASS);
		assert(u.getContrasena().equals(PASS));
	}

	@Test
	void testRegistrar() {
		try {
			u.registrar(FILE_PATH);
			Files.delete(Paths.get(FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }    

    @Test
    void testGetMd5() {
        assertEquals(CODED_PASS, u.getMd5(PASS));

    }
}