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

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entidades.Barco;
import entidades.Nivel;
import entidades.Usuario;

@SuppressWarnings("serial")
public class PantallaModoJuego extends JFrame {
	
	private static String FICH_TEMATICAS;
	public static String INSTRUCCIONES;
	private static String FICH_NIVELES;
	private static ArrayList<Nivel> niveles;
	private static ArrayList<JRadioButton> rTematicas = new ArrayList<>();
	private static ArrayList<JRadioButton> rNiveles = new ArrayList<>();
	private Clip clip = PantallaInicio.clip;

	private Logger LOG = Logger.getLogger(PantallaModoJuego.class.getName());
	
	public PantallaModoJuego(Usuario u) {
		
		cargarPropiedades();
		Color cRosa = new Color(255,102,196);
		Font subtitulo = new Font("Arial", Font.BOLD, 18);	

		this.setSize(new Dimension(400,400));
		this.setTitle("Naufragio en el Pacífico");
		setLocationRelativeTo( null );
		Image iconImage = new ImageIcon("resources/images/IconoNP.png").getImage();
        setIconImage(iconImage);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JMenuBar menu = new JMenuBar();
    	JMenu archivo = new JMenu("Archivo");
    	JMenu musica = new JMenu("Musica");
    	JMenu perfil = new JMenu("Perfil");
    	JMenuItem verPerfil = new JMenuItem("Ver Perfil");
    	JMenuItem instrucciones = new JMenuItem("Instrucciones");
    	
    	verPerfil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaPerfil(u);
			}
    		
    	});
    	
    	JSlider volumen = new JSlider();
    	volumen.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int volumen = ((JSlider) e.getSource()).getValue();
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				float range = gainControl.getMaximum() - gainControl.getMinimum();
				float gain = (range * volumen / 100.0f) + gainControl.getMinimum();
				gainControl.setValue(gain);
			}
    		
    	});
    	
    	JMenuItem mute = new JMenuItem("Pause/Play");
    	mute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (clip.isActive()) {
					clip.stop();
				} else {
					clip.start();
				}
			}
    	});
    	
    	musica.add(volumen);
    	musica.add(mute);
    	
    	JMenuItem salir = new JMenuItem("Salir");
    	salir.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	});
    	JMenuItem cerrarSesion= new JMenuItem("Cerrar Sesión");
    	cerrarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaInicio();
				dispose();
				
			}
		});
    	
    	instrucciones.addActionListener(new ActionListener(){
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			mostrarInstrucciones();
    		}
    	});
    	
    	archivo.add(salir);
    	archivo.add(instrucciones);
    	archivo.add(cerrarSesion);
    	perfil.add(verPerfil);
    	menu.add(archivo);
    	menu.add(musica);
    	menu.add(perfil);
    	setJMenuBar(menu);
		
		
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
				String tematica = "";
				Nivel n = null;
				String nivel = "";
				
				for(JRadioButton r : rNiveles)
				{
					if(r.isSelected())
						nivel = r.getText();
				}
				
				for(JRadioButton r : rTematicas)
				{
					if(r.isSelected())
						tematica = r.getText();
				}
				
				for(Nivel ni : niveles)
				{
					if(ni.getNombre().equals(nivel))
						n = ni;
				}
				
				new PantallaJuego(u, tematica + ".png", n);
				dispose();
			}
		});
		sur.add(bJugar);
		
		//CENTRO
		JLabel lTablero = new JLabel("DIFICULTAD");
		lTablero.setFont(subtitulo);
		centro.add(lTablero);
		JLabel lTematica = new JLabel("TEMÁTICA");
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
			rTematicas.add(rb);
			p.add(rb);
			LOG.log(Level.INFO, "Se incluye la tematica " + s);
		}
		
		return p;
	}
	
	private JPanel cargarNiveles()
	{
		niveles = new ArrayList<>();
		JPanel p;
		Barco b;
		String nivel;
		ButtonGroup bg = new ButtonGroup();
		final String separador = ";";
		
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(FICH_NIVELES));
			while((nivel = br.readLine()) != null)
			{
				String[] params = nivel.split(separador);
				int numBarcos = Integer.parseInt(params[3]);
				Nivel n = new Nivel(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]), numBarcos);
				for(int i = 4; i < 4 + numBarcos; i++)
				{
					b = new Barco(Integer.parseInt(params[i]));
					n.anadirBarco(b);
					LOG.log(Level.INFO, "Se añade un barco para el nivel " + n.getNombre());
				}
				niveles.add(n);
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		p = new JPanel(new GridLayout(niveles.size(),1));
		
		for(Nivel n : niveles)
		{
			JRadioButton rb = new JRadioButton(n.getNombre());
			bg.add(rb);
			rNiveles.add(rb);
			p.add(rb);
			LOG.log(Level.INFO, "Se incluye el nivel " + n.getNombre());
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
			INSTRUCCIONES = p.getProperty("instrucciones");
		} catch (Exception e1) {
			e1.printStackTrace();
			LOG.log(Level.WARNING,"No se ha podido cargar el fichero propiedades.");
		}	
	}
	
	private void mostrarInstrucciones() {
		JOptionPane.showMessageDialog(null, INSTRUCCIONES);
	}
}
