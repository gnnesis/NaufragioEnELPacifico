package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entidades.Barco;
import entidades.Casilla;
import entidades.Nivel;

@SuppressWarnings("serial")
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
	private int aguas = 0;
	private int tocados = 0;
	private Casilla[][] tablero;
	private Logger LOG = Logger.getLogger(PantallaJuego.class.getName());
	private Nivel nivel;
	private Random random = new Random(System.currentTimeMillis());
	private Clip clip = PantallaInicio.clip;

	
	public PantallaJuego(String imagenCasilla, Nivel nivel){
		this.nivel = nivel;
		this.setTitle("Naufragio en el Pacífico");
		this.setSize(new Dimension(900,600));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo( null );
		Image iconImage = new ImageIcon("Media/IconoNP.png").getImage();
        setIconImage(iconImage);
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
		LOG.log(Level.INFO,"Cronómetro de juego iniciado.");
		String ruta = Rutas.DIR_IMAGENES + imagenCasilla;
		ImageIcon fondo = null;
		try {
			Image img = ImageIO.read(new File(ruta));
			fondo = new ImageIcon(img);
		} catch (IOException e1) {
			LOG.log(Level.SEVERE,"Ha ocurrido un error cargando el icono de la celda.");
		}
		
		
		JMenuBar menu = new JMenuBar();
    	JMenu archivo = new JMenu("Archivo");
    	JMenu musica = new JMenu("Musica");
    	
    	JSlider volumen = new JSlider();
    	volumen.addChangeListener((ChangeListener) new ChangeListener() {
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
    	
    	
    	JMenuItem abandonar= new JMenuItem("Abandonar");
    	abandonar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PantallaModoJuego();
				dispose();
				
			}
		});
    	
    	archivo.add(salir);
    	archivo.add(abandonar);
    	menu.add(archivo);
    	menu.add(musica);
    
    	setJMenuBar(menu);
		
		JPanel pantNorte = new JPanel();
		JPanel pantSur = new JPanel();
		JPanel pantCentro = new JPanel();
		pantCentro.setLayout(new GridLayout(nivel.getColumnas(),nivel.getFilas()));
		JPanel pantEste = new JPanel();
		JPanel pantOeste = new JPanel();
		pantOeste.setLayout(new GridLayout(5,2));
		
		//NORTE
		JLabel titulo = new JLabel("Naufragio en el Pacífico");
		pantNorte.add(titulo);
		
		//CENTRO
		tablero = new Casilla[nivel.getColumnas()][nivel.getFilas()];
		for (int i=0; i<nivel.getColumnas();i++) {
			for (int j=0; j<nivel.getFilas();j++) {
				Casilla boton = new Casilla();
				boton.setIcon(fondo);
				tablero[i][j] = boton;
				boton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						numClicks++;
						l4.setText(String.valueOf(numClicks));
						
						boton.setFocusable(false);
						if(!boton.isDestapado())
						{
							boton.setIcon(null);
							boton.setDestapado(true);
							if(boton.isHayBarco())
							{
								tocados++;
								l8.setText("" + tocados);
								try {
									boton.setIcon(new ImageIcon(ImageIO.read(new File(Rutas.DIR_IMAGENES + "Tocado" + imagenCasilla))));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								LOG.log(Level.INFO,"La celda contenia un barco.");
								boolean terminado = juegoTerminado();
								if (terminado) {
									mostrarPuntuacion();
								}
							}
							else
							{
								aguas++;
								l6.setText(""+aguas);
								try {
									boton.setIcon(new ImageIcon(ImageIO.read(new File(Rutas.DIR_IMAGENES + "Agua" + imagenCasilla))));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						}
						
					}
				});
				pantCentro.add(boton);
			}
		}
		colocarBarcos();
		
		//ESTE
		String nombreArchivo = imagenCasilla;
        int indicePunto = nombreArchivo.lastIndexOf('.');
        String nombreSinExtension = (indicePunto != -1) ? nombreArchivo.substring(0, indicePunto) : nombreArchivo;		
		
		JLabel plantBarco = new JLabel();
		ImageIcon plantBarcoImg = new ImageIcon(Rutas.DIR_IMAGENES + "Plant"+nombreSinExtension+nivel.getColumnas()+"x"+nivel.getFilas()+".png");
		Image img = plantBarcoImg.getImage().getScaledInstance(200, 500, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(img);

		plantBarco.setIcon(scaledIcon);
		pantEste.add(plantBarco);
		
		LOG.log(Level.INFO,"Se añade la plantilla de barcos de temática "+nombreSinExtension+" de tamaño "+nivel.getColumnas()+"x"+nivel.getFilas()+".");
		

		//OESTE
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
	
	private boolean juegoTerminado() {
		boolean fin = true;
		//cada vez que se destape la casilla hay que comprobar si el juego ha terminado.
		for (int i=0; i<nivel.getColumnas();i++) {
			for (int j=0; j<nivel.getFilas();j++) {
				if (tablero[i][j].isHayBarco()&& !tablero[i][j].isDestapado()) {
					fin = false;
				}
			}
		}
		return fin;
	}
	
	private void colocarBarcos() {
		Barco[] barcos = nivel.getBarcos();
		int i = Integer.MIN_VALUE;
		int j = i;
		
		boolean vertical;
		
		for(Barco b : barcos)
		{
			vertical = random.nextBoolean();
			int celdas = b.getNumCeldas();
			encontrarPosiciones(i,j,vertical,celdas);
		}
	}
	
	private void encontrarPosiciones(int i, int j, boolean vertical, final int celdas) {
	    int limite;
	    if (vertical) {
	        limite = nivel.getColumnas() - celdas;
	    } else {
	        limite = nivel.getFilas() - celdas;
	    }

	    do {
	        i = random.nextInt(nivel.getColumnas());
	        j = random.nextInt(nivel.getFilas());
	    } while ((i > limite && vertical) || (j > limite && !vertical) || !posicionDisponible(i, j, vertical, celdas));

	    colocarBarco(i, j, vertical, celdas);
	    LOG.log(Level.INFO, "Se coloca el barco en la posicion ["+i+"]["+j+"] "+ vertical);
	}

	private boolean posicionDisponible(int i, int j, boolean vertical, int celdas) {
	    for (int k = 0; k < celdas; k++) {
	        if (vertical) {
	            if (tablero[i + k][j].isHayBarco()) {
	                return false;
	            }
	        } else {
	            if (tablero[i][j + k].isHayBarco()) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	private void colocarBarco(int i, int j, boolean vertical, int celdas) {
	    for (int k = 0; k < celdas; k++) {
	        if (vertical) {
	            tablero[i + k][j].setHayBarco(true);
	        } else {
	            tablero[i][j + k].setHayBarco(true);
	        }
	    }
	}
	
	private void mostrarPuntuacion() {
		int minutosJuego = minutos;
		int segundosJuego = segundos;
		int clicksTotales = numClicks;
		JOptionPane.showMessageDialog(null, "Enhorabuena, has solucionado el tablero");
		LOG.log(Level.INFO, "Tablero finalizado");
		new PantallaPuntuacion(minutosJuego, segundosJuego, clicksTotales);
		dispose();
	}
}