package domain;

/**
 * Clase Partida
 * @author izaro, Genesis, Ainhoa
 *
 */

public class Partida {
	private String jugador;
	private int tiempo;
	private int clicks;
	
	/**
	 * Constructor Partida
	 * @param jugador Nombre de usuario del jugador
	 * @param tiempo Tiempo total para completar la partida
	 * @param clicks Clicks totales al completar la partida
	 */
	public Partida(String jugador, int tiempo, int clicks) {
		this.jugador = jugador;
		this.tiempo = tiempo;
		this.clicks = clicks;
	}
	
	/**
	 * Metodo para obtener el nombre de usuario del jugador
	 * @return Nombre de usuario del jugador
	 */
	public String getJugador() {
		return jugador;
	}
	
	/**
	 * Metodo para actualizar el nombre del jugador
	 * @param jugador Jugador de la Partida
	 */
	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
	
	/**
	 * Metodo para obtener el tiempo que ha necesitado el jugador para completar la partida
	 * @return Tiempo total de la partida
	 */
	public int getTiempo() {
		return tiempo;
	}

	/**
	 * Metodo para actualizar el tiempo de total de la partida
	 * @param tiempo Tiempo total de la partida
	 */
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	/**
	 * Metodo para obtener clicks totales de la partida
	 * @return Clicks totales de la partida
	 */
	public int getClicks() {
		return clicks;
	}

	/**
	 * Metodo para actualizar el clicks de totales de la partida
	 * @param clicks Clicks totales de la partida
	 */
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	/**
	 * Metodo String de clase
	 */
	@Override
	public String toString() {
		return "Partida [jugador=" + jugador + ", tiempo=" + tiempo + ", clicks=" + clicks + "]";
	}
	
}