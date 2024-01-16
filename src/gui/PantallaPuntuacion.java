package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domain.Usuario;

@SuppressWarnings("serial")
public class PantallaPuntuacion extends JFrame {
	
	private JLabel lTiempo;
	private JLabel lClicksTotales;
	private Clip clip = PantallaInicio.clip;
	private Usuario u;
	
    public PantallaPuntuacion(Usuario u, int minutos, int segundos, int numClicks) {
    	this.u = u;
    	
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setTitle("Puntuación");
        this.setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
    	Image iconImage = new ImageIcon("resources/images/IconoNP.png").getImage();
        setIconImage(iconImage);
    	Color cRosa= new Color(255,102,196);
    	
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
    	mute.addActionListener((ActionListener) new ActionListener() {

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
    	
    	archivo.add(salir);
    	menu.add(archivo);
    	menu.add(musica);
    
    	setJMenuBar(menu);

        JPanel pSuperior = new JPanel();
        JPanel pInferior = new JPanel();
        JPanel pDerecha = new JPanel();
        JPanel pIzquierda = new JPanel();
        JPanel pCentral = new JPanel(new GridLayout(3,1));

        this.add(pSuperior, BorderLayout.NORTH);
        this.add(pInferior, BorderLayout.SOUTH);
        this.add(pDerecha, BorderLayout.EAST);
        this.add(pIzquierda, BorderLayout.WEST);
        this.add(pCentral, BorderLayout.CENTER);

        JLabel lTitulo = new JLabel("NAUFRAGIO EN EL PACÍFICO");
        JLabel lResumen = new JLabel("RESUMEN DE LA PARTIDA");
        JButton bJugar= new JButton("VOLVER A JUGAR");
        JButton bVerRanking = new JButton ("VER RANKING");
        JButton bVerRankingGlobal = new JButton ("VER RANKING GLOBAL");
        
        lTitulo.setForeground(cRosa);
        lTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        lResumen.setForeground(cRosa);
        lResumen.setFont(new Font("Open Sans", Font.BOLD, 20));

        bJugar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new PantallaModoJuego(u);
	        	dispose();	
			}
        });        
    
        bVerRanking.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		verRanking();
        	}
        });
        
        bVerRankingGlobal.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		verRankingGlobal();
        	}
        });
        
        bJugar.setBackground(cRosa);
        bVerRanking.setBackground(cRosa);
        bVerRankingGlobal.setBackground(cRosa);
        
        lTitulo.setForeground(cRosa);
        lResumen.setForeground(cRosa);
        lResumen.setFont(new Font("Open Sans", Font.BOLD, 16));        
        
        
        lTiempo = new JLabel("Tiempo: " + String.format("%02d:%02d", minutos, segundos));
        lClicksTotales = new JLabel ("Clicks totales: " + numClicks);      
        
        pSuperior.add(lTitulo);
        pCentral.add(lResumen);
        pCentral.add(lTiempo);
        pCentral.add(lClicksTotales);
        pInferior.add(bJugar) ;
        pInferior.add(bVerRanking);
        pInferior.add(bVerRankingGlobal);
        
        pSuperior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pCentral.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        pInferior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        this.setVisible(true);
    }

	private void verRanking() {
    	new PantallaRanking(u, false);	
    }
	
	private void verRankingGlobal() {
    	new PantallaRanking(u, true);	
    }
}