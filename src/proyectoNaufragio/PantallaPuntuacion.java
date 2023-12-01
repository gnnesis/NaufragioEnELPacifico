package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PantallaPuntuacion extends JFrame {
	
	private JLabel lTiempo;
	private JLabel lClicksTotales;
	

    public PantallaPuntuacion(int minutos, int segundos, int numClicks) {
    	
    	Image iconImage = new ImageIcon("Media/IconoNP.png").getImage();
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
        JButton bVerRanking = new JButton ("VER RANKING");
        
        //Diseño
        lTitulo.setForeground(cRosa);
        lTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        lResumen.setForeground(cRosa);
        lResumen.setFont(new Font("Open Sans", Font.BOLD, 20));

        bJugar.setBackground(cRosa);
        bJugar.setForeground(Color.MAGENTA);
        bJugar.setFont(new Font("Consolas", Font.BOLD, 16));
      
        bJugar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new PantallaModoJuego();
	        	dispose();	
			}
        });
        
        //2.5 Crear botón ranking
        
       
        bVerRanking.setBackground(cRosa);
        bVerRanking.setForeground(Color.MAGENTA);
        bVerRanking.setFont(new Font("Consolas", Font.BOLD, 12));
        bVerRanking.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		verRanking();
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
        pInferior.add(bVerRanking);
        
        //5. Añadir bordes y espacios entre componentes
        pSuperior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        pCentral.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        pInferior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        this.setVisible(true);
    }
    
    
    private void verRanking() {
    	new PantallaRanking();
    	
    }
    
    public static void main(String[] args) {
    	//Esto es para un ejemplo de uso
    	new PantallaPuntuacion(2,30,50);
    }
}