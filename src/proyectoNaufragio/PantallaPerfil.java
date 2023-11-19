package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PantallaPerfil extends JFrame {


	public PantallaPerfil() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
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
        pantCentro.setLayout(new GridLayout(9, 9));
        pantEste.setLayout(new GridLayout(3, 1));

        // NORTE
        JLabel titUs = new JLabel("Usuario");
        titUs.setForeground(RositaClarito);
        titUs.setFont(new Font("Arial", Font.BOLD, 40));
        pantNorte.add(titUs);

        // CENTRO
        JTextField usuario = new JTextField("Nickname: ");
        usuario.setBackground(Rosita);
        pantCentro.add(usuario);

        JTextField mail = new JTextField("EMail: ");
        mail.setBackground(Rosita);
        pantCentro.add(mail);

        JTextField partidas = new JTextField("Partidas totales: ");
        partidas.setBackground(Rosita);
        pantCentro.add(partidas);
        // Falta añadir el icono o perfil de usuario

        // OESTE
        pantOeste.setLayout(new GridLayout(9, 9));

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
        JButton bJugar = new JButton("Modo Juego");
        bJugar.setBackground(Rosita);
        bJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para ir a la clase "pantallaModoJuego"
                new PantallaModoJuego();
                dispose(); // Cierra ventana
            }
        });
        pantSur.add(bJugar);

        this.add(pantNorte, BorderLayout.NORTH);
        this.add(pantSur, BorderLayout.SOUTH);
        this.add(pantCentro, BorderLayout.CENTER);
        this.add(pantEste, BorderLayout.EAST);
        this.add(pantOeste, BorderLayout.WEST);
        this.setVisible(true);
    }
}