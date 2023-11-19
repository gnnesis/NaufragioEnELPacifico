package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PantallaPuntuacion extends JFrame {

    public PantallaPuntuacion() {
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
        JPanel pCentral = new JPanel();

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
        
        //3.Diseño de componentes
        bJugar.setBackground(cRosa);
        bJugar.setFont(new Font("Consolas", Font.BOLD, 12));
        lTitulo.setForeground(cRosa);
        lResumen.setForeground(cRosa);
        lResumen.setFont(new Font("Open Sans", Font.BOLD, 16));
        
        //4.Añadir componentes a contenedores
        pSuperior.add(lTitulo);
        pCentral.add(lResumen);
        pInferior.add(bJugar) ;
        
        this.setVisible(true);
    }
}