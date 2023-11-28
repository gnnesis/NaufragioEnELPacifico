package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
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
		u = new Usuario(NICK, PASS);
	}

	@Test
	void testUsuario() {
		 assertNotNull(u);
		 assertEquals(NICK, u.getNickname());
		 assertEquals(PASS, u.getContrasena());
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
		assert(u.getContrasena().equals(PASS));
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
	void testComprobarContrasena() {
		 assertTrue(u.comprobarContrasena(PASS));
	     assertFalse(u.comprobarContrasena("contrasenaIncorrecta"));
	}

	@Test
	void testRegistrar() {
		
    }

    

    @Test
    void testGetMd5() {
        // Prueba con una cadena simple
        String input = "password123";
        String expectedHash = "482c811da5d5b4bc6d497ffa98491e38";
        assertEquals(expectedHash, u.getMd5(input));

        // Prueba con una cadena vacía
        input = "";
        expectedHash = "d41d8cd98f00b204e9800998ecf8427e";
        assertEquals(expectedHash, u.getMd5(input));

        // Prueba con una cadena más larga y compleja
        input = "estaEsUnaCadenaLargaYCompleja123!@#";
        expectedHash = "1e8324765e0b5506fde94626b8a9d7c1";
        assertEquals(expectedHash, u.getMd5(input));
        
	}

}
