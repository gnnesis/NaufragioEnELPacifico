package domain;

@SuppressWarnings("serial")
public class UsuarioNoEncontradoException extends Exception {
	
	/**
	 * Constructor de la excepcion
	 */
	public UsuarioNoEncontradoException() {
		super("El usuario introducido no ha sido encontrado");
	}
}
