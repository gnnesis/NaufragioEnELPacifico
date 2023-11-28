package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PantallaPuntuacion extends JFrame {
	
	private JLabel lTiempo;
	private JLabel lClicksTotales;
	private JLabel lAguas;
	private JLabel lTocados;
	private JLabel lHundidos;

    public PantallaPuntuacion(int minutos, int segundos, int numClicks) {
    	
    	Image iconImage = new ImageIcon("Media/TocadoHalloween.png").getImage();
        setIconImage(iconImage);
    	Color cRosa= new Color(255,102,196);
        // Básicos
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        this.setTitle("Puntuación");
        this.setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // 1. Crear contenedores
        JPanel pSuperior = new JPanel();
        JPanel pInferior = new JPanel();
        JPanel pDerecha = new JPanel();
        JPanel pIzquierda = new JPanel();
        JPanel pCentral = new JPanel(new GridLayout(3,1));

        // Ubicar contenedores
        this.add(pSuperior, BorderLayout.NORTH);
        this.add(pInferior, BorderLayout.SOUTH);
        this.add(pDerecha, BorderLayout.EAST);
        this.add(pIzquierda, BorderLayout.WEST);
        this.add(pCentral, BorderLayout.CENTER);

        // 2. Crear componentes
        JLabel lTitulo = new JLabel("NAUFRAGIO EN EL PACÍFICO");
        JLabel lResumen = new JLabel("RESUMEN DE LA PARTIDA");
        JButton bJugar= new JButton("VOLVER A JUGAR");
        bJugar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new PantallaModoJuego();
	        	dispose();	
			}
        });
        
        //3.Diseño de componentes
        bJugar.setBackground(cRosa);
        bJugar.setFont(new Font("Consolas", Font.BOLD, 12));
        lTitulo.setForeground(cRosa);
        lResumen.setForeground(cRosa);
        lResumen.setFont(new Font("Open Sans", Font.BOLD, 16));        
        
        //NUEVO
        lTiempo = new JLabel("Tiempo: " + String.format("%02d:%02d", minutos, segundos));
        lClicksTotales = new JLabel ("Clicks totales: " + numClicks);      
        
        //4.Añadir componentes a contenedores
        pSuperior.add(lTitulo);
        pCentral.add(lResumen);
        pCentral.add(lTiempo);
        pCentral.add(lClicksTotales);
        pInferior.add(bJugar) ;
        
        this.setVisible(true);
    }
}