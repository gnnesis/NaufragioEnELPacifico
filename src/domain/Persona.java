package domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Persona {
	private String nickname;
	private String contrasena;
	
	/**
	 * Constructor de Persona
	 * @param nick Nickname de la persona que juega
	 * @param pass Contraseña del jugador
	 */
	public Persona(String nick, String pass) {
		this.nickname = nick;
		this.contrasena = pass;
	}
	
	/**
	 * Metodo para obtener el nickname de la persona jugadora
	 * @return Nickname del jugador
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * Metodo para actualizar el nickname del jugador
	 * @param nickname Nickname del jugador
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	/**
	 * Metodo para obtener la contraseña del jugador
	 * @return Contraseña del jugador
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Metodo para actualizar la contraseña del jugador
	 * @param contraseña Conrtaseña del jugador
	 */
	public void setContrasena(String contraseña) {
		this.contrasena = contraseña;
	}
	
	/**
	 * Metodo para comprobar si la contraseña introducida coincide con la contraseña del jugador
	 * @param pass Contraseña introducida
	 * @return booleano que indica si las contraseña es correcta
	 */
	public boolean comprobarContrasena(String pass) {
		String hashedPassword = getMd5(pass);
		return contrasena.equals(hashedPassword);
	}
	
	/**
	 * Metodo para registrar nuevo jugador en el fichero de una BD
	 * @param fich Fichero al que se añadira el nuevo jugador
	 */
	public void registrar(String fich)
	{
		final String separador = ";";
		BufferedWriter br;
		try
		{
			br = new BufferedWriter(new FileWriter(fich, true));
			String cifrada = getMd5(contrasena);
			br.write("\n" + nickname + separador + cifrada);
			br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo hash MD5
	 * @param input Contraseña a cifrar
	 * @return Contraseña cifrada
	 */
	public String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			
			BigInteger no = new BigInteger(1, messageDigest);
			
			String hashtext = no.toString(16);
			while (hashtext.length()<32) {
				hashtext = "0" + hashtext;
			}
			
			return hashtext;
			
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/**
	 * Metodo String de clase
	 */
	@Override
	public String toString() {
		return "Persona [nickname=" + nickname + ", contrasena=" + contrasena + "]";
	}
	
	
}