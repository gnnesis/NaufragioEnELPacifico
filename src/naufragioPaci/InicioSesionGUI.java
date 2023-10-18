package naufragioPaci;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesionGUI {
    public static void main(String[] args) {
        // Crear un JFrame
        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2));

        // Etiquetas y campos de texto
        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField();
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField();

        // Botones
        JButton iniciarSesionButton = new JButton("Iniciar Sesión");
        JButton limpiarButton = new JButton("Limpiar");

        // Agregar componentes al frame
        frame.add(usuarioLabel);
        frame.add(usuarioField);
        frame.add(contrasenaLabel);
        frame.add(contrasenaField);
        frame.add(iniciarSesionButton);
        frame.add(limpiarButton);

        // Acción del botón "Iniciar Sesión"
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica de autenticación
                String usuario = usuarioField.getText();
                char[] contrasena = contrasenaField.getPassword();

                // Por ahora, simplemente mostraremos los datos ingresados
                JOptionPane.showMessageDialog(frame, "Usuario: " + usuario + "\nContraseña: " + new String(contrasena));
            }
        });

        // Acción del botón "Limpiar"
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioField.setText("");
                contrasenaField.setText("");
            }
        });

        // Mostrar el frame
        frame.setVisible(true);
    }
}
