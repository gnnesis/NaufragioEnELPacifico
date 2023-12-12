package proyectoNaufragio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Partida;

@SuppressWarnings("serial")
public class PantallaRanking extends JFrame {
	
	private static List<Partida> partidas = new ArrayList<>();
	private static String FICH_PARTIDAS;
	private static Logger LOG = Logger.getLogger(PantallaRanking.class.getName());

	public PantallaRanking() {
		cargarPropiedades();
		cargarPartidas();
		ordenarPartidas();
		
		this.setTitle("Ranking de Partidas");
		this.setSize(new Dimension(600,400));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
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
		
		JScrollPane scrollPane = new JScrollPane(tablaRanking);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);
		
		this.add(panelPrincipal);
		this.setVisible(true);
		
	}
	private void cargarPartidas() {
		partidas = new ArrayList<>();
    	final String separador = ";";
    	Partida p;
    	String linea;
    	BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(FICH_PARTIDAS));
			while((linea = br.readLine()) != null)
			{
				String[] params = linea.split(separador);
				p = new Partida(params[0].trim(), Integer.parseInt(params[1]),Integer.parseInt(params[2]));
				partidas.add(p);
				LOG.log(Level.INFO, "Se ha encontrado una partida");
			}
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	private static void cargarPropiedades()
    {
    	try {
    		Properties p = new Properties();
			p.load(new FileInputStream(Rutas.FICH_PROPERTIES));
			FICH_PARTIDAS = p.getProperty("partidas");
		} catch (Exception e1) {
			e1.printStackTrace();
			LOG.log(Level.WARNING,"No se ha podido cargar el fichero propiedades.");
		}
    }
	
	private void ordenarPartidas() {
		Collections.sort(partidas, Comparator.comparingInt(Partida::getTiempo));
	}

	public static void main (String[] args) {
		new PantallaRanking();
	}
}
