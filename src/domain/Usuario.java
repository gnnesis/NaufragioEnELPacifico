package domain;

/**
 * Clase Usuario
 * @author izaro, Genesis, Ainhoa
 *
 */

public class Usuario extends Persona{

	/**
	 * Constructor de Usuario
	 * @param nick
	 * @param pass
	 */
	public Usuario(String nick, String pass) {
		super(nick, pass);
	}
	
	/**
	 * Metodo String de clase
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}