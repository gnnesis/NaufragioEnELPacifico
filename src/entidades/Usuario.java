package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {
	private String nickname;
	private String contrasena;
	
	public Usuario(String nick, String pass) {
		this.nickname = nick;
		this.contrasena = pass;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contraseña) {
		this.contrasena = contraseña;
	}
	
	public boolean comprobarContrasena(String pass) {
		String hashedPassword = getMd5(pass);
		return contrasena.equals(hashedPassword);
		
	}
	
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
	
	private String getMd5(String input) {
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
}