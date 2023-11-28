package proyectoNaufragio;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PantallaPerfil extends JFrame {

	public PantallaPerfil( ) {
		
		Image iconImage = new ImageIcon("Media/TocadoHalloween.png").getImage();
        setIconImage(iconImage);
		
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 450);
        this.setTitle("Perfil");
        
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

        JLabel usuario = new JLabel("Nickname: XXXX-XXXX");
        usuario.setSize(new Dimension(50, 50));
        usuario.setBackground(Rosita);
        pantCentro.add(usuario);

        JLabel partidas = new JLabel("Partidas totales: 89");
        partidas.setBackground(Rosita);
        pantCentro.add(partidas);
        
        JLabel tiempoTotal = new JLabel("Tiempo total jugado: 63672 hrs");
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

        // ESTE
        JButton bSonido = new JButton("Sonido");
        JButton bMusica = new JButton("Música");
        JButton bInfo = new JButton("Información");
        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "BIENVENIDO A NAUFRAGIO EN EL PACÍFICO\r\n"
                        + "\r\n"
                        + "* Info general\r\n"
                        + " - Descripción\r\n"
                        + "* Reglas de juego\r\n"
                        + " - Explicación de cómo funciona el juego\r\n"
                        + "* Contacto\r\n"
                        + " - Email\r\n"
                        + " - Tel.\r\n"
                        + " - Redes sociales");
            }
        });
        pantEste.add(bInfo);
        pantEste.add(bMusica);
        pantEste.add(bSonido);
        
        // SUR
        JPanel filler1 = new JPanel(); // Panel de relleno
        JPanel filler2 = new JPanel(); // Panel de relleno
        
        JLabel titEstadis = new JLabel("Tus Estadísticas");
        titEstadis.setForeground(RositaClarito);
        titEstadis.setFont(new Font("Arial", Font.BOLD, 20));
        filler1.add(titEstadis);
        pantSur.add(filler1);
        
        String rutaFEstadis = "Media/logoNaufragio.png"; 
        JLabel PEstadis=new JLabel();
        ImageIcon estadis = new ImageIcon(rutaFEstadis);
        Icon IEstadis = new ImageIcon(estadis.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        PEstadis.setIcon(IEstadis);
        filler2.add(PEstadis);
        pantSur.add(filler2);
                
        JButton bJugar = new JButton("Modo Juego");
        bJugar.setBackground(Rosita);
        bJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PantallaModoJuego();
                dispose();
            }
        });
        JPanel p1= new JPanel();
        p1.add(bJugar);
        pantSur.add(p1);

        this.add(pantNorte, BorderLayout.NORTH);
        this.add(pantSur, BorderLayout.SOUTH);
        this.add(pantCentro, BorderLayout.CENTER);
        this.add(pantEste, BorderLayout.EAST);
        this.add(pantOeste, BorderLayout.WEST);
        this.setVisible(true);
        
    }
	 public static void main(String[] args) {
	        new PantallaPerfil();
	    }
}