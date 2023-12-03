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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidades.Usuario;

@SuppressWarnings("serial")
public class PantallaInicio extends JFrame {
	
	private static String FICH_USUARIOS;
	private static String logo;
	private static Logger LOG = Logger.getLogger(PantallaInicio.class.getName());
	private JTextField nick;
	private JPasswordField pass;
	private static ArrayList<Usuario> usuarios;
	
    public PantallaInicio() {
    	
    	Image iconImage = new ImageIcon("Media/IconoNP.png").getImage();
        setIconImage(iconImage);
    	
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
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT));
        limagen.setIcon(icono);

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
            	
            	for(Usuario u : usuarios)
            	{
            		if(u.getNickname().equals(username))
            		{
            			LOG.log(Level.INFO, "Usuario encontrado");
            			System.out.println(password);
            			if(u.comprobarContrasena(password))
            			{
            				LOG.log(Level.INFO, "Inicio de sesion correcto");
            				new PantallaModoJuego();
                            dispose();
            			}
            			else
            			{
            				LOG.log(Level.WARNING, "Contraseña introducida incorrecta");
            			}
            		}
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

    public static void main(String[] args) {
    	cargarPropiedades();
    	usuarios = cargarUsuarios();
        new PantallaInicio();
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
}