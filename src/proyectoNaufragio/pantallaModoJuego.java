package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class pantallaModoJuego extends JFrame {
	
	public pantallaModoJuego(){
		Color cRosa = new Color(255,102,196);
		Color cRosaClaro = new Color (255,128,234);
		Font subtitulo = new Font("Arial", Font.BOLD, 18);	
		
		this.setSize(new Dimension(400,400));
		this.setTitle("Naufragio en el Pacífico");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel norte = new JPanel();
		JPanel sur = new JPanel();
		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(3,2));
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
		bJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new pantallaJuego();
				dispose();
				
			}
		});
		sur.add(bJugar);
		
		//CENTRO
		JLabel lTablero = new JLabel("TABLERO");
		//lTablero.setHorizontalAlignment(SwingConstants.CENTER);
		lTablero.setFont(subtitulo);
		centro.add(lTablero);
		JLabel lTematica = new JLabel("TEMATICA");
		lTematica.setFont(subtitulo);
		centro.add(lTematica);
		
		
		JTextArea  lDificultad = new JTextArea  ("DIFICULTAD: \n *Fácil 9x9 \n *Medio 12x12 \n *Difícil");
		centro.add(lDificultad);
		JTextArea  lElegirTema = new JTextArea  ("*Clásico \n *Tema1 \n *Tema2");
		centro.add(lElegirTema);
		JTextArea lDiseño = new JTextArea ("DISEÑO: \n *Rosa \n *Azul \n *Naranja");
		centro.add(lDiseño);
		

		
		
			
		
		
		
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