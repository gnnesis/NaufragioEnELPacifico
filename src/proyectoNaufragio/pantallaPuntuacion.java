package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

@SuppressWarnings("serial")
public class pantallaPuntuacion extends JFrame {

    public pantallaPuntuacion() {
        // Básicos
        this.setVisible(true);
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
        JLabel tResumen = new JLabel("RESUMEN DE LA PARTIDA");

        // 3. Cuadrado
        Cuadrado cuadrado = new Cuadrado();
        pCentral.add(cuadrado); // Añadir el cuadrado al panel pCentral
    }

    public static void main(String[] args) {
    	new pantallaPuntuacion();
    }
}


