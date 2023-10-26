package proyectoNaufragio;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Visual extends JFrame {
	
	private int clicks = 0;
	private Casillas[][] tablero;
	
	public Visual()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Ejemplo casillas");
		this.setLayout(new GridLayout(3,3));
		this.setSize(500,500);
		tablero = new Casillas[3][3];
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				Casillas b = new Casillas();
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						clicks++;
						System.out.println("Clicks: " + clicks);
						Casillas actual = ((Casillas) e.getSource());
						if(actual.isHayBarco())
						{
							System.out.println("Acabo de tocar un barco");
							actual.setBackground(Color.red);
						}
						else
						{
							System.out.println("Agua");
							actual.setBackground(Color.blue);
						}
						actual.setEnabled(false);
						
						
					}});
				this.add(b);
				tablero[i][j] = b;
			}
		}
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Visual();
	}

}