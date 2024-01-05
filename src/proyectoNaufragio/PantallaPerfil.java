package proyectoNaufragio;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import entidades.Usuario;

@SuppressWarnings("serial")
public class PantallaPerfil extends JFrame {
	private Clip clip = PantallaInicio.clip;
	private Usuario u;


	public PantallaPerfil(Usuario u) {
		this.u = u;
		Image iconImage = new ImageIcon("Media/IconoNP.png").getImage();
        setIconImage(iconImage);
		
        this.setVisible(true);
        this.setSize(600, 450);
        this.setTitle("Perfil");
        
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
    	
    	archivo.add(salir);
    	menu.add(archivo);
    	menu.add(musica);
    
    	setJMenuBar(menu);
        
        // Colores
        Color Rosita = new Color(255, 102, 196);
        Color RositaClarito = new Color(255, 128, 234);

        this.setLayout(new BorderLayout());
        JPanel pantNorte = new JPanel();
        JPanel pantSur = new JPanel();
        JPanel pantEste = new JPanel();
        JPanel pantOeste = new JPanel();
        JPanel pantCentro = new JPanel();
        pantCentro.setLayout(new GridLayout(4, 1));
        pantEste.setLayout(new GridLayout(3, 1));
        pantSur.setLayout(new GridLayout(3, 1));

        // NORTE
        String rutaImagen = "Media/logoNaufragio.png"; 
        JLabel Pimagen=new JLabel();
        ImageIcon imagen = new ImageIcon(rutaImagen);
        Icon foto = new ImageIcon(imagen.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
        Pimagen.setIcon(foto);
        pantNorte.add(Pimagen);
         
        // CENTRO
        JLabel titUs = new JLabel("Usuario");
        titUs.setForeground(RositaClarito);
        titUs.setFont(new Font("Arial", Font.BOLD, 20));
        pantCentro.add(titUs);

        JLabel usuario = new JLabel("Nickname: "+u.getNickname());
        usuario.setSize(new Dimension(50, 50));
        usuario.setBackground(Rosita);
        pantCentro.add(usuario);

        JLabel partidas = new JLabel("Partidas totales: ");
        partidas.setBackground(Rosita);
        pantCentro.add(partidas);
        
        JLabel tiempoTotal = new JLabel("Tiempo total jugado: ");
        tiempoTotal.setBackground(Rosita);
        pantCentro.add(tiempoTotal);
        
        // OESTE
        pantOeste.setLayout(new GridLayout(1,1));
        String rutaFPerfil = "Media/TocadoHalloween.png"; 
        JLabel fPerfil=new JLabel();
        ImageIcon perfil = new ImageIcon(rutaFPerfil);
        Icon icono1 = new ImageIcon(perfil.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        fPerfil.setIcon(icono1);
        pantOeste.add(fPerfil);
        
        // SUR
        JPanel filler1 = new JPanel();
        
        JLabel titEstadis = new JLabel("Tus Estadísticas");
        titEstadis.setForeground(RositaClarito);
        titEstadis.setFont(new Font("Arial", Font.BOLD, 20));
        filler1.add(titEstadis);
        
        JButton filler2 = new JButton("Ver estadisticas");
        filler2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						generarEstadisticasUsuario(u);
					}
				});
			}
        });
        pantSur.add(filler2);

        this.add(pantNorte, BorderLayout.NORTH);
        this.add(pantSur, BorderLayout.SOUTH);
        this.add(pantCentro, BorderLayout.CENTER);
        this.add(pantEste, BorderLayout.EAST);
        this.add(pantOeste, BorderLayout.WEST);
        this.setVisible(true);
        
    }
	
	private void generarEstadisticasUsuario(Usuario u)
	{
		double[] xData = new double[] { 0.0, 1.0, 2.0 };
		double[] yData = new double[] { 2.0, 1.0, 0.0 };
		
		// Create Chart
		XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
		
		new JFrame().add(new SwingWrapper<XYChart>(chart).displayChart());

	}
}