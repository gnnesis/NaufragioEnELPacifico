package entidades;

public class Partida {
	private String jugador;
	private int tiempo;
	private int clicks;
	
	
	public Partida(String jugador, int tiempo, int clicks) {
		this.jugador = jugador;
		this.tiempo = tiempo;
		this.clicks = clicks;
	}


	public String getJugador() {
		return jugador;
	}


	public void setJugador(String jugador) {
		this.jugador = jugador;
	}


	public int getTiempo() {
		return tiempo;
	}


	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}


	public int getClicks() {
		return clicks;
	}


	public void setClicks(int clicks) {
		this.clicks = clicks;
	}


	@Override
	public String toString() {
		return "Partida [jugador=" + jugador + ", tiempo=" + tiempo + ", clicks=" + clicks + "]";
	}
	
}