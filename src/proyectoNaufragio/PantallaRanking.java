package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.BBDD;
import entidades.Partida;
import entidades.RendererTabla;
import entidades.Usuario;

@SuppressWarnings("serial")
public class PantallaRanking extends JFrame {
	
	private static List<Partida> partidas = new ArrayList<>();
	private static Logger LOG = Logger.getLogger(PantallaRanking.class.getName());
	private Usuario u;

	public PantallaRanking(Usuario u, boolean global) {
		this.u = u;
		cargarPartidas(global);
		ordenarPartidas();
		Color cRosa= new Color(255,102,196);
		
		this.setTitle("Ranking de Partidas");
		this.setSize(new Dimension(600,400));
		Image iconImage = new ImageIcon("resources/images/IconoNP.png").getImage();
        setIconImage(iconImage);
		this.setLayout(new BorderLayout());
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		JPanel sur = new JPanel();
		JButton bSalir = new JButton("SALIR");
		bSalir.setBackground(cRosa);
		bSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		DefaultTableModel modeloTabla = new DefaultTableModel();
		
		modeloTabla.addColumn("Posicion");
		modeloTabla.addColumn("Jugador");
		modeloTabla.addColumn("Tiempo");
		modeloTabla.addColumn("Clicks");
		
		int posicion = 1;
		
		for (Partida partida : partidas) {
			Object[] fila = {posicion, partida.getJugador(), partida.getTiempo(), partida.getClicks()};
			modeloTabla.addRow(fila);
			posicion++;
		}
		
		JTable tablaRanking = new JTable(modeloTabla);
		tablaRanking.setFont(new Font ("Arial", Font.PLAIN, 12));
		tablaRanking.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaRanking.setDefaultRenderer(Object.class, new RendererTabla());
		
		sur.add(bSalir);
		
		JScrollPane scrollPane = new JScrollPane(tablaRanking);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);
		panelPrincipal.add(sur, BorderLayout.SOUTH);
		
		this.add(panelPrincipal);
		this.setVisible(true);
		
	}
	private void cargarPartidas(boolean global) {
		BBDD bd = new BBDD();
		if(global)
		{
			partidas = bd.obtenerTodasLasPartidas();
		}
		else
		{
			partidas = bd.obtenerTodasLasPartidas(u.getNickname());
		}
		
		LOG.log(Level.INFO, "Partidas cargadas");
	}
	
	private void ordenarPartidas() {
		Collections.sort(partidas, Comparator.comparingInt(Partida::getTiempo));
	}
}