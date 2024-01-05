package domain;

public class Nivel {
	
	private String nombre;
	private Barco[] barcos;
	private int filas, columnas;
	private int numBarco = 0;
	
	public Nivel(String nombre, int filas, int columnas, int numBarcos) {
		this.nombre = nombre;
		this.filas = filas;
		this.columnas = columnas;
		this.barcos = new Barco[numBarcos];
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
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
	
	public Barco[] getBarcos()
	{
		return barcos;
	}

}