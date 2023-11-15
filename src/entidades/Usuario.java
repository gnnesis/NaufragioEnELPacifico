package entidades;


import java.awt.Image;


public class Usuario {
	private String nickname;
	private String contrasena;
	//public Image foto;
	
	public Usuario(String nick, String contraseña, Image foto) {
		super();
		this.nickname = nickname;
		this.contrasena = contrasena;
		//this.foto = foto;
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

	//public Image getFoto() {
		//return foto;
	//}

	//public void setFoto(Image foto) {
	//	this.foto = foto;
	//} 
	
	public boolean comprobarContrasena(String pass) {
		return pass.equals(contrasena);
		
	}

}
