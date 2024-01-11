import org.junit.jupiter.api.Test;
import domain.UsuarioIncorrectoException;
import domain.UsuarioNoEncontradoException;

class ExceptionesTest {

	@Test
	void testUsuarioIncorrecto() {
		UsuarioIncorrectoException uie = new UsuarioIncorrectoException();			
		assert(uie.getMessage().equals("El usuario y/o contraseña introducidos no son correctos"));
		}
		
	@Test
	void testUsuarioNoEncontrado() {
		UsuarioNoEncontradoException uie = new UsuarioNoEncontradoException();		
		assert(uie.getMessage().equals("El usuario introducido no ha sido encontrado"));
		}

	
}
