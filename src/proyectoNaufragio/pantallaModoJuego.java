package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class pantallaModoJuego extends JFrame {
	
	public pantallaModoJuego(){
		Color cRosa = new Color(255,102,196);
		Color cRosaClaro = new Color (255,128,234);
		this.setSize(new Dimension(400,400));
		this.setTitle("Naufragio en el Pacífico");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel norte = new JPanel();
		JPanel sur = new JPanel();
		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(2,2));
		JPanel este = new JPanel();
		JPanel oeste = new JPanel();
		
		//NORTE
		JLabel titulo = new JLabel("NAUFRAGIO EN EL PACÍFICO");
		titulo.setForeground(cRosa);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		norte.add(titulo);
		
		//SUR
		JButton bJugar = new JButton("JUGAR");
		bJugar.setBackground(cRosa);
		sur.add(bJugar);
		
		//CENTRO
		JLabel lTablero = new JLabel("TABLERO");
		//lTablero.setHorizontalAlignment(SwingConstants.CENTER);
		lTablero.setFont(new Font("Arial", Font.BOLD, 18));
		centro.add(lTablero);
		JLabel lTematica = new JLabel("TEMATICA");
		lTematica.setFont(new Font("Arial", Font.BOLD, 18));
		centro.add(lTematica);
		
		JPanel pCentroIzq = new JPanel ();
		centro.add(pCentroIzq);
		JLabel lDificultad = new JLabel ("DIFICULTAD: \n *Fácil 9x9 \n *Medio 12x12 \n *Difícil");
		centro.add(lDificultad);
		
		
		
		
		JPanel pCentroDer = new JPanel ();
		centro.add(pCentroDer);
		JLabel lElegirTema = new JLabel ("*Clásico \n *Tema1 \n *Tema2");
		centro.add(lElegirTema);
		
		
		
		
		
		
		
		
	
		
	
		
		this.add(norte,BorderLayout.NORTH);
		this.add(sur,BorderLayout.SOUTH);
		this.add(centro, BorderLayout.CENTER);
		this.add(este,BorderLayout.EAST);
		this.add(oeste,BorderLayout.WEST);
		this.setVisible(true);
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pantallaModoJuego();

	}

}
