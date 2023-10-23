package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class pantallaPerfil extends JFrame{
	
	public pantallaPerfil() {
		this.setVisible(true);
		this.getDefaultCloseOperation();
		this.setSize(600,400);
		this.setTitle("Perfil");
		
		//los colores
		Color Rosita = new Color(255,102,196);
		Color RositaClarito= new Color (255,128,234);
		
		
		this.setLayout(new BorderLayout());
		JPanel pantNorte = new JPanel();
		JPanel pantSur = new JPanel();
		JPanel pantEste = new JPanel();
		JPanel pantOeste = new JPanel();
		JPanel pantCentro = new JPanel();
		pantCentro.setLayout(new GridLayout(9,9));
		
		//NORTE
	//	JLabel tit = new JLabel ("Naufragio en el Pacífico \n");
		//pantNorte.add(tit);
		
		JLabel titUs = new JLabel ("Usuario");
		titUs.setForeground(RositaClarito);
		titUs.setFont(new Font("Arial", Font.BOLD, 40));
		pantNorte.add(titUs);
		
		//CENTRO
		JTextField usuario = new JTextField ("Nickname: ");
		usuario.setBackground(Rosita);
		pantCentro.add(usuario);
		
		JTextField mail = new JTextField ("EMail: ");
		mail.setBackground(Rosita);
		pantCentro.add(mail);
		
		JTextField partidas = new JTextField ("Partidas totales: ");
		partidas.setBackground(Rosita);
		pantCentro.add(partidas);
		//Falta añadir el icono o perfil de usuario
		
		//OESTE
		pantOeste.setLayout(new GridLayout(9,9));
		
	
		
		this.add(pantNorte,BorderLayout.NORTH);
		this.add(pantSur,BorderLayout.SOUTH);
		this.add(pantCentro, BorderLayout.CENTER);
		this.add(pantEste,BorderLayout.EAST);
		this.add(pantOeste,BorderLayout.WEST);
		this.setVisible(true);
	
		
		
		
	}

	public static void main(String args[]) {
		new pantallaPerfil();
	}
}
