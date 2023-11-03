package proyectoNaufragio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// JUEGO DE PRUEBA
// MATRIZ 9X9
/**
 * El juego tendra un JFrame interactivo con un gridLayout de 9x9.
 * El tablero de juego será una matriz de 9x9 y en cada hueco habrá un boton que cuando el jugador acierte que hay un barco,
 * la casilla se pondrá azul, si no se pondrá rojo. Los barcos se colocarán siempre en el mismo sitio.
 * Hay 3 barcos de diferente longitud: barco1 longitud de 4 casillas, barco2 longitud de 2 y barco3 de longitud de 5.
 * Estos solo pueden estar colocados en vertical o en horizontal.
 * Importante que no sea en diagonal, y no se pueden sobre poner entre ellos.
 * Cada vez que se haga clic en una casilla/botón habrá un contador de clicks.
 * Si el ususario consigue destapar una casilla azul sale una notificación de "¡Tocado!"
 * y que barco ha destapado y de que longitud es. Y cuando destape todos los barcos saldra una notificación de fin del juego y
 * cuantos clicks ha dado. Donde colocar los barcos: - Barco1: [1][0], [1][1],[1][2], [1][3] - Barco2: [6][7],[6][8] - Barco3: [2][1],[3][1],[4][1],[5][1],[6][1
 *
 *
 */
public class Juego {
    private Casilla[][] tablero;
    private int contadorClicks;
    private JFrame frame;

    public Juego() {
        tablero = new Casilla[9][9];
        contadorClicks = 0;
        frame = new JFrame("Hundir la flota");
        frame.setLayout(new GridLayout(9,9));
        inicializarTablero();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void inicializarTablero() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tablero[i][j] = new Casilla(i, j);
                // Coloca los barcos en las posiciones deseadas
                if ((i == 1 && j >= 0 && j <= 3) || (i == 6 && j >= 7 && j <= 8) || (i >= 2 && i <= 6 && j == 1)) {
                    tablero[i][j].setHayBarco(true);
                }
                frame.add(tablero[i][j]);
            }
        }
    }

    public int getContadorClicks() {
        return contadorClicks;
    }

    class Casilla extends JButton {
        private boolean hayBarco;
        private int x, y;

        public Casilla(int x, int y) {
            this.x = x;
            this.y = y;
            this.hayBarco = false;
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clickCasilla();
                }
            });
        }

        public boolean isHayBarco() {
            return hayBarco;
        }

        public void setHayBarco(boolean hayBarco) {
            this.hayBarco = hayBarco;
        }

        public void clickCasilla() {
            contadorClicks++;
            if (hayBarco) {
                setBackground(Color.BLUE);
                JOptionPane.showMessageDialog(frame, "¡Tocado!");
                verificarFinDelJuego();
            } else {
                setBackground(Color.RED);
            }
        }

        private void verificarFinDelJuego() {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (tablero[i][j].isHayBarco() && tablero[i][j].getBackground() != Color.BLUE) {
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "¡Hundido! Has dado " + contadorClicks + " clicks.");
        }
    }

    public static void main(String[] args) {
        new Juego();
    }
}
