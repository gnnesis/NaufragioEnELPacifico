package domain;
/**
 * Clase Nivel
 * @author izaro
 *
 */

public class Nivel {
	
	private String nombre;
	private Barco[] barcos;
	private int filas, columnas;
	private int numBarco = 0;
	
	/**
	 * Constructor de Nivel
	 * @param nombre Nombre del nivel
	 * @param filas numero de filas del tablero
	 * @param columnas numero de columnas del tablero
	 * @param numBarcos numero de barcos que se colocan en el tablero
	 */
	public Nivel(String nombre, int filas, int columnas, int numBarcos) {
		this.nombre = nombre;
		this.filas = filas;
		this.columnas = columnas;
		this.barcos = new Barco[numBarcos];
	}
	/**
	 * Metodo para obtener el nombre del nivel
	 * @return Nombre del nivel
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo para actualizar el nombre del nivel
	 * @param nombre Nombre del nivel
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Metodo para obterner el numero de filas que tiene el tablero
	 * @return numero de filas
	 */
	public int getFilas() {
		return filas;
	}
	
	/**
	 * Metodo para actualizar el numero de filas
	 * @param filas numero de filas del tablero
	 */
	public void setFilas(int filas) {
		this.filas = filas;
	}
	
	/**
	 * Metodo para obterner el numero de columnas que tiene el tablero
	 * @return numero de columnas
	 */
	public int getColumnas() {
		return columnas;
	}

	/**
	 * Metodo para actualizar el numero de columnas
	 * @param columnas numero de columnas del tablero
	 */
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	/**
	 * Metodo para añadir un objeto Barco en una celda
	 * @param b Barco a añadir
	 * @return devuelve si ha podido añadir o no el barco
	 */
	public boolean anadirBarco(Barco b)
	{
		boolean anadido = false;
		
		if(barcos.length > numBarco)
		{
			barcos[numBarco] = b;
			numBarco++;
			anadido = true;
		}
		
		return anadido;
	}
	
	/**
	 * Metodo para obtener el listdo de todos los barcos del nivel
	 * @return Listado de barcos
	 */
	public Barco[] getBarcos()
	{
		return barcos;
	}

}