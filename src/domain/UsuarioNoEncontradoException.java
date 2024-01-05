package domain;

@SuppressWarnings("serial")
public class UsuarioNoEncontradoException extends Exception {
	
	public UsuarioNoEncontradoException() {
		super("El usuario introducido no ha sido encontrado");
	}
}
