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
public class pantallaInicio extends JFrame {
	
	public pantallaInicio(){
		Color cRosa = new Color(255,102,196);
		Color cRosaClaro = new Color (255,128,234);
		
		this.setSize(new Dimension(400,400));
		this.setTitle("Naufragio en el Pacífico");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel norte = new JPanel();
		JPanel sur = new JPanel();
		sur.setLayout(new GridLayout(2,1)); //(filas,columnas)
		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(3,1));
		JPanel este = new JPanel();
		JPanel oeste = new JPanel();
		
		//NORTE
		JLabel titulo = new JLabel("NAUFRAGIO EN EL PACÍFICO");
		titulo.setForeground(cRosa);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		norte.add(titulo);
		
		//SUR
		JButton bEnter = new JButton("ENTRAR");
		JPanel s1= new JPanel();
		JPanel s2 = new JPanel();
		bEnter.setBackground(cRosa);
		bEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new pantallaModoJuego();
				dispose();
				
			}
		});
		s1.add(bEnter);
		sur.add(s1);
		JButton bRegistro = new JButton("REGISTRAR");
		bRegistro.setBackground(cRosa);
		s2.add(bRegistro);
		sur.add(s2);
		
		//CENTRO
		JLabel inicio = new JLabel("Iniciar sesión");
		JPanel p1= new JPanel();
		JPanel p2= new JPanel();
		inicio.setHorizontalAlignment(SwingConstants.CENTER);
		inicio.setFont(new Font("Arial", Font.BOLD, 18));
		centro.add(inicio);
		JTextField nick = new JTextField("Introduce tu nick");  //Hacer un listener
		nick.setSize(new Dimension(50,50));
		nick.setBackground(cRosaClaro);
		nick.setColumns(30);
		p1.add(nick);
		centro.add(p1);
		JPasswordField pass = new JPasswordField();
		pass.setBackground(cRosaClaro);
		pass.setColumns(30);
		p2.add(pass);
		centro.add(p2);
		
		
	
	
		
		this.add(norte,BorderLayout.NORTH);
		this.add(sur,BorderLayout.SOUTH);
		this.add(centro, BorderLayout.CENTER);
		this.add(este,BorderLayout.EAST);
		this.add(oeste,BorderLayout.WEST);
		this.setVisible(true);
		
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pantallaInicio();

	}

}
