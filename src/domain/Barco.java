package domain;

/**
 * Clase Barco
 * @author Izaro,Genesis,Ainhoa
 */
public class Barco {
	
	private int numCeldas;
	
	/**
	 * Constructor de Barco
	 * @param n Numero de celdas del barco
	 */
	public Barco(int n)
	{
		numCeldas = n;
	}
	
	/**
	 * Metodo para obtener las celdas de un barco
	 * @return Numero de celdas que contiene un barco
	 */
	public int getNumCeldas()
	{
		return numCeldas;
	}
	
	/**
	 * Metodo para actualizar la cantidad de celdas de un barco
	 * @param n Numero de celdas del barco
	 */
	public void setNumCeldas(int n)
	{
		numCeldas = n;
	}

}
