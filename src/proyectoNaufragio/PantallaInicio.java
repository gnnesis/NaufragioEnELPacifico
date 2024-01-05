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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import database.BBDD;
import entidades.Usuario;
import excepciones.UsuarioIncorrectoException;
import excepciones.UsuarioNoEncontradoException;

@SuppressWarnings("serial")
public class PantallaInicio extends JFrame {
	
	private static String FICH_USUARIOS;
	private static String logo;
	private static Logger LOG = Logger.getLogger(PantallaInicio.class.getName());
	private JTextField nick;
	private JPasswordField pass;
	private static ArrayList<Usuario> usuarios;

	// Necesario ser publico para gestionarlo entre ventanas
	public static Clip clip;
	private AudioInputStream audioInputStream;
	private static final String filePath = "resources/images/Musica.wav";
	
    public PantallaInicio() {
    	
    	Image iconImage = new ImageIcon("resources/images/IconoNP.png").getImage();
        setIconImage(iconImage);
    	
        inicializarAudio();
        
        Color cRosa = new Color(255, 102, 196);
        Color cRosaClaro = new Color(255, 128, 234);

        this.setSize(new Dimension(600, 400));
        this.setTitle("Naufragio en el Pacífico");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel norte = new JPanel();
        JPanel sur = new JPanel();
        sur.setLayout(new GridLayout(2, 1)); // (filas,columnas)
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(3, 2));
        JPanel este = new JPanel();
        JPanel oeste = new JPanel();
        
        JLabel limagen = new JLabel();
        ImageIcon imagen = new ImageIcon(logo);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(341,128, Image.SCALE_DEFAULT));
        limagen.setIcon(icono);
        
        JMenuBar menu = new JMenuBar();
    	JMenu archivo = new JMenu("Archivo");
    	JMenu musica = new JMenu("Musica");
    	
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
    	
    	archivo.add(salir);
    	menu.add(archivo);
    	menu.add(musica);
    
    	setJMenuBar(menu);

        // NORTE
        norte.add(limagen);

        // SUR
        JButton bEnter = new JButton("ENTRAR");
        JPanel s1 = new JPanel();
        JPanel s2 = new JPanel();
        bEnter.setBackground(cRosa);
        bEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nick.getText();
                String password = String.valueOf(pass.getPassword());
                boolean encontrado = false;
                try {
                	
                    for (Usuario u : usuarios) {
                        if (u.getNickname().equals(username)) {
                            LOG.log(Level.INFO, "Usuario encontrado");
                            encontrado=true;
                            if (u.comprobarContrasena(password)) {
                                LOG.log(Level.INFO, "Inicio de sesión correcto");
                                new PantallaModoJuego(u);
                                dispose();
                            } else {
                                throw new UsuarioIncorrectoException();
                            }
                        }
                    }
                    if (!encontrado) {
                        throw new UsuarioNoEncontradoException();
                    }
                } catch (UsuarioIncorrectoException | UsuarioNoEncontradoException ex) {
                    LOG.log(Level.WARNING, ex.getMessage());
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        
        s1.add(bEnter);
        sur.add(s1);
        JButton bRegistro = new JButton("REGISTRAR");
        bRegistro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usuario = JOptionPane.showInputDialog(null, "Introduce tu nombre de usuario");
				String pass = JOptionPane.showInputDialog(null, "Introduce tu contraseña");
				
				if(usuario != null & pass != null )
				{
					Usuario u = new Usuario (usuario, pass);
					u.registrar(FICH_USUARIOS);
					JOptionPane.showMessageDialog(null, "Usuario creado");
					LOG.log(Level.INFO, "Se ha añadido un nuevo usuario al sistema");
					usuarios = cargarUsuarios();
				}
				else
				{
					LOG.log(Level.WARNING, "Intento de registro fallido");
					JOptionPane.showMessageDialog(null, "Es necesario completar todos los datos");
				}
			}
        });
        
        bRegistro.setBackground(cRosa);
        s2.add(bRegistro);
        sur.add(s2);

        // CENTRO
        JLabel inicio = new JLabel("Iniciar sesión");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
       
        inicio.setHorizontalAlignment(SwingConstants.CENTER);
        inicio.setFont(new Font("Arial", Font.BOLD, 18));
        centro.add(inicio);
        nick = new JTextField("Nickname");
        nick.setSize(new Dimension(50, 50));
        nick.setBackground(cRosaClaro);
        nick.setColumns(30);
        p1.add(nick);
        centro.add(p1);
        pass = new JPasswordField();
        pass.setBackground(cRosaClaro);
        pass.setColumns(30);
        p2.add(pass);
        centro.add(p2);
        
        this.add(norte, BorderLayout.NORTH);
        this.add(sur, BorderLayout.SOUTH);
        this.add(centro, BorderLayout.CENTER);
        this.add(este, BorderLayout.EAST);
        this.add(oeste, BorderLayout.WEST);
        this.setVisible(true);
    }
    
    private void inicializarAudio()  {
    	
    	try
    	{
    		audioInputStream = AudioSystem.getAudioInputStream(new File (filePath).getAbsoluteFile());
        	
        	clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    private static void cargarPropiedades()
    {
    	try {
    		Properties p = new Properties();
			p.load(new FileInputStream(Rutas.FICH_PROPERTIES));
			FICH_USUARIOS = p.getProperty("usuarios");
			logo = p.getProperty("logo");
		} catch (Exception e1) {
			e1.printStackTrace();
			LOG.log(Level.WARNING,"No se ha podido cargar el fichero propiedades.");
		}
    }
    
    private static ArrayList<Usuario> cargarUsuarios()
    {
    	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    	final String separador = ";";
    	Usuario u;
    	String linea;
    	BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(FICH_USUARIOS));
			while((linea = br.readLine()) != null)
			{
				String[] params = linea.split(separador);
				u = new Usuario(params[0].trim(), params[1].trim());
				usuarios.add(u);
				LOG.log(Level.INFO, "Se incorpora un nuevo usuario al sistema");
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    	return usuarios;
    }
    
    public static void main(String[] args) {
    	cargarPropiedades();
    	usuarios = cargarUsuarios();
    	
    	new BBDD().checkEstado().thenAccept(correcto -> {
    		if(!correcto)
    		{
    			JOptionPane.showMessageDialog(null, "No se ha podido establecer conexion con la base de datos");
    		}
    	});

    	new PantallaInicio();
    }
}