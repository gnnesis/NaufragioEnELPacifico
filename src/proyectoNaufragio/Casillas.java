package proyectoNaufragio;

import javax.swing.JButton;

public class Casillas extends JButton{

	private boolean hayBarco;
	
	public Casillas()
	{
		hayBarco = System.currentTimeMillis() % 2 == 0;
	}

	public boolean isHayBarco() {
		return hayBarco;
	}

	public void setHayBarco(boolean hayBarco) {
		this.hayBarco = hayBarco;
	}
	
	
}