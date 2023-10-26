package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.*;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class pantallaInicio extends JFrame {
	
	public pantallaInicio(){
		Color cRosa = new Color(224,33,138);
		Color cRosaClaro = new Color (255,128,234);
		
		this.setSize(new Dimension(400,400));
		this.setTitle("Naufragio en el Pacífico");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel norte = new JPanel();
		JPanel sur = new JPanel();
		sur.setLayout(new GridLayout(2,1)); //(filas,columnas)
		JPanel centro = new JPanel();
		centro.setLayout(new GridLayout(3,2));
		JPanel este = new JPanel();
		JPanel oeste = new JPanel();
	
		JLabel limagen = new JLabel();
		ImageIcon imagen = new ImageIcon("Media/logoNaufragio.png");
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));
		limagen.setIcon(icono);
		
		//NORTE
		/*JLabel titulo = new JLabel("NAUFRAGIO EN EL PACÍFICO");
		titulo.setForeground(cRosa);
		titulo.setFont(new Font("Arial", Font.BOLD, 22));
		norte.add(titulo);*/
		
		norte.add(limagen);
		
		//SUR
		JButton bEnter = new JButton("ENTRAR");
		bEnter.setBackground(cRosa);
		bEnter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new pantallaModoJuego();
				dispose();
				
			}
		});
		
		sur.add(bEnter);
		JButton bRegistro = new JButton("REGISTRAR");
		bRegistro.setBackground(cRosa);
		sur.add(bRegistro);
		
		//CENTRO
		JLabel inicio = new JLabel("Iniciar sesión");
		inicio.setHorizontalAlignment(SwingConstants.CENTER);
		inicio.setFont(new Font("Arial", Font.BOLD, 18));
		centro.add(inicio);
		JTextField nick = new JTextField("Introduce tu nick");  //Hacer un listener
		nick.setSize(new Dimension(50,50));
		nick.setBackground(cRosaClaro);
		centro.add(nick);
		JPasswordField pass = new JPasswordField();
		pass.setBackground(cRosaClaro);
		centro.add(pass);
		
		
	
	
		
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
