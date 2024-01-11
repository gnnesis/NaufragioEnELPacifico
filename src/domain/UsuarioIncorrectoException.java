package domain;

@SuppressWarnings("serial")
public class UsuarioIncorrectoException extends Exception {

	/**
	 * Constructor de la excepción
	 */
	public UsuarioIncorrectoException()
	{
		super("El usuario y/o contraseña introducidos no son correctos");
	}
	
}
