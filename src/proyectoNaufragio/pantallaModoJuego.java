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
		JPanel centro = new JPanel(new GridLayout(3,2));
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
		//CENTRO
				JLabel lTablero = new JLabel("DIFICULTAD");
				//lTablero.setHorizontalAlignment(SwingConstants.CENTER);
				lTablero.setFont(subtitulo);
				centro.add(lTablero);
				JLabel lTematica = new JLabel("TEMATICA");
				lTematica.setFont(subtitulo);
				centro.add(lTematica);
				JPanel pDificultad = new JPanel(new GridLayout(3,1));
				JPanel pTematica = new JPanel(new GridLayout(5,1));
				ButtonGroup bgDificultad = new ButtonGroup();
				JRadioButton rbFacil = new JRadioButton("Facil");
				rbFacil.setSelected(true);
				JRadioButton rbMedio = new JRadioButton("Medio");
				JRadioButton rbDificil = new JRadioButton("Dificil");
				bgDificultad.add(rbFacil);
				bgDificultad.add(rbMedio);
				bgDificultad.add(rbDificil);
				pDificultad.add(rbFacil);
				pDificultad.add(rbMedio);
				pDificultad.add(rbDificil);
				ButtonGroup bgTematica = new ButtonGroup();
				JRadioButton rbClasico = new JRadioButton("Clasico");
				rbClasico.setSelected(true);
				JRadioButton rbNavidad = new JRadioButton("Navidad");
				JRadioButton rbHalloween = new JRadioButton("Halloween");
				JRadioButton rbVerano = new JRadioButton("Verano");
				JRadioButton rbPrimavera = new JRadioButton("Primavera");
				bgTematica.add(rbClasico);
				bgTematica.add(rbNavidad);
				bgTematica.add(rbHalloween);
				bgTematica.add(rbVerano);
				bgTematica.add(rbPrimavera);
				pTematica.add(rbClasico);
				pTematica.add(rbNavidad);
				pTematica.add(rbHalloween);
				pTematica.add(rbVerano);
				pTematica.add(rbPrimavera);
				
				
				centro.add(pDificultad);
				centro.add(pTematica);
		

		
		
			
		
		
		
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