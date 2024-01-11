package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Partida;
/**
 * Clase de acceso a la base de datos
 * @author izaro
 *
 */
public class BBDD {
	
	private static Connection conn;
	private boolean abierta = false;
	private Logger LOG = Logger.getLogger(BBDD.class.getName());
	private String ruta = "resources/db/Database.db";
	
	public BBDD() {}
	
	public BBDD(String ruta) {
		this.ruta = ruta;
	}
	
	public void abrirConexion()
	{
		try
		{
			abierta = false;
			
			Class.forName("org.sqlite.JDBC");
			
			conn = DriverManager.getConnection("jdbc:sqlite:"+ruta);
			
			abierta = true;
			LOG.log(Level.INFO, "Conexion abierta");
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			LOG.log(Level.SEVERE, "Ha ocurrido un problema abriendo la conexion");
		}
	}
	
	
	public void cerrarConexion()
	{
		try {
			conn.close();
			abierta = false;
			LOG.log(Level.INFO, "Conexion cerrada");
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.log(Level.SEVERE, "Ha ocurrido un problema cerrando la conexion");
		}
	}
	
	public void limpiarBaseDeDatos()
	{
		abrirConexion();
		
		if(abierta)
		{
			try
			{
				Statement st = conn.createStatement();
				st.execute("DELETE FROM PARTIDAS");
				LOG.log(Level.INFO, "Se han eliminado todos los registros de la tabla");
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		cerrarConexion();
	}
	
	public void insertarPartida(Partida p)
	{
		abrirConexion();
		
		if(abierta)
		{
			try
			{
				Statement st = conn.createStatement();
				st.execute("INSERT INTO partidas (nickname, tiempo, clicks) VALUES('"+p.getJugador()+"',"+p.getTiempo()+","+p.getClicks()+");");
				LOG.log(Level.INFO, "Partida insertada");
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		cerrarConexion();
	}
	
	public List<Partida> obtenerTodasLasPartidas()
	{
		List<Partida> partidas = new ArrayList<>();
		Partida aux;
		abrirConexion();
		
		if(abierta)
		{
			try
			{
				Statement st = conn.createStatement();
				ResultSet res = st.executeQuery("SELECT * FROM PARTIDAS");
				
				while(res.next())
				{
					aux = new Partida(res.getString(2),res.getInt(3),res.getInt(4));
					partidas.add(aux);
				}
				
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		cerrarConexion();
		
		return partidas;
	}
	
	public List<Partida> obtenerTodasLasPartidas(String usuario)
	{
		List<Partida> partidas = new ArrayList<>();
		Partida aux;
		abrirConexion();
		
		if(abierta)
		{
			try
			{
				Statement st = conn.createStatement();
				ResultSet res = st.executeQuery("SELECT * FROM PARTIDAS WHERE nickname='"+usuario+"'");
				
				while(res.next())
				{
					aux = new Partida(res.getString(2),res.getInt(3),res.getInt(4));
					partidas.add(aux);
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		cerrarConexion();
		
		return partidas;
	}
	
	public List<Partida> obtenerRanking()
	{
		List<Partida> partidas = obtenerTodasLasPartidas();
		
		partidas.sort(Comparator.comparingInt(Partida::getClicks));
		
		return partidas;
	}
	
	public Map<String, List<Partida>> obtenerPartidasPorUsuario()
	{
		Map<String, List<Partida>> pUsuario = new HashMap<>();
		ArrayList<Partida> aux;
		
		List<Partida> todas = obtenerTodasLasPartidas();
		
		for(Partida p : todas)
		{
			if(pUsuario.containsKey(p.getJugador()))
			{
				pUsuario.get(p.getJugador()).add(p);
			}
			else
			{
				aux = new ArrayList<>();
				aux.add(p);
				pUsuario.put(p.getJugador(), aux);
			}
		}
		
		return pUsuario;
	}
	
	public CompletableFuture<Boolean> checkEstado()
	{
		return CompletableFuture.supplyAsync( () -> {
				
			abrirConexion();
            if (abierta) {
                cerrarConexion();
                return true;
            } else {
                return false;
            }
		});
	}
}