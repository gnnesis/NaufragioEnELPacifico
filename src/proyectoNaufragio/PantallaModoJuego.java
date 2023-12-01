package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

@SuppressWarnings("serial")
public class PantallaModoJuego extends JFrame {
	
	private static String FICH_TEMATICAS;
	private static String FICH_NIVELES;
	private Logger LOG = Logger.getLogger(PantallaModoJuego.class.getName());
	
	public PantallaModoJuego(){
		
		Image iconImage = new ImageIcon("Media/IconoNP.png").getImage();
        setIconImage(iconImage);
        
		cargarPropiedades();
		Color cRosa = new Color(255,102,196);
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
				String tematica = "Clasico";
				new PantallaJuego(tematica + ".png");
				dispose();
			}
		});
		sur.add(bJugar);
		
		//CENTRO
		JLabel lTablero = new JLabel("DIFICULTAD");
		lTablero.setFont(subtitulo);
		centro.add(lTablero);
		JLabel lTematica = new JLabel("TEMATICA");
		lTematica.setFont(subtitulo);
		centro.add(lTematica);
		
		JPanel pDificultad = cargarNiveles();
		JPanel pTematica = cargarTematicas();
		
		centro.add(pDificultad);
		centro.add(pTematica);

		this.add(norte,BorderLayout.NORTH);
		this.add(sur,BorderLayout.SOUTH);
		this.add(centro, BorderLayout.CENTER);
		this.add(este,BorderLayout.EAST);
		this.add(oeste,BorderLayout.WEST);
		this.setVisible(true);
	}
	
	private JPanel cargarTematicas()
	{
		final String separador = ";";
		ArrayList<String> temas = new ArrayList<>();
		JPanel p;
		String tematica;
		ButtonGroup tematicas = new ButtonGroup();
		
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(FICH_TEMATICAS));
			while((tematica = br.readLine()) != null)
			{
				String[] params = tematica.split(separador);
				temas.add(params[0]);
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		p = new JPanel(new GridLayout(temas.size(),1));
		
		for(String s : temas)
		{
			JRadioButton rb = new JRadioButton(s);
			tematicas.add(rb);
			p.add(rb);
			LOG.log(Level.INFO, "Se incluye la tematica " + s);
		}
		
		return p;
	}
	
	private JPanel cargarNiveles()
	{
		ArrayList<String> niveles = new ArrayList<>();
		JPanel p;
		String nivel;
		ButtonGroup bg = new ButtonGroup();
		
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(FICH_NIVELES));
			while((nivel = br.readLine()) != null)
			{
				niveles.add(nivel);
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		p = new JPanel(new GridLayout(niveles.size(),1));
		
		for(String s : niveles)
		{
			JRadioButton rb = new JRadioButton(s);
			bg.add(rb);
			p.add(rb);
			LOG.log(Level.INFO, "Se incluye el nivel " + s);
		}
		return p;
	}
	
	private void cargarPropiedades()
	{
		try {
    		Properties p = new Properties();
			p.load(new FileInputStream(Rutas.FICH_PROPERTIES));
			FICH_TEMATICAS = p.getProperty("tematicas");
			FICH_NIVELES = p.getProperty("niveles");
		} catch (Exception e1) {
			e1.printStackTrace();
			LOG.log(Level.WARNING,"No se ha podido cargar el fichero propiedades.");
		}	
	}
}