package naufragioPaci;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HundirFlotaGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hundir la Flota");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel para la cuadrícula de juego
        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        JButton[][] buttons = new JButton[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                buttons[i][j] = new JButton();
                gridPanel.add(buttons[i][j]);
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Lógica para manejar los clics en los botones (agua, tocado, hundido)
                    }
                });
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);

        // Panel para estadísticas y contador de tiempo
        JPanel infoPanel = new JPanel(new FlowLayout());
        JLabel tiempoLabel = new JLabel("Tiempo: 00:00");
        JLabel aguasLabel = new JLabel("Aguas: 0");
        JLabel tocadosLabel = new JLabel("Tocados: 0");
        JLabel hundidosLabel = new JLabel("Hundidos: 0");

        infoPanel.add(tiempoLabel);
        infoPanel.add(aguasLabel);
        infoPanel.add(tocadosLabel);
        infoPanel.add(hundidosLabel);

        frame.add(infoPanel, BorderLayout.SOUTH);

        // Debes implementar la lógica del juego, como colocar los barcos y realizar un seguimiento de las estadísticas y el tiempo.

        frame.pack();
        frame.setVisible(true);
    }
}
