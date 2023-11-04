package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class PantallaJuego extends JFrame{
	private int numClicks = 0;
	
	private final JLabel l1 = new JLabel ("Tiempo");
	private JLabel l2 = new JLabel ("00:00");
	private final JLabel l3 = new JLabel ("Clicks totales:");
	private JLabel l4 = new JLabel ("0");
	private final JLabel l5 = new JLabel ("Aguas: ");
	private JLabel l6 = new JLabel ("0");
	private final JLabel l7 = new JLabel ("Tocados:");
	private  JLabel l8 = new JLabel ("0");
	private final JLabel l9 = new JLabel ("Hundidos: ");
	private  JLabel l10 = new JLabel ("0");
	private int minutos = 0;
	private int segundos = 0;
	
	public PantallaJuego(){
		
		this.setTitle("Naufragio en el Pacífico");
		this.setSize(new Dimension(600,600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		Timer tiempo = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				segundos++;
				
				if(segundos == 60)
				{
					segundos = 0;
					minutos++;
				}
				
				l2.setText(String.format("%02d:%02d", minutos, segundos));
			}
			
		});
		
		tiempo.start();
		JPanel pantNorte = new JPanel();
		JPanel pantSur = new JPanel();
		JPanel pantCentro = new JPanel();
		pantCentro.setLayout(new GridLayout(9,9));
		JPanel pantEste = new JPanel();
		JPanel pantOeste = new JPanel();
		pantOeste.setLayout(new GridLayout(5,2));
		
		//NORTE
		JLabel titulo = new JLabel("Naufragio en el Pacífico");
		pantNorte.add(titulo);
		
		
		//SUR
		
		
		//CENTRO
		
		for (int i=0; i<9;i++) {
			for (int j=0; j<9;j++) {
				JButton boton = new JButton();
				boton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						numClicks++;
						l4.setText(String.valueOf(numClicks));
						
					
						
					}
				});
				pantCentro.add(boton);
				
				
				
			}
		}
		
		
		//ESTE
		JTextArea barcos = new JTextArea("Barco1 \n Barco2 \n Barco3 \n Barco4 \n Barco5 \n");
		pantEste.add(barcos);
		
		//OESTE
		//JTextArea info = new JTextArea("Tiempo \n Clicks Totales \n Aguas \n Tocados \n Hundidos \n");
		//pantOeste.add(info);
		
		pantOeste.add(l1);
		
		pantOeste.add(l2);
		
		pantOeste.add(l3);
		
		pantOeste.add(l4);
		pantOeste.add(l5);
		
		pantOeste.add(l6);
		pantOeste.add(l7);
		
		pantOeste.add(l8);
		
		pantOeste.add(l9);
		
		pantOeste.add(l10);
		
		this.add(pantNorte,BorderLayout.NORTH);
		this.add(pantSur,BorderLayout.SOUTH);
		this.add(pantCentro, BorderLayout.CENTER);
		this.add(pantEste,BorderLayout.EAST);
		this.add(pantOeste,BorderLayout.WEST);
		this.setVisible(true);
	
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PantallaJuego();

	}


}
