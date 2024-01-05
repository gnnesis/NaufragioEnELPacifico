package domain;

@SuppressWarnings("serial")
public class UsuarioIncorrectoException extends Exception {

	public UsuarioIncorrectoException()
	{
		super("El usuario y/o contraseña introducidos no son correctos");
	}
	
}
